package com.learning.service;

import com.learning.dto.CryptoPrice;
import com.learning.exception.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/** Fixed responses for the logic; one real call to CoinGecko, skipped when unreachable. */
class CryptoServiceTest {

    private static final String URL =
            "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin%2Cethereum&vs_currencies=usd"; // the comma is encoded

    private final RestClient.Builder builder = RestClient.builder();
    private final MockRestServiceServer server = MockRestServiceServer.bindTo(builder).build();
    private final CryptoService cryptoService = new CryptoService(builder);

    @Test
    void readsAPriceForEachCoinInTheOrderAskedFor() {
        server.expect(requestTo(URL)).andRespond(withSuccess(
                "{\"ethereum\":{\"usd\":3100.5},\"bitcoin\":{\"usd\":64000}}", MediaType.APPLICATION_JSON));

        List<CryptoPrice> prices = cryptoService.fetchPrices(List.of("bitcoin", "ethereum"));

        assertEquals(List.of(new CryptoPrice("bitcoin", 64000), new CryptoPrice("ethereum", 3100.5)), prices);
        server.verify();
    }

    @Test
    void aMissingCoinIsAnApiError() {
        server.expect(requestTo(URL)).andRespond(withSuccess("{\"bitcoin\":{\"usd\":64000}}", MediaType.APPLICATION_JSON));

        ApiException e = assertThrows(ApiException.class, () -> cryptoService.fetchPrices(List.of("bitcoin", "ethereum")));
        assertTrue(e.getMessage().contains("ethereum"));
    }

    @Test
    void beingRateLimitedIsAnApiError() {
        // CoinGecko's free API answers 429 Too Many Requests when called too often.
        server.expect(requestTo(URL)).andRespond(withStatus(HttpStatus.TOO_MANY_REQUESTS));

        assertThrows(ApiException.class, () -> cryptoService.fetchPrices(List.of("bitcoin", "ethereum")));
    }

    @Test
    void fetchesRealPricesForEveryRequestedCoin() {
        List<CryptoPrice> prices;
        try {
            prices = new CryptoService().fetchPrices(List.of("bitcoin", "ethereum"));
        } catch (ApiException e) {
            assumeTrue(!(e.getCause() instanceof RestClientException), "CoinGecko unreachable or rate-limited: " + e.getMessage());
            throw e;
        }

        assertEquals(2, prices.size());
        for (CryptoPrice price : prices) {
            assertTrue(price.usd() > 0, "Expected a positive price for " + price.coin());
        }
        assertEquals("bitcoin", prices.get(0).coin());
        assertEquals("ethereum", prices.get(1).coin());
    }
}
