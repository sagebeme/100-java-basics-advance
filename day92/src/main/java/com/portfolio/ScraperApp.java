package com.portfolio;

import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ScraperApp {

    public static void main(String[] args) {
        WebScraper scraper = new WebScraper();
        CsvSaver csvSaver = new CsvSaver();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("URL to scrape: ");
            String url = scanner.nextLine().trim();
            System.out.print("CSS selector for each item (e.g. 'article'): ");
            String itemSelector = scanner.nextLine().trim();
            System.out.print("CSS selector for the title within an item (e.g. 'h2'): ");
            String titleSelector = scanner.nextLine().trim();
            System.out.print("CSS selector for the link within an item (e.g. 'a'): ");
            String linkSelector = scanner.nextLine().trim();
            System.out.print("Output CSV file path: ");
            String outputPath = scanner.nextLine().trim();

            Document document = scraper.fetch(url);
            List<ScrapedItem> items = scraper.extractItems(document, itemSelector, titleSelector, linkSelector);

            if (items.isEmpty()) {
                System.out.println("No items matched those selectors.");
                return;
            }

            csvSaver.save(items, new File(outputPath));
            System.out.println("Saved " + items.size() + " items to " + outputPath);
        } catch (IOException e) {
            System.out.println("Scraping failed: " + e.getMessage());
        }
    }
}
