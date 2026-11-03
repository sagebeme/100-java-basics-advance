package com.learning.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactController contactController;

    @Test
    void exportDownloadsACsvAttachmentWithTheSeededContacts() throws Exception {
        mockMvc.perform(get("/contacts/export"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", containsString("attachment; filename=contacts.csv")))
                .andExpect(content().string(containsString("Name,Email,Phone")))
                .andExpect(content().string(containsString("Amina,amina@example.com")));
    }

    @Test
    void importingAValidCsvAddsToTheContactList() throws Exception {
        int before = contactController.getContacts().size();
        String csv = "Name,Email,Phone\nZawadi,zawadi@example.com,0700000099\n";
        MockMultipartFile file = new MockMultipartFile("file", "import.csv", "text/csv", csv.getBytes());

        mockMvc.perform(multipart("/contacts/import").file(file))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/contacts"))
                .andExpect(flash().attribute("imported", 1));

        org.junit.jupiter.api.Assertions.assertEquals(before + 1, contactController.getContacts().size());
    }

    @Test
    void importingABadRowReportsAnErrorWithoutAddingIt() throws Exception {
        int before = contactController.getContacts().size();
        String csv = "Name,Email,Phone\n,missingname@example.com,0700000099\n";
        MockMultipartFile file = new MockMultipartFile("file", "import.csv", "text/csv", csv.getBytes());

        mockMvc.perform(multipart("/contacts/import").file(file))
                .andExpect(flash().attribute("imported", 0));

        org.junit.jupiter.api.Assertions.assertEquals(before, contactController.getContacts().size());
    }

    @Test
    void theListPageShowsWhatAnImportDid() throws Exception {
        mockMvc.perform(get("/contacts").flashAttr("imported", 2).flashAttr("errors", java.util.List.of("Row 3: name and email are required")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Imported 2 contacts.")))
                .andExpect(content().string(containsString("Row 3: name and email are required")))
                .andExpect(content().string(containsString("Amina")));
    }

    @Test
    void importingWithNoFileSaysSo() throws Exception {
        MockMultipartFile empty = new MockMultipartFile("file", "", "text/csv", new byte[0]);

        mockMvc.perform(multipart("/contacts/import").file(empty))
                .andExpect(redirectedUrl("/contacts"))
                .andExpect(flash().attribute("errors", java.util.List.of("Choose a CSV file first.")));
    }
}
