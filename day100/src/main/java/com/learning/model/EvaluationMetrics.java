package com.learning.model;

public record EvaluationMetrics(double r2, double mse, double mae, int trainSize, int testSize) {
}
