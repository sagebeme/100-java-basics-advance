package com.learning;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Already written. Your work is in the two templates.
@Controller
public class PageController {

    static final List<String> COUNTIES = List.of("Meru", "Tharaka-Nithi", "Embu", "Isiolo");

    @GetMapping("/prices")
    public String prices(Model model) {
        model.addAttribute("produce", List.of(
                new Produce("Avocado (Hass)", "kg", 45, true),
                new Produce("Miraa", "bundle", 300, true),
                new Produce("Mangoes", "crate", 1200, false),
                new Produce("French beans", "kg", 70, true)));
        return "prices";
    }

    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("counties", COUNTIES);
        model.addAttribute("errors", Map.of());
        return "join";
    }

    @PostMapping("/join")
    public String join(@RequestParam(defaultValue = "") String name,
                       @RequestParam(defaultValue = "") String phone,
                       @RequestParam(defaultValue = "") String county,
                       Model model, RedirectAttributes redirect) {
        Map<String, String> errors = new LinkedHashMap<>();
        if (name.isBlank()) {
            errors.put("name", "Type your name as it is on your ID.");
        }
        if (!phone.replaceAll("[\\s-]", "").matches("0[17]\\d{8}")) {
            errors.put("phone", "Use your M-Pesa number, like 0712 345 678.");
        }
        if (!COUNTIES.contains(county)) {
            errors.put("county", "Choose the county where you farm.");
        }
        if (errors.isEmpty()) {
            redirect.addFlashAttribute("joined", "Karibu, " + name.trim() + "! We'll call you this week.");
            return "redirect:/prices";
        }
        model.addAttribute("counties", COUNTIES);
        model.addAttribute("errors", errors);
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        model.addAttribute("county", county);
        return "join";
    }
}
