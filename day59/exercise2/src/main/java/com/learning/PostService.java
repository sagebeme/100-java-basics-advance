package com.learning;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>(List.of(
            new Post(1, "Kayole's new footbridge opens", "News"),
            new Post(2, "Water rationing: the new timetable", "News"),
            new Post(3, "Under-14 football final on Saturday", "Sport"),
            new Post(4, "Free eye clinic at the health centre", "Health"),
            new Post(5, "Boda boda riders form a Sacco", "Business"),
            new Post(6, "Kayole Stars win the final 2-1", "Sport"),
            new Post(7, "New market stalls at Mihango", "Business")));

    public synchronized int count() {
        return posts.size();
    }

    // Always at least 1, so an empty site still has a first page to show.
    public synchronized int totalPages(int pageSize) {
        // TODO
        return 0;
    }

    // Page 1 is the first `pageSize` posts. A page past the end is empty.
    public synchronized List<Post> getPage(int page, int pageSize) {
        // TODO: refuse a page below 1 with an IllegalArgumentException
        return List.of();
    }

    // True if there was such a post to delete.
    public synchronized boolean delete(long id) {
        // TODO
        return false;
    }
}
