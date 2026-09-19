package com.learning.service;

import com.learning.model.EarningsRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * One-hot encodes the categorical fields (education, location, industry) using reference-category
 * encoding: each category list's first (alphabetically sorted) value is dropped from the encoded
 * vector rather than given its own column. This is the standard, textbook-correct way to encode
 * categoricals for linear regression - including a column for every category (the "dummy variable
 * trap") makes the design matrix's columns perfectly collinear with the bias column, since the
 * one-hot columns for any category always sum to 1.
 */
@Component
public class FeatureEncoder {

    private List<String> educationCategories;
    private List<String> locationCategories;
    private List<String> industryCategories;

    public void fit(List<EarningsRecord> data) {
        educationCategories = distinctSorted(data, EarningsRecord::education);
        locationCategories = distinctSorted(data, EarningsRecord::location);
        industryCategories = distinctSorted(data, EarningsRecord::industry);
    }

    public double[] encode(double experience, String education, String location, String industry) {
        requireFit();
        List<Double> features = new ArrayList<>();
        features.add(1.0); // bias
        features.add(experience);
        appendOneHot(features, educationCategories, education);
        appendOneHot(features, locationCategories, location);
        appendOneHot(features, industryCategories, industry);

        double[] vector = new double[features.size()];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = features.get(i);
        }
        return vector;
    }

    public double[] encode(EarningsRecord record) {
        return encode(record.experience(), record.education(), record.location(), record.industry());
    }

    public int featureCount() {
        requireFit();
        return 2 + (educationCategories.size() - 1) + (locationCategories.size() - 1) + (industryCategories.size() - 1);
    }

    public List<String> getEducationCategories() {
        return educationCategories;
    }

    public List<String> getLocationCategories() {
        return locationCategories;
    }

    public List<String> getIndustryCategories() {
        return industryCategories;
    }

    private void appendOneHot(List<Double> features, List<String> categories, String value) {
        for (int i = 1; i < categories.size(); i++) {
            features.add(categories.get(i).equals(value) ? 1.0 : 0.0);
        }
    }

    private List<String> distinctSorted(List<EarningsRecord> data, Function<EarningsRecord, String> extractor) {
        return data.stream().map(extractor).distinct().sorted().toList();
    }

    private void requireFit() {
        if (educationCategories == null) {
            throw new IllegalStateException("FeatureEncoder must be fit() before it can encode");
        }
    }
}
