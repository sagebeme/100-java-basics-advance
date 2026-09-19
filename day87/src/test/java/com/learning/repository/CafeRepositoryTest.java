package com.learning.repository;

import com.learning.model.Cafe;
import com.learning.model.PriceLevel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CafeRepositoryTest {

    @Autowired
    private CafeRepository cafeRepository;

    @Test
    void findsCafesByCaseInsensitiveAddressMatch() {
        cafeRepository.save(new Cafe("Java House", "Moi Avenue, Nairobi", -1.28, 36.82, 4.3, true, PriceLevel.TWO, "chain"));
        cafeRepository.save(new Cafe("Other Cafe", "Somewhere Else", -1.3, 36.7, 4.0, true, PriceLevel.ONE, "other"));

        List<Cafe> result = cafeRepository.findByAddressContainingIgnoreCaseOrNameContainingIgnoreCase("nairobi", "nairobi");

        assertEquals(1, result.size());
        assertEquals("Java House", result.get(0).getName());
    }

    @Test
    void findsCafesByCaseInsensitiveNameMatch() {
        cafeRepository.save(new Cafe("Westlands Brew", "Some Road", -1.26, 36.81, 4.1, true, PriceLevel.TWO, "brew"));

        List<Cafe> result = cafeRepository.findByAddressContainingIgnoreCaseOrNameContainingIgnoreCase("westlands", "westlands");

        assertEquals(1, result.size());
    }

    @Test
    void returnsEmptyWhenNothingMatches() {
        cafeRepository.save(new Cafe("Java House", "Moi Avenue, Nairobi", -1.28, 36.82, 4.3, true, PriceLevel.TWO, "chain"));

        List<Cafe> result = cafeRepository.findByAddressContainingIgnoreCaseOrNameContainingIgnoreCase("Mombasa", "Mombasa");

        assertTrue(result.isEmpty());
    }
}
