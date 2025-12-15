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

## ✅ Checklist
- [ ] Understand JPA
- [ ] Can create entities
- [ ] Can use repositories
- [ ] Can perform CRUD
- [ ] Completed user management
- [ ] Committed code to Git

## 🚀 Next Steps
**Ready for Day 64?** You'll build a Top 10 Movies website!


