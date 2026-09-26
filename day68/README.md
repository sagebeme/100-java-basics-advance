# Day 68 - Authentication with Spring Security

## 📚 Learning Objectives
- Implement authentication
- Use Spring Security
- Handle user login
- Secure endpoints
- Manage sessions

## 🎯 Topics Covered
- Spring Security
- Authentication
- Authorization
- Password encoding
- Security configuration
- User management

## 📝 Step-by-Step Instructions

### Step 1: Security Configuration
Configure Spring Security:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/register").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            );
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

### Step 2: User Service
Implement user details:

```java
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserPrincipal(user);
    }
}
```

## 🎮 Project: Secure Blog

### Requirements
Add security to blog:
1. User registration
2. Login/logout
3. Protected routes
4. Password encryption
5. Session management

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE.

## ✅ Checklist
- [ ] Configured Spring Security
- [ ] Implemented authentication
- [ ] Added authorization
- [ ] Secured endpoints
- [ ] Completed secure blog
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `controller/AuthController.java` | Home, register, login and a dashboard only logged-in users can see |
| `model/AppUser.java` | A user account, stored with a hashed password |
| `repository/AppUserRepository.java` | Spring Data repository for accounts |
| `security/SecurityConfig.java` | Which pages need a login, the login form, and the BCrypt password encoder |
| `security/UserDetailsServiceImpl.java` | Loads an account for Spring Security by username |

Also in `src/main/resources/`: `templates/dashboard.html`, `templates/login.html`, `templates/register.html`.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day68
mvn spring-boot:run
```

Then open http://localhost:8080/register. Stop it with Ctrl+C. Or run `Application` from your IDE.

## 🧪 How to Test

```bash
cd day68
mvn test
```

Runs `SecurityTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 69?** You'll add users to your blog!
