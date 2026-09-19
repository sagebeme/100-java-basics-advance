package com.learning.service;

/**
 * Multiple linear regression trained via the normal equations (X^T X) w = X^T y, solved with
 * Gaussian elimination and partial pivoting - the same approach as day80's house price predictor,
 * generalized here to however many features the caller's design matrix has (one-hot encoded
 * categoricals plus numeric features).
 */
public class LinearRegressionModel {

    private double[] weights;

    public void train(double[][] designMatrix, double[] targets) {
        int featureCount = designMatrix[0].length;
        double[][] xtx = new double[featureCount][featureCount];
        double[] xty = new double[featureCount];

        for (int i = 0; i < featureCount; i++) {
            for (int j = 0; j < featureCount; j++) {
                double sum = 0;
                for (double[] row : designMatrix) {
                    sum += row[i] * row[j];
                }
                xtx[i][j] = sum;
            }
            double sum = 0;
            for (int row = 0; row < designMatrix.length; row++) {
                sum += designMatrix[row][i] * targets[row];
            }
            xty[i] = sum;
        }

        this.weights = solve(xtx, xty);
    }

    public double predict(double[] featureVector) {
        if (weights == null) {
            throw new IllegalStateException("Model must be trained before it can predict");
        }
        double total = 0;
        for (int i = 0; i < weights.length; i++) {
            total += weights[i] * featureVector[i];
        }
        return total;
    }

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
