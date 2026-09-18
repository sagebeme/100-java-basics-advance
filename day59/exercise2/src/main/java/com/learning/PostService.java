package com.learning;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();

    public PostService() {
        posts.add(new Post(1L, "Learn Java", "Java"));
        posts.add(new Post(2L, "Build an API", "Spring"));
        posts.add(new Post(3L, "Style with Bootstrap", "Frontend"));
        posts.add(new Post(4L, "Write JUnit Tests", "Testing"));
        posts.add(new Post(5L, "Deploy to the Cloud", "DevOps"));
        posts.add(new Post(6L, "Database Basics", "Java"));
        posts.add(new Post(7L, "REST Best Practices", "Spring"));
    }

    public int totalCount() {
        return posts.size();
    }

    public int totalPages(int pageSize) {
        return (int) Math.ceil((double) posts.size() / pageSize);
    }

    /**
     * @param page 1-based page number.
     */
    public List<Post> getPage(int page, int pageSize) {
        int fromIndex = (page - 1) * pageSize;
        if (fromIndex >= posts.size() || fromIndex < 0) {
            return List.of();
        }
        int toIndex = Math.min(fromIndex + pageSize, posts.size());
        return posts.subList(fromIndex, toIndex);
    }

    public boolean deletePost(Long id) {
        return posts.removeIf(p -> p.getId().equals(id));
    }

    public List<Post> getAllPosts() {
        return Collections.unmodifiableList(posts);
    }
}
