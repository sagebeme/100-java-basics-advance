package com.learning.maktaba;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class BorrowForm {

    @NotBlank(message = "Enter your library card number.")
    @Pattern(regexp = "M-\\d{4}", message = "A library card number looks like M-1234.")
    private String memberNumber;

    @Min(value = 7, message = "Borrow for 7 to 21 days.")
    @Max(value = 21, message = "Borrow for 7 to 21 days.")
    private int days = 14;

    public String getMemberNumber() { return memberNumber; }
    public void setMemberNumber(String memberNumber) { this.memberNumber = memberNumber == null ? null : memberNumber.trim(); }
    public int getDays() { return days; }
    public void setDays(int days) { this.days = days; }
}
