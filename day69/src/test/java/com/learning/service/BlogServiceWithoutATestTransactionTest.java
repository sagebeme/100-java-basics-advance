package com.learning.service;

import com.learning.model.BlogUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

// No @Transactional here, unlike BlogServiceTest: this is how a controller or a scheduled job
// calls the service. The service must open its own transaction, or touching author.posts
// (a lazy list) throws LazyInitializationException.
@SpringBootTest
class BlogServiceWithoutATestTransactionTest {

    @Autowired
    private BlogService blogService;

    @Test
    void creatingAPostWorksOutsideATestTransaction() {
        BlogUser shiru = blogService.registerUser("shiru-" + System.nanoTime(), "shiru@example.com");

        blogService.createPost(shiru.getId(), "Water rationing: the new timetable");

        assertEquals(1, blogService.getPostsByAuthor(shiru.getId()).size());
    }
}
