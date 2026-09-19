package com.learning.model;

public record PredictionResult(double predictedEarnings, double confidence, String modelVersion) {
}
