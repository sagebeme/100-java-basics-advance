package com.learning.controller;

import com.learning.model.ContactForm;
import com.learning.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public String showForm(Model model) {
        if (!model.containsAttribute("contactForm")) {
            model.addAttribute("contactForm", new ContactForm());
        }
        return "contact";
    }

    @PostMapping("/contact")
    public String submit(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "contact";
        }
        contactService.save(contactForm);
        // Post/Redirect/Get: the browser ends up on a plain GET, so refreshing the page
        // doesn't send the same message again.
        redirectAttributes.addFlashAttribute("submitted", true);
        return "redirect:/contact";
    }
}
