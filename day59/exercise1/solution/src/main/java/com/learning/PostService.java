package com.learning;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final List<Post> posts = List.of(
            new Post(1, "Kayole's new footbridge opens", "Wanjiku Mwangi", LocalDate.of(2026, 11, 3),
                    "The footbridge over the Nairobi River at Soweto opened on Monday, after two years of building."
                            + " Children who walked a long way round to school now cross in five minutes.\n\n"
                            + "The county says the bridge will be lit at night by January."),
            new Post(2, "Water rationing: the new timetable", "Otieno Ouma", LocalDate.of(2026, 10, 30),
                    "Nairobi Water has published new rationing days for Kayole. Phases 1 and 2 get water on Monday,"
                            + " Wednesday and Saturday.\n\nPhase 3 gets Tuesday, Thursday and Sunday."),
            new Post(3, "Under-14 football final on Saturday", "Amina Hassan", LocalDate.of(2026, 10, 28),
                    "Kayole Stars play Umoja United at the Kayole grounds at 2pm. Entry is free."),
            new Post(4, "Free eye clinic at the health centre", "Wanjiku Mwangi", LocalDate.of(2026, 10, 25),
                    "Doctors from a Nairobi hospital will check eyes for free on 8 November, from 8am. Bring your ID."
                            + " Glasses will be sold at cost.\n\nThe clinic last came in March and saw 600 people."));

    public List<Post> all() {
        return posts;
    }

    public Optional<Post> find(long id) {
        return posts.stream().filter(p -> p.getId() == id).findFirst();
    }

    // The text if it fits in `max` characters. Otherwise it's cut at the last space that leaves room
    // for "…", so no word is broken in half, and "…" is added.
    public static String excerpt(String text, int max) {
        String flat = text.replaceAll("\\s+", " ").trim();
        if (flat.length() <= max) {
            return flat;
        }
        int cut = flat.lastIndexOf(' ', max - 1);
        if (cut <= 0) {
            cut = max - 1;
        }
        return flat.substring(0, cut).replaceAll("[\\s.,;:]+$", "") + "…";
    }
}
