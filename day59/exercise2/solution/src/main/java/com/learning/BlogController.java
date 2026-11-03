package com.learning;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BlogController {

    static final int PAGE_SIZE = 3;

    private final PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {
        int totalPages = postService.totalPages(PAGE_SIZE);
        if (page < 1) {
            return "redirect:/posts";
        }
        if (page > totalPages) {
            return "redirect:/posts?page=" + totalPages;
        }
        model.addAttribute("posts", postService.getPage(page, PAGE_SIZE));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "posts";
    }

    // A POST, not a GET: a GET must never change anything, or a link preview or a
    // search engine following links could delete stories.
    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id, RedirectAttributes redirect) {
        boolean deleted = postService.delete(id);
        redirect.addFlashAttribute("message", deleted ? "Story deleted." : "That story was already gone.");
        return "redirect:/posts";
    }
}
