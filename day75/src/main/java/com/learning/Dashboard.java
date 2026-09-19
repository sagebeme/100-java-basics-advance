package com.learning;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A dashboard that holds one dataset and can render it as several chart types, refresh itself
 * against a filtered view of the data, and export every chart it currently holds in one call -
 * the "comprehensive dashboard" this day asks for, one step up from a single chart.
 */
public class Dashboard {

    private Map<String, Double> data;

    public Dashboard(Map<String, Double> data) {
        this.data = data;
    }

    public Map<String, Double> getData() {
        return data;
    }

    /**
     * The "interactive" part in a backend context: the dashboard can be re-pointed at a
     * filtered slice of the data and every chart type below reflects that new view.
     */
    public void filterAbove(double threshold) {
        Map<String, Double> filtered = new LinkedHashMap<>();
        data.forEach((k, v) -> { if (v >= threshold) filtered.put(k, v); });
        this.data = filtered;
    }

    public JFreeChart bar(String title) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach((k, v) -> dataset.addValue(v, "Series", k));
        return ChartFactory.createBarChart(title, "Category", "Value", dataset);
    }

    public JFreeChart line(String title) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach((k, v) -> dataset.addValue(v, "Series", k));
        return ChartFactory.createLineChart(title, "Category", "Value", dataset);
    }

    public JFreeChart pie(String title) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        data.forEach(dataset::setValue);
        return ChartFactory.createPieChart(title, dataset);
    }

    public JFreeChart area(String title) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach((k, v) -> dataset.addValue(v, "Series", k));
        return ChartFactory.createAreaChart(title, "Category", "Value", dataset);
    }

    /**
     * Exports bar, line, pie and area views of the current data as four real PNG files in
     * the given directory, returning their paths.
     */
    public Map<String, File> exportAll(File directory, String baseTitle) throws IOException {
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Could not create directory " + directory);
        }
        Map<String, File> exported = new LinkedHashMap<>();
        exported.put("bar", save(bar(baseTitle + " (Bar)"), new File(directory, "bar.png")));
        exported.put("line", save(line(baseTitle + " (Line)"), new File(directory, "line.png")));
        exported.put("pie", save(pie(baseTitle + " (Pie)"), new File(directory, "pie.png")));
        exported.put("area", save(area(baseTitle + " (Area)"), new File(directory, "area.png")));
        return exported;
    }

    private File save(JFreeChart chart, File file) throws IOException {
        ChartUtils.saveChartAsPNG(file, chart, 800, 600);
        return file;
    }

    public static void main(String[] args) throws IOException {
        Map<String, Double> sales = new LinkedHashMap<>();
        sales.put("Jan", 1000.0);
        sales.put("Feb", 1200.0);
        sales.put("Mar", 900.0);
        sales.put("Apr", 1500.0);

        Dashboard dashboard = new Dashboard(sales);
        Map<String, File> files = dashboard.exportAll(new File("dashboard-charts"), "Sales");
        files.forEach((type, file) -> System.out.println(type + ": " + file.getAbsolutePath() + " (" + file.length() + " bytes)"));
    }
}
