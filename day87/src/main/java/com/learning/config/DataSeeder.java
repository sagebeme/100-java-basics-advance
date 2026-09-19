package com.learning.config;

import com.learning.model.Cafe;
import com.learning.model.PriceLevel;
import com.learning.repository.CafeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CafeRepository cafeRepository;

    public DataSeeder(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @Override
    public void run(String... args) {
        if (cafeRepository.count() > 0) {
            return;
        }

        cafeRepository.save(new Cafe("Java House", "Moi Avenue, Nairobi", -1.2833, 36.8167, 4.3, true, PriceLevel.TWO,
                "A reliable Nairobi chain with strong coffee and steady wifi for laptop work."));
        cafeRepository.save(new Cafe("Artcaffe Westlands", "Woodvale Grove, Westlands, Nairobi", -1.2676, 36.8107, 4.5, true, PriceLevel.THREE,
                "Bright, spacious branch popular with remote workers; pastries and full breakfast menu."));
        cafeRepository.save(new Cafe("Cafe Deli Karen", "Karen Road, Nairobi", -1.3197, 36.7076, 4.2, false, PriceLevel.TWO,
                "Quiet garden seating in Karen; no wifi, better for a relaxed break than laptop work."));
        cafeRepository.save(new Cafe("Nairobi Coffee House", "Kimathi Street, Nairobi", -1.2841, 36.8233, 3.9, true, PriceLevel.ONE,
                "Budget-friendly spot in the CBD with fast counter service."));
        cafeRepository.save(new Cafe("Spring Valley Coffee", "Spring Valley, Nairobi", -1.2569, 36.7896, 4.6, true, PriceLevel.THREE,
                "Specialty single-origin beans and a dedicated quiet zone for calls."));
        cafeRepository.save(new Cafe("Kilimani Grind", "Wood Avenue, Kilimani, Nairobi", -1.2921, 36.7876, 4.1, true, PriceLevel.TWO,
                "Popular with students; plenty of power outlets and fast wifi."));
        cafeRepository.save(new Cafe("Lavington Curve Cafe", "James Gichuru Road, Lavington, Nairobi", -1.2793, 36.7679, 4.0, false, PriceLevel.TWO,
                "Cosy neighbourhood cafe, seating is limited so it fills up on weekends."));
        cafeRepository.save(new Cafe("CBD Express Coffee", "Tom Mboya Street, Nairobi", -1.2854, 36.8256, 3.6, true, PriceLevel.ONE,
                "Quick takeaway counter for commuters; minimal seating."));
    }
}
