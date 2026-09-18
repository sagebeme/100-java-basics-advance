package com.learning;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @GetMapping("/account")
    public String account(
            @RequestParam(defaultValue = "false") boolean loggedIn,
            @RequestParam(defaultValue = "GUEST") String role,
            @RequestParam(defaultValue = "0") double balance,
            Model model
    ) {
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("role", role);
        model.addAttribute("balance", balance);
        return "account";
    }
}
