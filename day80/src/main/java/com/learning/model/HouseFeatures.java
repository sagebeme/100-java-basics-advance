package com.learning.model;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record HouseFeatures(
        @Positive double squareFeet,
        @PositiveOrZero int bedrooms,
        @PositiveOrZero int bathrooms,
        @PositiveOrZero double ageYears) {
}
