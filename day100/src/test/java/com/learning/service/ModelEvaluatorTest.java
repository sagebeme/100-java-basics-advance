package com.learning.service;

import com.learning.model.EarningsRecord;
import com.learning.model.EvaluationMetrics;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ModelEvaluatorTest {

    private final ModelEvaluator evaluator = new ModelEvaluator();

    private List<EarningsRecord> tenRecords() {
        List<EarningsRecord> records = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            records.add(new EarningsRecord(i, "Bachelor", "Urban", "Technology", 1000 + 500.0 * i));
        }
        return records;
    }

    @Test
    void splitProducesTheExpectedTrainAndTestSizes() {
        ModelEvaluator.Split split = evaluator.trainTestSplit(tenRecords(), 0.8, 42L);

        assertEquals(8, split.train().size());
        assertEquals(2, split.test().size());
    }

    @Test
    void splitCoversEveryOriginalRecordExactlyOnce() {
        List<EarningsRecord> original = tenRecords();
        ModelEvaluator.Split split = evaluator.trainTestSplit(original, 0.8, 42L);

        Set<EarningsRecord> combined = new HashSet<>(split.train());
        combined.addAll(split.test());
        assertEquals(new HashSet<>(original), combined);
        assertEquals(original.size(), split.train().size() + split.test().size());
    }

    @Test
    void splitIsDeterministicForTheSameSeed() {
        List<EarningsRecord> original = tenRecords();
        ModelEvaluator.Split first = evaluator.trainTestSplit(original, 0.8, 42L);
        ModelEvaluator.Split second = evaluator.trainTestSplit(original, 0.8, 42L);

        assertEquals(first.train(), second.train());
        assertEquals(first.test(), second.test());
    }

    @Test
    void evaluatingAPerfectlyFittedModelReportsR2OfOneAndZeroError() {
        List<EarningsRecord> records = tenRecords(); // earnings = 1000 + 500*experience, no noise, single category
        FeatureEncoder encoder = new FeatureEncoder();
        encoder.fit(records);

        LinearRegressionModel model = new LinearRegressionModel();
        double[][] design = new double[records.size()][];
        double[] targets = new double[records.size()];
        for (int i = 0; i < records.size(); i++) {
            design[i] = encoder.encode(records.get(i));
            targets[i] = records.get(i).earnings();
        }
        model.train(design, targets);

        EvaluationMetrics metrics = evaluator.evaluate(model, encoder, records, records.size());

        assertEquals(1.0, metrics.r2(), 0.0001);
        assertEquals(0.0, metrics.mse(), 0.01);
        assertEquals(0.0, metrics.mae(), 0.01);
        assertEquals(records.size(), metrics.testSize());
    }
}
