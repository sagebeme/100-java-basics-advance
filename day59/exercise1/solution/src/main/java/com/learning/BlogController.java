package com.learning;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class BlogController {

    private final PostService postService;

    // Spring passes in the PostService it made. Asking for it in the constructor makes it
    // obvious what the controller needs, and lets a test pass in its own.
    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(Model model) {
        model.addAttribute("posts", postService.all());
        return "posts";
    }

    @GetMapping("/posts/{id}")
    public String one(@PathVariable long id, Model model) {
        Post post = postService.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No story " + id));
        model.addAttribute("post", post);
        return "post";
    }
}
