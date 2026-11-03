package com.learning;

// One line on a member's statement. Already written. Money out is a negative amount.
public class Entry {

    private final String date;
    private final String what;
    private final int shillings;

    public Entry(String date, String what, int shillings) {
        this.date = date;
        this.what = what;
        this.shillings = shillings;
    }

    public String getDate() {
        return date;
    }

    public String getWhat() {
        return what;
    }

    public int getShillings() {
        return shillings;
    }
}
