package com.learning.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FileUploadController fileUploadController;

    @Test
    void showsTheUploadForm() throws Exception {
        mockMvc.perform(get("/upload"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("enctype=\"multipart/form-data\"")));
    }

    @Test
    void uploadingARealFileSavesItAndSaysSo() throws Exception {
        long before = fileUploadController.countUploadedFiles();

        MockMultipartFile file = new MockMultipartFile("file", "notes.txt", "text/plain", "Day 61 notes".getBytes());

        mockMvc.perform(multipart("/upload").file(file))
                .andExpect(redirectedUrl("/upload"))
                .andExpect(flash().attribute("message", "Saved notes.txt."));

        assertEquals(before + 1, fileUploadController.countUploadedFiles());
        assertEquals("Day 61 notes", Files.readString(fileUploadController.getUploadDir().resolve("notes.txt")));
    }

    @Test
    void uploadingAnEmptyFileAsksForOne() throws Exception {
        MockMultipartFile emptyFile = new MockMultipartFile("file", "empty.txt", "text/plain", new byte[0]);

        mockMvc.perform(multipart("/upload").file(emptyFile))
                .andExpect(redirectedUrl("/upload"))
                .andExpect(flash().attribute("error", "Choose a file first."));
    }

    @Test
    void aFileNameCannotClimbOutOfTheUploadFolder() throws Exception {
        MockMultipartFile sneaky = new MockMultipartFile("file", "../../escaped.txt", "text/plain", "x".getBytes());

        mockMvc.perform(multipart("/upload").file(sneaky))
                .andExpect(flash().attribute("message", "Saved escaped.txt."));

        assertFalse(Files.exists(fileUploadController.getUploadDir().getParent().getParent().resolve("escaped.txt")));
        assertEquals("x", Files.readString(fileUploadController.getUploadDir().resolve("escaped.txt")));
    }

    @Test
    void safeNameKeepsOnlyTheLastPart() {
        assertEquals("receipt.jpg", FileUploadController.safeName("C:\\Users\\Wanjiru\\receipt.jpg"));
        assertEquals("passwd", FileUploadController.safeName("../../etc/passwd"));
        assertNull(FileUploadController.safeName(".."));
        assertNull(FileUploadController.safeName("photos/"));
        assertNull(FileUploadController.safeName(null));
    }
}
