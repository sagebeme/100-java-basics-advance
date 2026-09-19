package com.portfolio;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebScraperTest {

    private final WebScraper scraper = new WebScraper();

    private static final String SAMPLE_HTML = """
            <html><body>
              <article>
                <h2>First Post</h2>
                <a href="/posts/first">Read more</a>
              </article>
              <article>
                <h2>Second Post</h2>
                <a href="/posts/second">Read more</a>
              </article>
              <article>
                <h2></h2>
                <a href="/posts/empty-title">Read more</a>
              </article>
              <article>
                <h2>No Link Post</h2>
              </article>
            </body></html>
            """;

    @Test
    void extractsTitleAndAbsoluteLinkForEachMatchingItem() {
        Document document = Jsoup.parse(SAMPLE_HTML, "https://example-blog.test/");

        List<ScrapedItem> items = scraper.extractItems(document, "article", "h2", "a");

        assertEquals(2, items.size());
        assertEquals(new ScrapedItem("First Post", "https://example-blog.test/posts/first"), items.get(0));
        assertEquals(new ScrapedItem("Second Post", "https://example-blog.test/posts/second"), items.get(1));
    }

    @Test
    void skipsItemsWithABlankTitleOrMissingLink() {
        Document document = Jsoup.parse(SAMPLE_HTML, "https://example-blog.test/");

        List<ScrapedItem> items = scraper.extractItems(document, "article", "h2", "a");

        assertTrue(items.stream().noneMatch(item -> item.title().isBlank()));
        assertEquals(2, items.size()); // the empty-title and no-link articles are both excluded
    }

    @Test
    void returnsAnEmptyListWhenTheSelectorMatchesNothing() {
        Document document = Jsoup.parse(SAMPLE_HTML, "https://example-blog.test/");

        List<ScrapedItem> items = scraper.extractItems(document, ".does-not-exist", "h2", "a");

        assertTrue(items.isEmpty());
    }

    @Test
    void fetchingAnUnreachableUrlThrowsADescriptiveIOException() {
        // Port 1 is a reserved low port that nothing listens on locally, so the connection is
        // refused quickly and reliably without depending on any real remote server.
        IOException exception = assertThrows(IOException.class, () -> scraper.fetch("http://127.0.0.1:1/"));
        assertTrue(exception.getMessage().contains("http://127.0.0.1:1/"));
    }

    @Test
    void fetchingAMalformedUrlThrows() {
        assertThrows(IOException.class, () -> scraper.fetch("not-a-valid-url"));
    }
}
