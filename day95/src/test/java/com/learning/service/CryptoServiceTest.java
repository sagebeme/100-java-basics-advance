package com.learning.service;

import com.learning.dto.CryptoPrice;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Hits the real, live, keyless CoinGecko API - no mocking. */
class CryptoServiceTest {

    private final CryptoService cryptoService = new CryptoService();

    @Test
    void fetchesRealPricesForEveryRequestedCoin() {
        List<CryptoPrice> prices = cryptoService.fetchPrices(List.of("bitcoin", "ethereum"));

        assertEquals(2, prices.size());
        for (CryptoPrice price : prices) {
            assertTrue(price.usd() > 0, "Expected a positive price for " + price.coin());
        }
        assertEquals("bitcoin", prices.get(0).coin());
        assertEquals("ethereum", prices.get(1).coin());
    }
}
