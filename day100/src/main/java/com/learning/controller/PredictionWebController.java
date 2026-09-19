package com.learning.controller;

import com.learning.model.EarningsRequest;
import com.learning.service.EarningsPredictionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PredictionWebController {

    private final EarningsPredictionService predictionService;

    public PredictionWebController(EarningsPredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("earningsRequest", new EarningsRequest());
        addFormAttributes(model);
        return "prediction";
    }

    @PostMapping("/predict")
    public String predict(@Valid @ModelAttribute("earningsRequest") EarningsRequest request, BindingResult bindingResult, Model model) {
        addFormAttributes(model);
        if (bindingResult.hasErrors()) {
            return "prediction";
        }
        model.addAttribute("result", predictionService.predict(request));
        return "prediction";
    }

    private void addFormAttributes(Model model) {
        model.addAttribute("educationOptions", predictionService.getEncoder().getEducationCategories());
        model.addAttribute("locationOptions", predictionService.getEncoder().getLocationCategories());
        model.addAttribute("industryOptions", predictionService.getEncoder().getIndustryCategories());
        model.addAttribute("metrics", predictionService.getEvaluationMetrics());
    }
}
