package com.learning.controller;

import com.learning.model.Contact;
import com.learning.service.CsvService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class ContactController {

    @Autowired
    private CsvService csvService;

    // Many requests can arrive at once; a CopyOnWriteArrayList stays correct when they do.
    private final List<Contact> contacts = new CopyOnWriteArrayList<>(List.of(
            new Contact("Amina", "amina@example.com", "0700000001"),
            new Contact("Kip", "kip@example.com", "0700000002")
    ));

    public List<Contact> getContacts() {
        return contacts;
    }

    // The list, the import form and the export link. After an import, the redirect brings the
    // flash attributes "imported" and "errors" into the model, and the page shows them.
    @GetMapping("/contacts")
    public String listContacts(Model model) {
        model.addAttribute("contacts", contacts);
        return "contacts";
    }

    @GetMapping("/contacts/export")
    public void exportContacts(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=contacts.csv");
        PrintWriter writer = response.getWriter();
        writer.print(csvService.toCsv(contacts));
    }

    @PostMapping("/contacts/import")
    public String importContacts(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("errors", List.of("Choose a CSV file first."));
            return "redirect:/contacts";
        }
        CsvService.ImportResult result = csvService.parseCsv(file.getInputStream());
        contacts.addAll(result.imported);
        redirectAttributes.addFlashAttribute("imported", result.imported.size());
        redirectAttributes.addFlashAttribute("errors", result.errors);
        return "redirect:/contacts";
    }
}
