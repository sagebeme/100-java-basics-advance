package com.learning.service;

import com.learning.model.EarningsRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeatureEncoderTest {

    private FeatureEncoder encoder;

    @BeforeEach
    void setUp() {
        encoder = new FeatureEncoder();
        encoder.fit(List.of(
                new EarningsRecord(5, "Bachelor", "Urban", "Technology", 80000),
                new EarningsRecord(10, "Master", "Rural", "Finance", 95000),
                new EarningsRecord(2, "HighSchool", "Suburban", "Retail", 40000)));
    }

    @Test
    void categoriesAreSortedAlphabeticallyWithTheFirstAsReference() {
        // Bachelor, HighSchool, Master sorted alphabetically -> Bachelor is the reference category.
        assertEquals(List.of("Bachelor", "HighSchool", "Master"), encoder.getEducationCategories());
    }

    @Test
    void encodingProducesTheExpectedVectorLength() {
        // bias(1) + experience(1) + education(3-1) + location(3-1) + industry(3-1) = 8
        assertEquals(8, encoder.featureCount());
        assertEquals(8, encoder.encode(5, "Bachelor", "Urban", "Technology").length);
    }

    @Test
    void theReferenceCategoryGetsAllZeroOneHotBits() {
        double[] vector = encoder.encode(5, "Bachelor", "Rural", "Retail");
        // bias, experience, then education one-hot (HighSchool, Master), location one-hot, industry one-hot
        // Bachelor is the reference for education, so both education bits should be 0.
        assertEquals(1.0, vector[0]); // bias
        assertEquals(5.0, vector[1]); // experience
        assertEquals(0.0, vector[2]); // HighSchool bit
        assertEquals(0.0, vector[3]); // Master bit
    }

    @Test
    void aNonReferenceCategoryGetsExactlyOneMatchingBitSet() {
        double[] vector = encoder.encode(5, "Master", "Rural", "Retail");
        assertEquals(0.0, vector[2]); // HighSchool bit
        assertEquals(1.0, vector[3]); // Master bit
    }

    @Test
    void encodingBeforeFittingThrows() {
        FeatureEncoder unfit = new FeatureEncoder();
        assertThrows(IllegalStateException.class, () -> unfit.encode(5, "Bachelor", "Urban", "Technology"));
    }

    @Test
    void anUnseenCategoryFallsBackToAllZeroBitsLikeTheReferenceCategory() {
        double[] knownReference = encoder.encode(5, "Bachelor", "Urban", "Technology");
        double[] unseenCategory = encoder.encode(5, "Doctorate", "Urban", "Technology");

        assertArrayEquals(knownReference, unseenCategory);
    }
}
