package com.learning.service;

import com.learning.model.EarningsRecord;
import com.learning.model.EarningsRequest;
import com.learning.model.EvaluationMetrics;
import com.learning.model.PredictionResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

@Service
public class EarningsPredictionService {

    private static final String MODEL_VERSION = "1.0";
    private static final double TRAIN_FRACTION = 0.8;
    private static final long SPLIT_SEED = 42L;

    private final FeatureEncoder encoder = new FeatureEncoder();
    private final LinearRegressionModel productionModel = new LinearRegressionModel();
    private final EvaluationMetrics evaluationMetrics;

    public EarningsPredictionService(EarningsDataLoader dataLoader, ModelEvaluator modelEvaluator) {
        List<EarningsRecord> allData;
        try {
            allData = dataLoader.load("earnings-data.csv");
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load earnings-data.csv", e);
        }

        // Fit the encoder on the full dataset so the category-to-column mapping is stable
        // regardless of which rows end up in the train vs. test split.
        encoder.fit(allData);

        ModelEvaluator.Split split = modelEvaluator.trainTestSplit(allData, TRAIN_FRACTION, SPLIT_SEED);

        // A model trained only on the training split, purely to report honest held-out metrics.
        LinearRegressionModel evaluationModel = new LinearRegressionModel();
        evaluationModel.train(toDesignMatrix(split.train()), toTargets(split.train()));
        this.evaluationMetrics = modelEvaluator.evaluate(evaluationModel, encoder, split.test(), split.train().size());

        // The model actually used for predictions is trained on all available data, since once
        // evaluation is done there's no reason to withhold real training signal from it.
        productionModel.train(toDesignMatrix(allData), toTargets(allData));
    }

    public PredictionResult predict(EarningsRequest request) {
        double[] features = encoder.encode(request.getExperience(), request.getEducation(), request.getLocation(), request.getIndustry());
        double predicted = productionModel.predict(features);
        double confidence = Math.max(0.0, Math.min(1.0, evaluationMetrics.r2()));
        return new PredictionResult(predicted, confidence, MODEL_VERSION);
    }

    public EvaluationMetrics getEvaluationMetrics() {
        return evaluationMetrics;
    }

    public FeatureEncoder getEncoder() {
        return encoder;
    }

    private double[][] toDesignMatrix(List<EarningsRecord> records) {
        double[][] matrix = new double[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            matrix[i] = encoder.encode(records.get(i));
        }
        return matrix;
    }

    private double[] toTargets(List<EarningsRecord> records) {
        double[] targets = new double[records.size()];
        for (int i = 0; i < records.size(); i++) {
            targets[i] = records.get(i).earnings();
        }
        return targets;
    }
}
