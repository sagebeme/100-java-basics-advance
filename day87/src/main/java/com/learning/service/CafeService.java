package com.learning.service;

import com.learning.model.Cafe;
import com.learning.model.PriceLevel;
import com.learning.repository.CafeRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CafeService {

    private final CafeRepository cafeRepository;

    public CafeService(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    /**
     * Searches cafes by location text (matched against name or address) and applies the given
     * filters. Any parameter left null/blank is treated as "no filter" for that criterion.
     */
    public List<Cafe> search(String locationQuery, Double minRating, Boolean wifiOnly, PriceLevel maxPriceLevel) {
        List<Cafe> candidates = (locationQuery == null || locationQuery.isBlank())
                ? cafeRepository.findAll()
                : cafeRepository.findByAddressContainingIgnoreCaseOrNameContainingIgnoreCase(locationQuery, locationQuery);

        return candidates.stream()
                .filter(cafe -> minRating == null || cafe.getRating() >= minRating)
                .filter(cafe -> wifiOnly == null || !wifiOnly || cafe.isHasWifi())
                .filter(cafe -> maxPriceLevel == null || cafe.getPriceLevel().ordinal() <= maxPriceLevel.ordinal())
                .sorted(Comparator.comparing(Cafe::getRating).reversed())
                .toList();
    }
}
