package com.learning.controller;

import com.learning.ml.HousePriceModel;
import com.learning.model.HouseFeaturesForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    private final HousePriceModel housePriceModel;

    public WebController(HousePriceModel housePriceModel) {
        this.housePriceModel = housePriceModel;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("houseFeaturesForm", new HouseFeaturesForm());
        return "predict-form";
    }

    @PostMapping("/predict")
    public String predict(@Valid @ModelAttribute HouseFeaturesForm houseFeaturesForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "predict-form";
        }
        double price = housePriceModel.predictPrice(houseFeaturesForm.toFeatures());
        model.addAttribute("predictedPrice", price);
        return "predict-form";
    }
}
