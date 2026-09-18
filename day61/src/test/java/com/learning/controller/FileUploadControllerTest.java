package com.learning.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FileUploadController fileUploadController;

    @Test
    void uploadingARealFileSavesItAndRedirectsToSuccess() throws Exception {
        long before = fileUploadController.countUploadedFiles();

        MockMultipartFile file = new MockMultipartFile("file", "notes.txt", "text/plain", "Day 61 notes".getBytes());

        mockMvc.perform(multipart("/upload").file(file))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/success"));

        assertEquals(before + 1, fileUploadController.countUploadedFiles());
        assertEquals("Day 61 notes", java.nio.file.Files.readString(fileUploadController.getUploadDir().resolve("notes.txt")));
    }

    @Test
    void uploadingAnEmptyFileRedirectsToError() throws Exception {
        MockMultipartFile emptyFile = new MockMultipartFile("file", "empty.txt", "text/plain", new byte[0]);

        mockMvc.perform(multipart("/upload").file(emptyFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error"));
    }
}
