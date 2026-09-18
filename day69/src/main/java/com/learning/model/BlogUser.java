package com.learning.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// "user" is reserved in H2 - see the Day 60 note - hence BlogUser / blog_user rather than User.
@Entity
@Table(name = "blog_user")
public class BlogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    public BlogUser() {
    }

    public BlogUser(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Post> getPosts() { return posts; }
}
