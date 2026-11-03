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

    private static final String URL = "https://api.coingecko.com/api/v3/simple/price?ids={ids}&vs_currencies=usd";

    private final RestClient restClient;

    public CryptoService() {
        this(RestClient.builder());
    }

    /** For tests: pass a builder bound to a MockRestServiceServer to answer without the internet. */
    CryptoService(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    /** Current USD prices for the given CoinGecko coin ids (e.g. "bitcoin", "ethereum"). */
    public List<CryptoPrice> fetchPrices(List<String> coinIds) {
        JsonNode root;
        try {
            // {ids} is a URI template variable, so the ids are encoded properly.
            root = restClient.get().uri(URL, String.join(",", coinIds)).retrieve().body(JsonNode.class);
        } catch (RestClientException e) {
            throw new ApiException("Could not fetch crypto prices: " + e.getMessage(), e);
        }
        List<CryptoPrice> prices = new ArrayList<>();
        for (String coinId : coinIds) {
            JsonNode usd = root == null ? null : root.path(coinId).get("usd");
            if (usd == null || !usd.isNumber()) {
                throw new ApiException("Crypto API response missing price for " + coinId, null);
            }
            prices.add(new CryptoPrice(coinId, usd.asDouble()));
        }
        return prices;
    }
}
