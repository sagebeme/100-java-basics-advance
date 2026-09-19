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

    private double[] weights;

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
