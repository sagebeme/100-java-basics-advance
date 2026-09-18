package com.learning.service;

import com.learning.model.BlogUser;
import com.learning.model.Post;
import com.learning.repository.BlogUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BlogServiceTest {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogUserRepository blogUserRepository;

    @Test
    void aCreatedPostRecordsItsAuthor() {
        BlogUser user = blogService.registerUser("amina", "amina@example.com");
        Post post = blogService.createPost(user.getId(), "Hello World");

        assertEquals("amina", post.getAuthor().getUsername());
    }

    @Test
    void findsAllPostsByAGivenAuthor() {
        BlogUser user = blogService.registerUser("kip", "kip@example.com");
        blogService.createPost(user.getId(), "First post");
        blogService.createPost(user.getId(), "Second post");

        List<Post> posts = blogService.getPostsByAuthor(user.getId());
        assertEquals(2, posts.size());
    }

    @Test
    void postsFromOneAuthorDoNotLeakIntoAnothersList() {
        BlogUser a = blogService.registerUser("amina", "amina@example.com");
        BlogUser b = blogService.registerUser("kip", "kip@example.com");
        blogService.createPost(a.getId(), "Amina's post");
        blogService.createPost(b.getId(), "Kip's post");

        List<Post> aPosts = blogService.getPostsByAuthor(a.getId());
        assertEquals(1, aPosts.size());
        assertEquals("Amina's post", aPosts.get(0).getTitle());
    }

    @Test
    void theUsersOwnPostsListReflectsWhatWasCreatedForThem() {
        BlogUser user = blogService.registerUser("zawadi", "zawadi@example.com");
        blogService.createPost(user.getId(), "Zawadi's first post");

        BlogUser reloaded = blogUserRepository.findById(user.getId()).orElseThrow();
        assertEquals(1, reloaded.getPosts().size());
        assertEquals("Zawadi's first post", reloaded.getPosts().get(0).getTitle());
    }

    @Test
    void creatingAPostForAnUnknownUserThrows() {
        assertThrows(IllegalArgumentException.class, () -> blogService.createPost(999999L, "Orphan post"));
    }
}
