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

## ✅ Checklist
- [ ] Configured Spring Security
- [ ] Implemented authentication
- [ ] Added authorization
- [ ] Secured endpoints
- [ ] Completed secure blog
- [ ] Committed code to Git

## 🚀 Next Steps
**Ready for Day 69?** You'll add users to your blog!

