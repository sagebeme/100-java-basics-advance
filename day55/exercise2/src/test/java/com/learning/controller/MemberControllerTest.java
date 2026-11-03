package com.learning.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// The tests share one running app, so each uses its own phone numbers and names.
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static String member(String name, String phone, int shillings) {
        return "{\"name\":\"" + name + "\",\"phone\":\"" + phone + "\",\"monthlyShillings\":" + shillings + "}";
    }

    private long join(String name, String phone) throws Exception {
        String body = mockMvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON).content(member(name, phone, 500)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readTree(body).get("id").asLong();
    }

    @Test
    void aValidMemberJoins() throws Exception {
        mockMvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON).content(member("Wanjiru Kamau", "0712000101", 1000)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Wanjiru Kamau"));
    }

    @Test
    void everyBrokenFieldIsReportedAtOnce() throws Exception {
        mockMvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON).content(member(" ", "712000102", 50)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors.name").value("Type the member's name"))
                .andExpect(jsonPath("$.errors.phone").value("Use a phone like 0712345678"))
                .andExpect(jsonPath("$.errors.monthlyShillings").value("The smallest monthly contribution is KES 100"))
                .andExpect(jsonPath("$.message").doesNotExist());
    }

    @Test
    void aMissingPhoneHasItsOwnMessage() throws Exception {
        mockMvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Otieno\",\"monthlyShillings\":200}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.phone").value("A phone number is needed for M-Pesa"))
                .andExpect(jsonPath("$.errors.name").doesNotExist());
    }

    @Test
    void bodyThatIsntJsonIs400WithAMessage() throws Exception {
        mockMvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON).content("{\"name\": "))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("The body isn't valid JSON for a member"));
    }

    @Test
    void anUnknownMemberIs404WithAMessage() throws Exception {
        mockMvc.perform(get("/api/members/999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("No member with id 999999"));
        mockMvc.perform(delete("/api/members/999999")).andExpect(status().isNotFound());
    }

    @Test
    void aPhoneCanOnlyBelongToOneMember() throws Exception {
        join("Akinyi", "0712000103");
        mockMvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON).content(member("Someone Else", "0712000103", 300)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("0712000103 is already a member"));
    }

    @Test
    void updatingYourselfKeepsYourOwnPhone() throws Exception {
        long id = join("Mutua", "0712000104");
        mockMvc.perform(put("/api/members/" + id)
                        .contentType(MediaType.APPLICATION_JSON).content(member("Mutua Musyoka", "0712000104", 800)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mutua Musyoka"))
                .andExpect(jsonPath("$.monthlyShillings").value(800));
    }

    @Test
    void updatesAreCheckedToo() throws Exception {
        long id = join("Chebet", "0712000105");
        join("Kiprop", "0712000106");
        mockMvc.perform(put("/api/members/" + id)
                        .contentType(MediaType.APPLICATION_JSON).content(member("Chebet", "0712000106", 500)))
                .andExpect(status().isConflict());
        mockMvc.perform(put("/api/members/" + id)
                        .contentType(MediaType.APPLICATION_JSON).content(member("", "0712000105", 500)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.name").exists());
    }

    @Test
    void searchesByPartOfTheNameIgnoringCase() throws Exception {
        join("Nyambura Searchtest", "0712000107");
        join("Kamau Searchtest", "0712000108");
        mockMvc.perform(get("/api/members").param("name", "NYAMBURA SEARCH"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].phone").value("0712000107"));
    }

    @Test
    void leavingAnswers204() throws Exception {
        long id = join("Barasa", "0712000109");
        mockMvc.perform(delete("/api/members/" + id)).andExpect(status().isNoContent());
        mockMvc.perform(get("/api/members/" + id)).andExpect(status().isNotFound());
    }
}
