package com.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class FileUploadController {

    private final Path uploadDir;

    public FileUploadController() throws IOException {
        uploadDir = Files.createTempDirectory("day61-uploads");
    }

    public Path getUploadDir() {
        return uploadDir;
    }

    public long countUploadedFiles() throws IOException {
        try (var files = Files.list(uploadDir)) {
            return files.count();
        }
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "redirect:/error";
        }
        Path target = uploadDir.resolve(file.getOriginalFilename());
        file.transferTo(target);
        return "redirect:/success";
    }
}
