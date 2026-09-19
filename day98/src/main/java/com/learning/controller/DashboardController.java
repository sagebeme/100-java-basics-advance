package com.learning.controller;

import com.learning.model.SalesRecord;
import com.learning.service.DashboardExporter;
import com.learning.service.DashboardService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;
    private final DashboardExporter dashboardExporter;

    public DashboardController(DashboardService dashboardService, DashboardExporter dashboardExporter) {
        this.dashboardService = dashboardService;
        this.dashboardExporter = dashboardExporter;
    }

    @GetMapping("/")
    public String dashboard(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String region,
            Model model) {

        List<SalesRecord> filtered = dashboardService.filter(category, region);

        model.addAttribute("category", category);
        model.addAttribute("region", region);
        model.addAttribute("categories", dashboardService.distinctCategories());
        model.addAttribute("regions", dashboardService.distinctRegions());
        model.addAttribute("totalRevenue", dashboardService.totalRevenue(filtered));
        model.addAttribute("recordCount", filtered.size());
        model.addAttribute("revenueByCategory", dashboardService.revenueByCategory(filtered));
        model.addAttribute("revenueByRegion", dashboardService.revenueByRegion(filtered));
        model.addAttribute("revenueByMonth", dashboardService.revenueByMonth(filtered));
        model.addAttribute("lastUpdated", LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        return "dashboard";
    }

    @GetMapping(value = "/export", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public ResponseEntity<String> export(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String region,
            @RequestParam(defaultValue = "csv") String format) {

        List<SalesRecord> filtered = dashboardService.filter(category, region);

        String body;
        String filename;
        MediaType contentType;
        if (format.equalsIgnoreCase("json")) {
            body = dashboardExporter.toJson(filtered);
            filename = "sales-data.json";
            contentType = MediaType.APPLICATION_JSON;
        } else {
            body = dashboardExporter.toCsv(filtered);
            filename = "sales-data.csv";
            contentType = MediaType.parseMediaType("text/csv");
        }

        return ResponseEntity.ok()
                .contentType(contentType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(body);
    }
}
