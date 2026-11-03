# Day 69 - Blog Capstone Part 4: Posts Belong to Their Authors

> Mtaa News has writers now, and every story should know who wrote it, and every writer should be able to see their own stories. That's a relationship between two tables, and JPA can manage both ends of it.

## 📖 The story

With accounts in place (Day 68), Kevo and Shiru have three more writers: Brian covers football, Mercy covers the market, Otieno covers the county assembly. Readers want a byline on every story and a "more from this writer" list. In the database that means:

- a `blog_user` table and a `post` table;
- each post has a `user_id` column pointing at the user who wrote it: a **foreign key**;
- one user, many posts: a **one-to-many** relationship (and, seen from the post, **many-to-one**).

```
blog_user                    post
id | username              id | title                               | user_id
 1 | brian                  1 | Kayole Stars win the final 2-1      | 1
 2 | mercy                  2 | New market stalls at Mihango        | 2
                            3 | Under-14 football final on Saturday | 1
```

`BlogService` creates writers and posts, and finds every post by one writer. The tests check that a post records its author, and that one writer's posts never turn up in another's list.

## 🎯 Learning Objectives
- Model a one-to-many relationship with `@ManyToOne` and `@OneToMany(mappedBy = ...)`
- Know which side owns the relationship (the one with the foreign key), and keep the other side in step
- Query across a relationship with a method name: `findByAuthorId`
- Understand lazy loading, and why it needs an open transaction
- Use `@Transactional` on service methods, and test them both inside and outside a test transaction

## 🧰 What you'll use

**Both ends of the relationship:**

```java
@Entity
public class Post {
    @ManyToOne
    @JoinColumn(name = "user_id")          // the foreign key column: this is the owning side
    private BlogUser author;
}

@Entity
@Table(name = "blog_user")
public class BlogUser {
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();   // the other side: no column of its own
}
```

`mappedBy = "author"` says "this list is just the posts whose `author` is me". Saving a `Post` with its author is what writes `user_id`. The `posts` list is only Java, so after saving a post you add it to the list yourself, or the object in memory is out of date.

**Lazy loading.** A writer's `posts` aren't loaded until something asks for them, which saves a big query every time you load a user. But loading them later needs the database session to still be open. Outside a transaction it isn't, and you get `LazyInitializationException`. So service methods that touch relationships are `@Transactional`:

```java
@Transactional
public Post createPost(Long authorId, String title) {
    BlogUser author = blogUserRepository.findById(authorId).orElseThrow(...);
    Post post = postRepository.save(new Post(title, author));
    author.getPosts().add(post);        // loads the lazy list: needs the transaction
    return post;
}
```

**A test-only transaction can hide this bug.** `BlogServiceTest` is `@Transactional`, so everything inside it works with or without the service's own transaction. `BlogServiceWithoutATestTransactionTest` calls the service the way a controller would, and fails if the service forgets `@Transactional`.

## 🪜 Steps

**Project: Writers and their stories** (the `day69/` folder is a finished version: build your own first, then compare).

1. `BlogUser` (username, email, posts) and `Post` (title, author), with both relationship annotations.
2. `BlogUserRepository`, and `PostRepository.findByAuthorId`.
3. `BlogService`: `registerUser`, `createPost` (an unknown author is an error) and `getPostsByAuthor`, with `@Transactional` where they need it.
4. Tests: the author is recorded, one writer's posts, no leaking between writers, the in-memory list kept in step, an unknown author, and a call without a test transaction.
5. Add bylines and a "more from this writer" list to your Mtaa News pages.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day69
mvn test
```

To see the join Hibernate writes for `findByAuthorId`, add `spring.jpa.show-sql=true` to `application.properties`.

## 🆘 Stuck?

- **`LazyInitializationException: ... no session`**: something touched a lazy list outside a transaction. Add `@Transactional` to the service method.
- **`user_id` is always null**: you set the post on the user's list but not the user on the post. The `@ManyToOne` side is the one that's saved.
- **`author.getPosts()` is empty right after creating a post**: the list is only Java. Add the new post to it in `createPost`.
- **JSON for a user never ends (`StackOverflowError`)**: the user lists posts, each post has the user, which lists posts… Send a DTO, or mark one side `@JsonIgnore`.
- **A table called `user` fails to create**: it's a reserved word. That's why this one is `blog_user`.

## ✅ Checklist
- [ ] A one-to-many relationship with both ends mapped
- [ ] Posts found by their author with a method name
- [ ] Service methods that touch relationships are `@Transactional`
- [ ] A test proves the service works without a test transaction
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 70 packages the app for deployment: environment-based configuration, health checks, and a Docker image.
