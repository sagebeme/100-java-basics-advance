package com.learning.controller;

import com.learning.model.Contact;
import com.learning.service.CsvService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private CsvService csvService;

    private final List<Contact> contacts = new ArrayList<>(List.of(
            new Contact("Amina", "amina@example.com", "0700000001"),
            new Contact("Kip", "kip@example.com", "0700000002")
    ));

    public List<Contact> getContacts() {
        return contacts;
    }

    @GetMapping("/contacts")
    @ResponseBody
    public String listContacts() {
        StringBuilder body = new StringBuilder(contacts.size() + " contacts\n");
        for (Contact c : contacts) {
            body.append(c.getName()).append(" - ").append(c.getEmail()).append("\n");
        }
        return body.toString();
    }

    @GetMapping("/contacts/export")
    public void exportContacts(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=contacts.csv");
        PrintWriter writer = response.getWriter();
        writer.print(csvService.toCsv(contacts));
    }

    @PostMapping("/contacts/import")
    public String importContacts(@RequestParam("file") MultipartFile file, org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) throws IOException {
        CsvService.ImportResult result = csvService.parseCsv(file.getInputStream());
        contacts.addAll(result.imported);
        redirectAttributes.addFlashAttribute("imported", result.imported.size());
        redirectAttributes.addFlashAttribute("errors", result.errors);
        return "redirect:/contacts";
    }
}
