package com.learning.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

// TODO: put the rules from the README on the fields, each with its message.
public class Member {

    private Long id;

    private String name;

    private String phone;

    private int monthlyShillings;

    public Member() {
    }

    public Member(Long id, String name, String phone, int monthlyShillings) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.monthlyShillings = monthlyShillings;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMonthlyShillings() {
        return monthlyShillings;
    }

    public void setMonthlyShillings(int monthlyShillings) {
        this.monthlyShillings = monthlyShillings;
    }
}
