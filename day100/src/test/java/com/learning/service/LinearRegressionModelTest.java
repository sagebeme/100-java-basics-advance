package com.learning.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinearRegressionModelTest {

    @Test
    void recoversExactWeightsForANoiseFreeLinearDataset() {
        // y = 10 + 2*x1 + 3*x2, features are [bias, x1, x2]
        double[][] design = {
                {1, 1, 1}, // y = 10+2+3 = 15
                {1, 2, 1}, // y = 10+4+3 = 17
                {1, 1, 2}, // y = 10+2+6 = 18
                {1, 3, 2}, // y = 10+6+6 = 22
                {1, 4, 5}, // y = 10+8+15 = 33
        };
        double[] targets = {15, 17, 18, 22, 33};

        LinearRegressionModel model = new LinearRegressionModel();
        model.train(design, targets);

        assertEquals(15.0, model.predict(new double[]{1, 1, 1}), 0.01);
        assertEquals(28.0, model.predict(new double[]{1, 3, 4}), 0.01); // 10+6+12=28, unseen combination
    }

    @Test
    void predictingBeforeTrainingThrows() {
        LinearRegressionModel model = new LinearRegressionModel();
        assertThrows(IllegalStateException.class, () -> model.predict(new double[]{1, 2, 3}));
    }
}
