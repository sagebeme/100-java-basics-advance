package com.learning.controller;

import com.learning.model.EarningsRequest;
import com.learning.model.PredictionResult;
import com.learning.service.EarningsPredictionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/predict")
public class PredictionRestController {

    private final EarningsPredictionService predictionService;

    public PredictionRestController(EarningsPredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping
    public ResponseEntity<PredictionResult> predict(@Valid @RequestBody EarningsRequest request) {
        return ResponseEntity.ok(predictionService.predict(request));
    }
}
