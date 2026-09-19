package com.learning.model;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * A plain, mutable stand-in for {@link HouseFeatures} used only for Thymeleaf form binding, which
 * needs JavaBean-style getters/setters rather than a record's accessor methods.
 */
public class HouseFeaturesForm {

    @Positive
    private double squareFeet = 1500;

    @PositiveOrZero
    private int bedrooms = 3;

    @PositiveOrZero
    private int bathrooms = 2;

    @PositiveOrZero
    private double ageYears = 10;

    public double getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(double squareFeet) {
        this.squareFeet = squareFeet;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public double getAgeYears() {
        return ageYears;
    }

    public void setAgeYears(double ageYears) {
        this.ageYears = ageYears;
    }

    public HouseFeatures toFeatures() {
        return new HouseFeatures(squareFeet, bedrooms, bathrooms, ageYears);
    }
}
