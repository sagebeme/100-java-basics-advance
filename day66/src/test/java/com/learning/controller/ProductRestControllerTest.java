package com.learning.controller;

import com.learning.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String json(Product product) {
        return objectMapper.writeValueAsString(product);
    }

    private Long createProduct(String name, double price) throws Exception {
        String response = mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(json(new Product(name, price))))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readTree(response).get("id").asLong();
    }

    // POST

    @Test
    void creatingAProductReturns201WithTheSavedResourceAndWhereItLives() throws Exception {
        String response = mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(json(new Product("Notebook", 250))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Notebook"))
                .andReturn().getResponse().getContentAsString();
        long id = objectMapper.readTree(response).get("id").asLong();
        mockMvc.perform(get("/api/v1/products/" + id)).andExpect(status().isOk());
        mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(json(new Product("Exercise book", 80))))
                .andExpect(header().string("Location", endsWith("/api/v1/products/" + (id + 1))));
    }

    @Test
    void creatingAProductWithATakenNameReturns409() throws Exception {
        createProduct("Pen", 20);
        mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(json(new Product("PEN", 25))))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("There's already a product called PEN"));
    }

    @Test
    void valuesThatBreakTheRulesReturn422() throws Exception {
        mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(json(new Product("", -5))))
                .andExpect(status().isUnprocessableContent())
                .andExpect(jsonPath("$.status").value(422))
                .andExpect(jsonPath("$.message").value("name is required, price must be positive"));
    }

    @Test
    void aBodyThatIsNotValidJsonReturns400() throws Exception {
        mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content("{\"name\": \"Pen\", \"price\": "))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void aBodyThatIsNotJsonAtAllReturns415() throws Exception {
        mockMvc.perform(post("/api/v1/products").contentType(MediaType.TEXT_PLAIN).content("Pen, 20"))
                .andExpect(status().isUnsupportedMediaType());
    }

    // GET

    @Test
    void gettingAKnownProductReturns200() throws Exception {
        Long id = createProduct("Pen", 20);
        mockMvc.perform(get("/api/v1/products/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pen"));
    }

    @Test
    void gettingAnUnknownProductReturns404WithAStructuredError() throws Exception {
        mockMvc.perform(get("/api/v1/products/999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("No product with id 999999"));
    }

    @Test
    void aMethodThePathDoesNotSupportReturns405AndSaysWhichOnesItDoes() throws Exception {
        mockMvc.perform(delete("/api/v1/products"))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(header().string("Allow", containsString("GET")))
                .andExpect(header().string("Allow", containsString("POST")));
    }

    // PUT

    @Test
    void updatingAKnownProductReturns200WithNewValues() throws Exception {
        Long id = createProduct("Old Name", 10);
        mockMvc.perform(put("/api/v1/products/" + id).contentType(MediaType.APPLICATION_JSON).content(json(new Product("New Name", 15))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Name"));
    }

    @Test
    void sendingTheSamePutTwiceLeavesTheSameResult() throws Exception {
        Long id = createProduct("Ruler", 50);
        for (int i = 0; i < 2; i++) {
            mockMvc.perform(put("/api/v1/products/" + id).contentType(MediaType.APPLICATION_JSON).content(json(new Product("Ruler", 60))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.price").value(60.0));
        }
    }

    @Test
    void putReplacesTheWholeProductSoAMissingFieldIsLost() throws Exception {
        Long id = createProduct("Eraser", 30);
        // Only a name sent. A PUT is the whole product, so the missing price can't be read: Jackson won't invent one. 400.
        // To change just the name, use PATCH.
        mockMvc.perform(put("/api/v1/products/" + id).contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Big eraser\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatingAnUnknownProductReturns404() throws Exception {
        mockMvc.perform(put("/api/v1/products/999999").contentType(MediaType.APPLICATION_JSON).content(json(new Product("X", 1))))
                .andExpect(status().isNotFound());
    }

    // PATCH

    @Test
    void patchChangesOnlyTheFieldsSent() throws Exception {
        Long id = createProduct("Crayons", 120);
        mockMvc.perform(patch("/api/v1/products/" + id).contentType(MediaType.APPLICATION_JSON).content("{\"price\": 150}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Crayons"))
                .andExpect(jsonPath("$.price").value(150.0));
    }

    @Test
    void patchStillChecksTheRules() throws Exception {
        Long id = createProduct("Glue", 90);
        mockMvc.perform(patch("/api/v1/products/" + id).contentType(MediaType.APPLICATION_JSON).content("{\"price\": -1}"))
                .andExpect(status().isUnprocessableContent());
        mockMvc.perform(patch("/api/v1/products/" + id).contentType(MediaType.APPLICATION_JSON).content("{\"name\": \"  \"}"))
                .andExpect(status().isUnprocessableContent());
        createProduct("Tape", 70);
        mockMvc.perform(patch("/api/v1/products/" + id).contentType(MediaType.APPLICATION_JSON).content("{\"name\": \"tape\"}"))
                .andExpect(status().isConflict());
    }

    // DELETE

    @Test
    void deletingAKnownProductReturns204ThenItIsGone() throws Exception {
        Long id = createProduct("Delete Me", 5);
        mockMvc.perform(delete("/api/v1/products/" + id)).andExpect(status().isNoContent());
        mockMvc.perform(get("/api/v1/products/" + id)).andExpect(status().isNotFound());
        // A second DELETE answers 404, but the product is just as gone: the state is the same, so DELETE is idempotent.
        mockMvc.perform(delete("/api/v1/products/" + id)).andExpect(status().isNotFound());
    }

    @Test
    void deletingAnUnknownProductReturns404InsteadOfSilentlySucceeding() throws Exception {
        mockMvc.perform(delete("/api/v1/products/999999")).andExpect(status().isNotFound());
    }
}
