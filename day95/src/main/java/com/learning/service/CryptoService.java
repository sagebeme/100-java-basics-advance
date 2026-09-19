package com.learning.service;

import com.learning.dto.CryptoPrice;
import com.learning.exception.ApiException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import tools.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

@Service
public class CryptoService {

    private static final String BASE_URL = "https://api.coingecko.com/api/v3/simple/price";

    private final RestClient restClient = RestClient.create();

    /** Current USD prices for the given CoinGecko coin ids (e.g. "bitcoin", "ethereum"). */
    public List<CryptoPrice> fetchPrices(List<String> coinIds) {
        String ids = String.join(",", coinIds);
        String url = BASE_URL + "?ids=" + ids + "&vs_currencies=usd";
        try {
            JsonNode root = restClient.get().uri(url).retrieve().body(JsonNode.class);
            List<CryptoPrice> prices = new ArrayList<>();
            for (String coinId : coinIds) {
                JsonNode coinNode = root.get(coinId);
                if (coinNode == null || coinNode.get("usd") == null) {
                    throw new ApiException("Crypto API response missing price for " + coinId, null);
                }
                prices.add(new CryptoPrice(coinId, coinNode.get("usd").asDouble()));
            }
            return prices;
        } catch (RestClientException e) {
            throw new ApiException("Could not fetch crypto prices: " + e.getMessage(), e);
        }
    }
}
