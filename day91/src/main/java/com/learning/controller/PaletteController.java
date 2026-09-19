package com.learning.controller;

import com.learning.model.RgbColor;
import com.learning.service.PaletteExporter;
import com.learning.service.PaletteGenerator;
import com.learning.service.PaletteScheme;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PaletteController {

    private final PaletteGenerator paletteGenerator;
    private final PaletteExporter paletteExporter;

    public PaletteController(PaletteGenerator paletteGenerator, PaletteExporter paletteExporter) {
        this.paletteGenerator = paletteGenerator;
        this.paletteExporter = paletteExporter;
    }

    @GetMapping("/")
    public String show(
            @RequestParam(defaultValue = "#3B6EA5") String baseColor,
            @RequestParam(defaultValue = "COMPLEMENTARY") PaletteScheme scheme,
            Model model) {

        RgbColor base = RgbColor.fromHex(baseColor);
        List<RgbColor> palette = paletteGenerator.generate(base, scheme);

        model.addAttribute("baseColor", base.toHex());
        model.addAttribute("scheme", scheme);
        model.addAttribute("schemes", PaletteScheme.values());
        model.addAttribute("palette", palette);
        return "index";
    }

    @GetMapping(value = "/export", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public ResponseEntity<String> export(
            @RequestParam String baseColor,
            @RequestParam PaletteScheme scheme,
            @RequestParam(defaultValue = "json") String format) {

        RgbColor base = RgbColor.fromHex(baseColor);
        List<RgbColor> palette = paletteGenerator.generate(base, scheme);

        String body;
        String filename;
        MediaType contentType;
        if (format.equalsIgnoreCase("css")) {
            body = paletteExporter.toCss(palette);
            filename = "palette.css";
            contentType = MediaType.parseMediaType("text/css");
        } else {
            body = paletteExporter.toJson(palette);
            filename = "palette.json";
            contentType = MediaType.APPLICATION_JSON;
        }

        return ResponseEntity.ok()
                .contentType(contentType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(body);
    }
}
