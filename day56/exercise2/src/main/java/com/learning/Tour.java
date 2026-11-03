package com.learning;

// One day trip. Already written.
public class Tour {

    private final String name;
    private final String place;
    private final int shillings;

    public Tour(String name, String place, int shillings) {
        this.name = name;
        this.place = place;
        this.shillings = shillings;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public int getShillings() {
        return shillings;
    }
}
