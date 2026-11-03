package com.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// Receipts and photos sent with an order. Files go into a temporary folder.
@Controller
public class FileUploadController {

    private final Path uploadDir;

    public FileUploadController() throws IOException {
        uploadDir = Files.createTempDirectory("day61-uploads").toRealPath();
    }

    public Path getUploadDir() {
        return uploadDir;
    }

    public long countUploadedFiles() throws IOException {
        try (var files = Files.list(uploadDir)) {
            return files.count();
        }
    }

    @GetMapping("/upload")
    public String form() {
        return "upload";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirect) throws IOException {
        if (file.isEmpty()) {
            redirect.addFlashAttribute("error", "Choose a file first.");
            return "redirect:/upload";
        }
        String name = safeName(file.getOriginalFilename());
        Path target = name == null ? null : uploadDir.resolve(name).normalize();
        if (target == null || !target.getParent().equals(uploadDir)) {
            redirect.addFlashAttribute("error", "That file name isn't allowed.");
            return "redirect:/upload";
        }
        file.transferTo(target);
        redirect.addFlashAttribute("message", "Saved " + name + ".");
        return "redirect:/upload";
    }

    // The browser chooses the file name, so never trust it: "../../.bashrc" would be saved outside
    // the upload folder. Keep only the last part, after any / or \, and refuse what's left if it's
    // empty, "." or "..".
    static String safeName(String original) {
        if (original == null) {
            return null;
        }
        String name = original.replace('\\', '/');
        name = name.substring(name.lastIndexOf('/') + 1).strip();
        if (name.isEmpty() || name.equals(".") || name.equals("..")) {
            return null;
        }
        return name;
    }
}
