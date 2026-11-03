package com.learning;

import java.time.LocalDate;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

// Already written. Each guest has their own card at /card/{id}.
@Controller
public class CardController {

    private final Map<Integer, Guest> guests = Map.of(
            7, new Guest(7, "Mama Atieno", 5000),
            8, new Guest(8, "Brian Kiprotich", 0),
            9, new Guest(9, "<script>alert('hi')</script>", 100));

    @GetMapping("/card/{id}")
    public String card(@PathVariable int id, Model model) {
        Guest guest = guests.get(id);
        if (guest == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No card for guest " + id);
        }
        model.addAttribute("guest", guest);
        model.addAttribute("cause", "Achieng's school fees at Moi University");
        model.addAttribute("date", LocalDate.of(2026, 12, 12));
        model.addAttribute("venue", "St. Paul's Hall, Kisumu");
        model.addAttribute("paybill", "247247");
        model.addAttribute("account", "FEES-ACHIENG");
        return "card";
    }
}
