package com.learning.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

// The fields a PATCH may change. null means "not sent, leave it as it is".
public record ProductPatch(
        @Pattern(regexp = ".*\\S.*", message = "name can't be blank") String name,
        @Positive(message = "price must be positive") Double price) {
}
