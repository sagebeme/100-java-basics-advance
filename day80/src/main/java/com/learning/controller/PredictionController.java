package com.learning.controller;

import com.learning.ml.HousePriceModel;
import com.learning.model.HouseFeatures;
import com.learning.model.Prediction;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/predict")
public class PredictionController {

    private final HousePriceModel housePriceModel;

    public PredictionController(HousePriceModel housePriceModel) {
        this.housePriceModel = housePriceModel;
    }

    @PostMapping("/house-price")
    public ResponseEntity<?> predictPrice(@Valid @RequestBody HouseFeatures features) {
        if (!housePriceModel.isWithinTrainingRange(features)) {
            ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                    HttpStatus.UNPROCESSABLE_ENTITY, housePriceModel.describeTrainingRange());
            return ResponseEntity.unprocessableEntity().body(problem);
        }
        double price = housePriceModel.predictPrice(features);
        return ResponseEntity.ok(new Prediction(price));
    }
}
