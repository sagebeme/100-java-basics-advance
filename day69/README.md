# Day 69 - Blog Capstone Part 4: Adding Users

## 📚 Learning Objectives
- Add user management to blog
- Implement user roles
- Create user profiles
- Link posts to users
- Complete blog application

## 🎯 Topics Covered
- User management
- User roles
- User profiles
- Post ownership
- User relationships

## 📝 Step-by-Step Instructions

### Step 1: User Entity
Add user to blog:

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String email;
    private String password;
    
    @OneToMany(mappedBy = "author")
    private List<Post> posts;
}
```

### Step 2: Post-User Relationship
Link posts to users:

```java
@Entity
public class Post {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
}
```

## 🎮 Project: Blog with Users

### Requirements
Add users to blog:
1. User registration
2. User profiles
3. Post ownership
4. User dashboard
5. User management

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE.

## ✅ Checklist
- [ ] Added user entity
- [ ] Linked posts to users
- [ ] Created user profiles
- [ ] Added user dashboard
- [ ] Completed blog with users
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `model/BlogUser.java` | A blog user, with the posts they wrote |
| `model/Post.java` | A post with its author |
| `repository/BlogUserRepository.java` | Spring Data repository for users |
| `repository/PostRepository.java` | Spring Data repository for posts |
| `service/BlogService.java` | Registers users, creates posts for an author, lists an author's posts |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day69
mvn spring-boot:run
```

It has no pages: today is about the data model. The tests show users and posts working together. Stop it with Ctrl+C.

## 🧪 How to Test

```bash
cd day69
mvn test
```

Runs `BlogServiceTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 70?** You'll learn deployment with Docker and Cloud!
