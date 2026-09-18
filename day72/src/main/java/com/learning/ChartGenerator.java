package com.learning;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ChartGenerator {

    public JFreeChart createBarChart(String title, Map<String, Double> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach((category, value) -> dataset.addValue(value, "Sales", category));
        return ChartFactory.createBarChart(title, "Category", "Value", dataset);
    }

    public JFreeChart createLineChart(String title, Map<String, Double> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach((category, value) -> dataset.addValue(value, "Trend", category));
        return ChartFactory.createLineChart(title, "Period", "Value", dataset);
    }

    public JFreeChart createPieChart(String title, Map<String, Double> data) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        data.forEach(dataset::setValue);
        return ChartFactory.createPieChart(title, dataset);
    }

    /**
     * Filters a data map down to entries at or above a threshold, for the dashboard's
     * "data filtering" requirement.
     */
    public Map<String, Double> filterAbove(Map<String, Double> data, double threshold) {
        return data.entrySet().stream()
                .filter(e -> e.getValue() >= threshold)
                .collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Exports a chart to a real PNG file, for the dashboard's "export capabilities" requirement.
     */
    public File exportToPng(JFreeChart chart, File file, int width, int height) throws IOException {
        ChartUtils.saveChartAsPNG(file, chart, width, height);
        return file;
    }

    public static void main(String[] args) throws IOException {
        Map<String, Double> sales = Map.of(
                "Jan", 1000.0, "Feb", 1200.0, "Mar", 900.0,
                "Apr", 1500.0, "May", 1800.0, "Jun", 2000.0
        );

        ChartGenerator generator = new ChartGenerator();
        JFreeChart barChart = generator.createBarChart("Monthly Sales", sales);
        File output = generator.exportToPng(barChart, new File("sales-chart.png"), 800, 600);

        System.out.println("Chart saved to " + output.getAbsolutePath() + " (" + output.length() + " bytes)");
    }
}
