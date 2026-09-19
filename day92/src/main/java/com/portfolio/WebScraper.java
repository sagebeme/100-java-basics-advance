package com.portfolio;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebScraper {

    private static final String USER_AGENT = "Mozilla/5.0 (compatible; PortfolioScraper/1.0)";
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    /**
     * Fetches and parses a live page. Kept separate from extractItems so extraction logic can be
     * tested against fixed local HTML without depending on network access or a specific site's
     * markup staying stable.
     */
    public Document fetch(String url) throws IOException {
        try {
            return Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .timeout((int) TIMEOUT.toMillis())
                    .get();
        } catch (IOException | IllegalArgumentException e) {
            // IllegalArgumentException covers a malformed URL, which Jsoup rejects before any
            // I/O happens - both cases mean "could not fetch this URL" from a caller's point of
            // view, so present them uniformly as one checked exception.
            throw new IOException("Failed to fetch " + url + ": " + e.getMessage(), e);
        }
    }

    /**
     * Extracts one ScrapedItem per element matching itemSelector, reading the title from
     * titleSelector's text and the link from linkSelector's absolute href, both relative to that
     * item element. Items missing either piece are skipped rather than producing a null field.
     */
    public List<ScrapedItem> extractItems(Document document, String itemSelector, String titleSelector, String linkSelector) {
        List<ScrapedItem> items = new ArrayList<>();
        Elements elements = document.select(itemSelector);
        for (Element element : elements) {
            Element titleElement = element.selectFirst(titleSelector);
            Element linkElement = element.selectFirst(linkSelector);
            if (titleElement == null || linkElement == null) {
                continue;
            }
            String title = titleElement.text();
            String link = linkElement.absUrl("href");
            if (title.isBlank() || link.isBlank()) {
                continue;
            }
            items.add(new ScrapedItem(title, link));
        }
        return items;
    }
}
