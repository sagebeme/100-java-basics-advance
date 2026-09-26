# Day 63 - Databases with JPA and Hibernate

## 📚 Learning Objectives
- Understand JPA and Hibernate
- Create entity classes
- Work with repositories
- Perform database operations
- Build data-driven applications

## 🎯 Topics Covered
- JPA annotations
- Entity relationships
- Repository pattern
- CRUD operations
- Query methods
- Database configuration

## 📝 Step-by-Step Instructions

### Step 1: Entity Class
Create JPA entity:

```java
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    // Constructors, getters, setters
}
```

### Step 2: Repository Interface
Create repository:

```java
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByNameContaining(String name);
}
```

### Step 3: Service Layer
Use repository in service:

```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
```

## 🎮 Project: User Management with Database

### Requirements
Create user management:
1. User entity
2. Repository interface
3. Service layer
4. CRUD operations
5. Database configuration

## 📚 Resources
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [JPA Documentation](https://docs.oracle.com/javaee/7/api/javax/persistence/package-summary.html)

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE.

## ✅ Checklist
- [ ] Understand JPA
- [ ] Can create entities
- [ ] Can use repositories
- [ ] Can perform CRUD
- [ ] Completed user management
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `model/User.java` | A JPA entity |
| `repository/UserRepository.java` | Spring Data repository, including a find-by-email query |
| `service/UserService.java` | Create, read, update and delete users |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day63
mvn spring-boot:run
```

It has no pages: today is about the database layer. The tests show it working. Stop it with Ctrl+C.

## 🧪 How to Test

```bash
cd day63
mvn test
```

Runs `UserRepositoryTest` and `UserServiceTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 64?** You'll build a Top 10 Movies website!
