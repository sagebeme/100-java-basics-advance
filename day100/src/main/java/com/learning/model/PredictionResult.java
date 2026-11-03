package com.learning.model;

public record PredictionResult(double predictedEarnings, double modelR2, String modelVersion) {
}
