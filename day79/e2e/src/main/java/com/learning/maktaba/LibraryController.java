package com.learning.maktaba;

import jakarta.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class LibraryController {

    static final DateTimeFormatter DUE = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy", Locale.ENGLISH);
    private final LibraryService library;

    public LibraryController(LibraryService library) {
        this.library = library;
    }

    @GetMapping("/")
    public String catalogue(Model model) {
        model.addAttribute("books", library.catalogue());
        return "catalogue";
    }

    @GetMapping("/borrow/{id}")
    public String borrowForm(@PathVariable long id, Model model) {
        model.addAttribute("book", library.find(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        model.addAttribute("form", new BorrowForm());
        return "borrow";
    }

    @PostMapping("/borrow/{id}")
    public String borrow(@PathVariable long id, @Valid @ModelAttribute("form") BorrowForm form, BindingResult errors, Model model) {
        Book book = library.find(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (errors.hasErrors()) {
            model.addAttribute("book", book);
            return "borrow";
        }
        try {
            Loan loan = library.borrow(id, form.getMemberNumber(), form.getDays());
            return "redirect:/loans/" + loan.code(); // Post/Redirect/Get: refreshing the receipt can't borrow twice
        } catch (NoCopiesLeftException e) {
            model.addAttribute("book", book);
            model.addAttribute("problem", e.getMessage());
            return "borrow";
        }
    }

    @GetMapping("/loans/{code}")
    public String receipt(@PathVariable String code, Model model) {
        Loan loan = library.loan(code).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("loan", loan);
        model.addAttribute("due", DUE.format(loan.due()));
        return "loan";
    }
}
