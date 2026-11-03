package com.learning;

import java.util.List;

// A Sacco member. Already written.
public class Member {

    private final int id;
    private final String name;
    private final String role;
    private final int savings;
    private final int monthsSaving;
    private final List<Entry> statement;

    public Member(int id, String name, String role, int savings, int monthsSaving, List<Entry> statement) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.savings = savings;
        this.monthsSaving = monthsSaving;
        this.statement = statement;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // "CHAIR", "TREASURER" or "MEMBER".
    public String getRole() {
        return role;
    }

    public int getSavings() {
        return savings;
    }

    public int getMonthsSaving() {
        return monthsSaving;
    }

    // Members can borrow three times their savings, once they've saved for six months.
    public int getLoanLimit() {
        return savings * 3;
    }

    public List<Entry> getStatement() {
        return statement;
    }
}
