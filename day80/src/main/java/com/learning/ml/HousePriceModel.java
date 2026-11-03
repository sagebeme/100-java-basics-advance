package com.learning.ml;

import com.learning.model.HouseData;
import com.learning.model.HouseFeatures;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Multiple linear regression trained via the normal equations (X^T X) w = X^T y, solved with
 * Gaussian elimination and partial pivoting. No external ML library is needed for a handful of
 * features and a small training set - this is the same math libraries like scikit-learn's
 * LinearRegression use internally for small, well-conditioned problems.
 */
@Component
public class HousePriceModel {

    private static final int FEATURE_COUNT = 5; // bias + squareFeet + bedrooms + bathrooms + ageYears

    /** How far outside the training data, as a share of each feature's range, still counts as "inside". */
    static final double RANGE_MARGIN = 0.10;

    private double[] weights;
    private double[] lowest;
    private double[] highest;

    public void trainModel(List<HouseData> trainingData) {
        if (trainingData.isEmpty()) {
            throw new IllegalArgumentException("Cannot train on an empty dataset");
        }

        int n = trainingData.size();
        double[][] x = new double[n][FEATURE_COUNT];
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = toRow(trainingData.get(i).features());
            y[i] = trainingData.get(i).price();
        }

        double[][] xtx = new double[FEATURE_COUNT][FEATURE_COUNT];
        double[] xty = new double[FEATURE_COUNT];
        for (int i = 0; i < FEATURE_COUNT; i++) {
            for (int j = 0; j < FEATURE_COUNT; j++) {
                double sum = 0;
                for (int row = 0; row < n; row++) {
                    sum += x[row][i] * x[row][j];
                }
                xtx[i][j] = sum;
            }
            double sum = 0;
            for (int row = 0; row < n; row++) {
                sum += x[row][i] * y[row];
            }
            xty[i] = sum;
        }

        this.weights = solve(xtx, xty);

        // Remember the range of every feature the model has seen. A straight line fitted to
        // houses of 1-40 years says nothing true about a 400-year-old one.
        lowest = new double[FEATURE_COUNT];
        highest = new double[FEATURE_COUNT];
        for (int i = 0; i < FEATURE_COUNT; i++) {
            lowest[i] = Double.POSITIVE_INFINITY;
            highest[i] = Double.NEGATIVE_INFINITY;
            for (double[] row : x) {
                lowest[i] = Math.min(lowest[i], row[i]);
                highest[i] = Math.max(highest[i], row[i]);
            }
        }
    }

    /**
     * True if every feature lies within the range the model was trained on, give or take
     * {@link #RANGE_MARGIN} of that range. Outside it the model is guessing, and a straight
     * line can even predict a negative price.
     */
    public boolean isWithinTrainingRange(HouseFeatures features) {
        if (weights == null) {
            throw new IllegalStateException("Model must be trained before it can predict");
        }
        double[] row = toRow(features);
        for (int i = 1; i < FEATURE_COUNT; i++) { // index 0 is the constant bias term
            double margin = (highest[i] - lowest[i]) * RANGE_MARGIN;
            if (row[i] < lowest[i] - margin || row[i] > highest[i] + margin) {
                return false;
            }
        }
        return true;
    }

    /** A sentence describing the houses the model learned from, for error messages. */
    public String describeTrainingRange() {
        if (weights == null) {
            throw new IllegalStateException("Model must be trained before it can predict");
        }
        return String.format("This model learned from houses of %,.0f-%,.0f sq ft, %.0f-%.0f bedrooms, "
                        + "%.0f-%.0f bathrooms and %.0f-%.0f years old, so it can't price this one.",
                lowest[1], highest[1], lowest[2], highest[2], lowest[3], highest[3], lowest[4], highest[4]);
    }

    public double predictPrice(HouseFeatures features) {
        if (weights == null) {
            throw new IllegalStateException("Model must be trained before it can predict");
        }
        double[] row = toRow(features);
        double total = 0;
        for (int i = 0; i < FEATURE_COUNT; i++) {
            total += weights[i] * row[i];
        }
        return total;
    }

    private static double[] toRow(HouseFeatures features) {
        return new double[]{1.0, features.squareFeet(), features.bedrooms(), features.bathrooms(), features.ageYears()};
    }

    /**
     * Solves A x = b via Gaussian elimination with partial pivoting. A is modified in place on a
     * defensive copy; the original matrices passed in by the caller are left untouched.
     */
    private static double[] solve(double[][] a, double[] b) {
        int n = b.length;
        double[][] matrix = new double[n][n];
        double[] vector = new double[n];
        for (int i = 0; i < n; i++) {
            matrix[i] = a[i].clone();
        }
        System.arraycopy(b, 0, vector, 0, n);
        double largest = 0;
        for (double[] row : a) {
            for (double value : row) {
                largest = Math.max(largest, Math.abs(value));
            }
        }

        for (int pivot = 0; pivot < n; pivot++) {
            int maxRow = pivot;
            for (int row = pivot + 1; row < n; row++) {
                if (Math.abs(matrix[row][pivot]) > Math.abs(matrix[maxRow][pivot])) {
                    maxRow = row;
                }
            }
            double[] tempRow = matrix[pivot];
            matrix[pivot] = matrix[maxRow];
            matrix[maxRow] = tempRow;
            double tempVal = vector[pivot];
            vector[pivot] = vector[maxRow];
            vector[maxRow] = tempVal;

            if (Math.abs(matrix[pivot][pivot]) < 1e-9 * largest) {
                // No unique answer: one feature is a mix of the others (every house is 10 years
                // old, or bathrooms always equal bedrooms), so their effects can't be told apart.
                throw new IllegalArgumentException(
                        "The training data can't separate the features' effects: some features move together");
            }

            for (int row = pivot + 1; row < n; row++) {
                double factor = matrix[row][pivot] / matrix[pivot][pivot];
                for (int col = pivot; col < n; col++) {
                    matrix[row][col] -= factor * matrix[pivot][col];
                }
                vector[row] -= factor * vector[pivot];
            }
        }

        double[] solution = new double[n];
        for (int row = n - 1; row >= 0; row--) {
            double sum = vector[row];
            for (int col = row + 1; col < n; col++) {
                sum -= matrix[row][col] * solution[col];
            }
            solution[row] = sum / matrix[row][row];
        }
        return solution;
    }
}
