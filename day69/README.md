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

## 💻 How to Run

**Run:** `mvn spring-boot:run` or run main Application from IDE. Open http://localhost:8080.

## 🚀 Next Steps
**Ready for Day 70?** You'll learn deployment with Docker and Cloud!






