package com.learning.service;

import com.learning.model.EarningsRecord;
import com.learning.model.EvaluationMetrics;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class ModelEvaluator {

    public record Split(List<EarningsRecord> train, List<EarningsRecord> test) {
    }

    /** Deterministic (seeded) shuffle-and-split, so training is reproducible run to run. */
    public Split trainTestSplit(List<EarningsRecord> data, double trainFraction, long seed) {
        List<EarningsRecord> shuffled = new ArrayList<>(data);
        Collections.shuffle(shuffled, new Random(seed));
        int trainSize = (int) Math.round(shuffled.size() * trainFraction);
        return new Split(new ArrayList<>(shuffled.subList(0, trainSize)), new ArrayList<>(shuffled.subList(trainSize, shuffled.size())));
    }

    public EvaluationMetrics evaluate(LinearRegressionModel model, FeatureEncoder encoder, List<EarningsRecord> testData, int trainSize) {
        int n = testData.size();
        double[] actual = new double[n];
        double[] predicted = new double[n];
        for (int i = 0; i < n; i++) {
            EarningsRecord record = testData.get(i);
            actual[i] = record.earnings();
            predicted[i] = model.predict(encoder.encode(record));
        }

        double meanActual = 0;
        for (double v : actual) {
            meanActual += v;
        }
        meanActual /= n;

        double sumSquaredResiduals = 0;
        double sumSquaredTotal = 0;
        double sumAbsoluteError = 0;
        for (int i = 0; i < n; i++) {
            double error = actual[i] - predicted[i];
            sumSquaredResiduals += error * error;
            sumSquaredTotal += (actual[i] - meanActual) * (actual[i] - meanActual);
            sumAbsoluteError += Math.abs(error);
        }

        double r2 = sumSquaredTotal == 0 ? 1.0 : 1.0 - (sumSquaredResiduals / sumSquaredTotal);
        double mse = sumSquaredResiduals / n;
        double mae = sumAbsoluteError / n;

        return new EvaluationMetrics(r2, mse, mae, trainSize, n);
    }
}
