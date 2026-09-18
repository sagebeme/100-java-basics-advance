package com.learning.service;

import com.learning.model.BlogUser;
import com.learning.model.Post;
import com.learning.repository.BlogUserRepository;
import com.learning.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogUserRepository blogUserRepository;

    @Autowired
    private PostRepository postRepository;

    public BlogUser registerUser(String username, String email) {
        return blogUserRepository.save(new BlogUser(username, email));
    }

    public Post createPost(Long authorId, String title) {
        BlogUser author = blogUserRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("No user with id " + authorId));
        Post post = postRepository.save(new Post(title, author));
        // Post.author is the owning side (it holds the foreign key), so saving the post above
        // is what actually persists the relationship. But author.posts (the inverse side) is
        // just a Java-side list; Hibernate does not update it for us on an entity already
        // resident in this persistence context, so it has to be kept in sync by hand here.
        author.getPosts().add(post);
        return post;
    }

    public List<Post> getPostsByAuthor(Long authorId) {
        return postRepository.findByAuthorId(authorId);
    }
}
