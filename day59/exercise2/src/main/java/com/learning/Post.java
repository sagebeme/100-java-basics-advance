package com.learning;

// One story on the editor's list. Already written.
public class Post {

    private final long id;
    private final String title;
    private final String section;

    public Post(long id, String title, String section) {
        this.id = id;
        this.title = title;
        this.section = section;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    // "News", "Sport", "Business" or "Health".
    public String getSection() {
        return section;
    }
}
