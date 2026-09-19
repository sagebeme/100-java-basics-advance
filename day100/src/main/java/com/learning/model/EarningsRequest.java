package com.learning.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Used for both the REST API's JSON body and the web form's binding target - one shape for the
 * same underlying request.
 */
public class EarningsRequest {

    @PositiveOrZero
    private double experience;

    @NotBlank
    private String education = "Bachelor";

    @NotBlank
    private String location = "Urban";

    @NotBlank
    private String industry = "Technology";

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
