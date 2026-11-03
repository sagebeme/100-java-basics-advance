package com.learning;

// Someone invited to the harambee. Already written.
public class Guest {

    private final int id;
    private final String name;
    private final int pledgedShillings;

    public Guest(int id, String name, int pledgedShillings) {
        this.id = id;
        this.name = name;
        this.pledgedShillings = pledgedShillings;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPledgedShillings() {
        return pledgedShillings;
    }
}
