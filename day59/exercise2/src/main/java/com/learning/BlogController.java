package com.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BlogController {

    private static final int PAGE_SIZE = 3;

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public String listPosts(@RequestParam(defaultValue = "1") int page, Model model) {
        model.addAttribute("posts", postService.getPage(page, PAGE_SIZE));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postService.totalPages(PAGE_SIZE));
        return "posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean deleted = postService.deletePost(id);
        redirectAttributes.addFlashAttribute("message", deleted ? "Post deleted" : "Post not found");
        return "redirect:/posts";
    }
}
