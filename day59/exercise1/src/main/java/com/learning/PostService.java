package com.learning;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();

    public PostService() {
        posts.add(new Post(1L, "Learn Java", "Start with the basics: variables, loops, methods."));
        posts.add(new Post(2L, "Build an API", "Spring Boot makes REST endpoints straightforward."));
        posts.add(new Post(3L, "Style with Bootstrap", "A CDN link and a few classes go a long way."));
    }

    public List<Post> getAllPosts() {
        return posts;
    }

    public Post getPostById(Long id) {
        return posts.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
