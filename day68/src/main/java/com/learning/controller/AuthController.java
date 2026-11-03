package com.learning.controller;

import com.learning.model.AppUser;
import com.learning.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    static final int MIN_PASSWORD_LENGTH = 8;

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        String name = username.strip();
        String problem = null;
        if (name.isEmpty()) {
            problem = "Choose a username.";
        } else if (password.length() < MIN_PASSWORD_LENGTH) {
            problem = "Use a password of at least " + MIN_PASSWORD_LENGTH + " characters. A short sentence is easy to remember.";
        } else if (appUserRepository.findByUsername(name).isPresent()) {
            problem = "That username is taken.";
        }
        if (problem != null) {
            model.addAttribute("error", problem);
            model.addAttribute("username", name);   // never send the password back
            return "register";
        }
        appUserRepository.save(new AppUser(name, passwordEncoder.encode(password)));
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        return "dashboard";
    }
}
