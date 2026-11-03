package com.learning.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Member {

    private Long id;

    @NotBlank(message = "Type the member's name")
    private String name;

    @NotNull(message = "A phone number is needed for M-Pesa")
    @Pattern(regexp = "0[17]\\d{8}", message = "Use a phone like 0712345678")
    private String phone;

    @Min(value = 100, message = "The smallest monthly contribution is KES 100")
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
