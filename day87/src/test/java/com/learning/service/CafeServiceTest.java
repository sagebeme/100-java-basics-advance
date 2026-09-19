package com.learning.service;

import com.learning.model.Cafe;
import com.learning.model.PriceLevel;
import com.learning.repository.CafeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CafeServiceTest {

    @Mock
    private CafeRepository cafeRepository;

    private CafeService cafeService;

    private Cafe cheapNoWifiLowRating;
    private Cafe expensiveWifiHighRating;
    private Cafe midRangeWifiMidRating;

    @BeforeEach
    void setUp() {
        cafeService = new CafeService(cafeRepository);
        cheapNoWifiLowRating = new Cafe("Cheap Cafe", "Downtown", 0, 0, 3.5, false, PriceLevel.ONE, "budget");
        expensiveWifiHighRating = new Cafe("Fancy Cafe", "Uptown", 0, 0, 4.8, true, PriceLevel.THREE, "fancy");
        midRangeWifiMidRating = new Cafe("Mid Cafe", "Midtown", 0, 0, 4.0, true, PriceLevel.TWO, "average");
    }

    @Test
    void withNoFiltersReturnsEveryCafeSortedByRatingDescending() {
        when(cafeRepository.findAll()).thenReturn(List.of(cheapNoWifiLowRating, expensiveWifiHighRating, midRangeWifiMidRating));

        List<Cafe> result = cafeService.search(null, null, null, null);

        assertEquals(List.of(expensiveWifiHighRating, midRangeWifiMidRating, cheapNoWifiLowRating), result);
    }

    @Test
    void filtersOutCafesBelowTheMinimumRating() {
        when(cafeRepository.findAll()).thenReturn(List.of(cheapNoWifiLowRating, expensiveWifiHighRating, midRangeWifiMidRating));

        List<Cafe> result = cafeService.search(null, 4.0, null, null);

        assertEquals(List.of(expensiveWifiHighRating, midRangeWifiMidRating), result);
    }

    @Test
    void wifiOnlyExcludesCafesWithoutWifi() {
        when(cafeRepository.findAll()).thenReturn(List.of(cheapNoWifiLowRating, expensiveWifiHighRating, midRangeWifiMidRating));

        List<Cafe> result = cafeService.search(null, null, true, null);

        assertFalse(result.contains(cheapNoWifiLowRating));
        assertTrue(result.contains(expensiveWifiHighRating));
        assertTrue(result.contains(midRangeWifiMidRating));
    }

    @Test
    void maxPriceExcludesCafesAboveThatPriceLevel() {
        when(cafeRepository.findAll()).thenReturn(List.of(cheapNoWifiLowRating, expensiveWifiHighRating, midRangeWifiMidRating));

        List<Cafe> result = cafeService.search(null, null, null, PriceLevel.TWO);

        assertEquals(List.of(midRangeWifiMidRating, cheapNoWifiLowRating), result);
    }

    @Test
    void aLocationQueryDelegatesToTheNameOrAddressSearch() {
        when(cafeRepository.findByAddressContainingIgnoreCaseOrNameContainingIgnoreCase("town", "town"))
                .thenReturn(List.of(midRangeWifiMidRating));

        List<Cafe> result = cafeService.search("town", null, null, null);

        assertEquals(List.of(midRangeWifiMidRating), result);
    }

    @Test
    void combinesLocationSearchWithFilters() {
        when(cafeRepository.findByAddressContainingIgnoreCaseOrNameContainingIgnoreCase("Midtown", "Midtown"))
                .thenReturn(List.of(midRangeWifiMidRating));

        List<Cafe> result = cafeService.search("Midtown", 4.5, null, null);

        assertTrue(result.isEmpty());
    }
}
