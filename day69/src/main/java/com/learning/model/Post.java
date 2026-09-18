package com.learning.model;

import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private BlogUser author;

    public Post() {
    }

    public Post(String title, BlogUser author) {
        this.title = title;
        this.author = author;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public BlogUser getAuthor() { return author; }
    public void setAuthor(BlogUser author) { this.author = author; }
}
