package com.learning.controller;

import com.learning.service.MessagingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessagingController {

    private final MessagingService messagingService;

    public MessagingController(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("messages", messagingService.listMessages());
        return "index";
    }

    @PostMapping("/messages/send")
    public String send(@RequestParam String toNumber, @RequestParam String body) {
        messagingService.sendMessage(toNumber, body);
        return "redirect:/";
    }
}
