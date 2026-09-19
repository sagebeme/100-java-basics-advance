package com.learning.service;

import com.learning.model.EarningsRequest;
import com.learning.model.EvaluationMetrics;
import com.learning.model.PredictionResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Trains the real model against the actual bundled earnings-data.csv - no mocking of the ML
 * pipeline, since the whole point of this day is verifying a real trained model behaves sensibly.
 */
class EarningsPredictionServiceTest {

    private EarningsPredictionService service;

    @BeforeEach
    void setUp() {
        service = new EarningsPredictionService(new EarningsDataLoader(), new ModelEvaluator());
    }

    private EarningsRequest request(double experience, String education, String location, String industry) {
        EarningsRequest request = new EarningsRequest();
        request.setExperience(experience);
        request.setEducation(education);
        request.setLocation(location);
        request.setIndustry(industry);
        return request;
    }

    @Test
    void predictsAPlausiblePositiveEarningsFigure() {
        PredictionResult result = service.predict(request(5, "Bachelor", "Urban", "Technology"));

        assertTrue(result.predictedEarnings() > 0);
        assertEquals("1.0", result.modelVersion());
        assertTrue(result.confidence() >= 0 && result.confidence() <= 1);
    }

    @Test
    void moreExperienceProducesHigherPredictedEarningsAllElseEqual() {
        PredictionResult junior = service.predict(request(1, "Bachelor", "Urban", "Technology"));
        PredictionResult senior = service.predict(request(20, "Bachelor", "Urban", "Technology"));

        assertTrue(senior.predictedEarnings() > junior.predictedEarnings());
    }

    @Test
    void higherEducationTendsToPredictHigherEarningsAllElseEqual() {
        PredictionResult highSchool = service.predict(request(5, "HighSchool", "Urban", "Technology"));
        PredictionResult phd = service.predict(request(5, "PhD", "Urban", "Technology"));

        assertTrue(phd.predictedEarnings() > highSchool.predictedEarnings());
    }

    @Test
    void evaluationMetricsCoverTheWholeBundledDatasetAndShowRealSignal() {
        EvaluationMetrics metrics = service.getEvaluationMetrics();

        assertEquals(150, metrics.trainSize() + metrics.testSize());
        assertTrue(metrics.testSize() > 0);
        // The synthetic data has a real linear relationship with modest noise, so a correctly
        // trained model should explain a majority of the variance - this is a loose bound to
        // avoid flakiness while still catching a genuinely broken model (e.g. r2 near 0).
        assertTrue(metrics.r2() > 0.5, "Expected r2 > 0.5, was " + metrics.r2());
        assertTrue(metrics.mae() > 0);
        assertTrue(metrics.mse() > 0);
    }
}
