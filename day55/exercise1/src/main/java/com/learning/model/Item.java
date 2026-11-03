package com.learning.model;

// One thing on Mama Njeri's shelves. Already written.
public class Item {

    private Long id;
    private String name;
    private int shillings;

    public Item() {
    }

    public Item(Long id, String name, int shillings) {
        this.id = id;
        this.name = name;
        this.shillings = shillings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShillings() {
        return shillings;
    }

    public void setShillings(int shillings) {
        this.shillings = shillings;
    }
}
