package com.portfolio;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Hits a real, live URL rather than a mock. example.com is IANA's domain explicitly reserved for
 * illustrative use in examples/tests and has an extremely stable, minimal page, so this proves
 * the actual HTTP fetch path works without depending on a site whose markup could change.
 */
class LiveFetchTest {

    @Test
    void fetchingARealLivePageReturnsItsActualContent() throws Exception {
        Document document = new WebScraper().fetch("https://example.com/");

        assertEquals("Example Domain", document.title());
        assertTrue(document.body().text().contains("documentation examples"));
    }
}
