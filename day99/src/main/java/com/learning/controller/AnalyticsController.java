package com.learning.controller;

import com.learning.model.SalesRecord;
import com.learning.service.AdvancedStatistics;
import com.learning.service.AnalyticsService;
import com.learning.service.InsightGenerator;
import com.learning.service.ReportGenerator;
import com.learning.service.RevenueForecaster;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class AnalyticsController {

    private final AnalyticsService analyticsService;
    private final AdvancedStatistics statistics;
    private final RevenueForecaster forecaster;
    private final InsightGenerator insightGenerator;
    private final ReportGenerator reportGenerator;

    public AnalyticsController(AnalyticsService analyticsService, AdvancedStatistics statistics,
                                RevenueForecaster forecaster, InsightGenerator insightGenerator,
                                ReportGenerator reportGenerator) {
        this.analyticsService = analyticsService;
        this.statistics = statistics;
        this.forecaster = forecaster;
        this.insightGenerator = insightGenerator;
        this.reportGenerator = reportGenerator;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        List<SalesRecord> records = analyticsService.getRecords();
        Map<String, Double> monthlyTotals = statistics.monthlyTotals(records);
        Map<String, Double> forecast = forecaster.forecast(monthlyTotals, 3);

        model.addAttribute("monthlyTotals", monthlyTotals);
        model.addAttribute("forecast", forecast);
        model.addAttribute("insights", insightGenerator.generate(records));
        model.addAttribute("totalRevenue", records.stream().mapToDouble(SalesRecord::amount).sum());
        return "dashboard";
    }

    @GetMapping("/report")
    @ResponseBody
    public ResponseEntity<byte[]> downloadReport() throws IOException {
        byte[] pdf = reportGenerator.generatePdfReport(analyticsService.getRecords());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"analytics-report.pdf\"")
                .body(pdf);
    }
}
