# Day 68 - Authentication with Spring Security

## 📚 Learning Objectives
- Implement authentication
- Use Spring Security
- Handle user login
- Secure endpoints
- Manage sessions
- Secure an API with JWT access tokens and rotating refresh tokens, and know when to choose them over sessions (Part 2)

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

## 🎮 Part 2: Tokens for an API (JWT, access and refresh)

Part 1 logs people in to a website with a form and a session cookie. Mobile apps and APIs that other programs call usually use **tokens** instead. `jwt/` is a small Spring Boot API, **Tikiti** (Swahili for ticket): fans can see who they are, and only organisers can create events.

### What a JWT is

Three pieces of base64url text joined by dots: `header.payload.signature`.

- **Header**: `{"alg":"HS256"}`, which algorithm signed it.
- **Payload**: the *claims*: `sub` (who), `iat` (issued at), `exp` (expires at), plus your own, like `role`.
- **Signature**: HMAC-SHA256 of `header.payload` with a secret key only the server has.

Two facts, and most JWT bugs come from forgetting one:

1. **Anyone can read it.** base64url is an encoding, not encryption. Never put a secret in a payload.
2. **Nobody can change it.** Edit one character and the signature no longer matches, so the server can trust `"role": "ORGANISER"` without asking the database.

`JwtConfig.java` pins the algorithm to HS256. Some libraries once trusted the token's own header, so attackers sent `"alg": "none"` with no signature and got in. The tests try exactly that.

### Access tokens and refresh tokens

A JWT is checked without the database, which is fast, but **it can't be taken back**: it works until it expires, even after logout. So there are two tokens:

| | Access token | Refresh token |
|---|---|---|
| What it is | a JWT | a random string, stored as a **hash** |
| Lives | **15 minutes** | 30 days |
| Sent with | every call, as `Authorization: Bearer <token>` | only `POST /auth/refresh`, in a cookie |
| Cookie settings | (not a cookie) | `HttpOnly`, `Secure`, `SameSite=Strict`, `Path=/auth` |
| Can be taken back | no, it just expires | yes |

### Rotation and reuse detection

Every refresh **uses up** the refresh token and issues a new one in the same *family* (one family per login, so per device). If a used-up token ever comes back, two people hold it, so `RefreshTokenService` revokes the whole family and both must log in again. Only the real owner can. The same user's other devices keep working.

A client app must refresh **once at a time**: if five requests all get a 401 and each refreshes, four of them present a used-up token and the user is logged out for nothing. Share one refresh between them (the TypeScript course builds this on Day 61).

### 401 or 403?

- **401**: "Who are you?" No token, a forged one, an expired one. Spring adds a `WWW-Authenticate: Bearer ...` header; `error="invalid_token"` tells the client a refresh may help.
- **403**: "I know who you are, and no." A fan creating an event.

### Sessions or JWTs?

| | Sessions (Part 1) | JWT + refresh (Part 2) |
|---|---|---|
| Check per request | session lookup | signature check, no database |
| Log someone out or ban them | instant | only when their access token expires |
| Many services or a mobile app | awkward | a header works anywhere |
| Ways to get it wrong | few | many: algorithm, storage, refresh races, revoking |

For one website talking to its own back end, **sessions are simpler and safer**. Choose tokens for mobile apps, public APIs and several back ends.

### Try it

From `day68/jwt` (it runs on port 8068; the two accounts are in `SecurityConfig.java`):

```bash
mvn spring-boot:run
curl -i -c jar.txt -X POST localhost:8068/auth/login -H 'Content-Type: application/json' -d '{"username":"amina","password":"matatu-sunset-42"}'
curl -i localhost:8068/me -H "Authorization: Bearer <paste the accessToken>"
curl -i -X POST localhost:8068/events -H "Authorization: Bearer <same token>"   # 403: amina is a fan
```

Paste an access token into any JWT decoder to see that the payload is readable. On `http://localhost`, curl still sends the `Secure` refresh cookie, but a browser wouldn't: in production everything runs over HTTPS.

**One Spring detail:** Spring accepts a token during its `exp` second itself, and by default allows 60 more seconds for servers whose clocks disagree. `JwtConfig` turns that allowance off, because Tikiti is a single server.

**HTTP methods and status codes, from `1xx` to `5xx`:** [the reference page](https://github.com/sagebeme/100-Days-of-TypeScript/blob/main/reference/http.md) (from the 100 Days of TypeScript course; HTTP works the same in every language).

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE.

## ✅ Checklist
- [ ] Configured Spring Security
- [ ] Implemented authentication
- [ ] Added authorization
- [ ] Secured endpoints
- [ ] Completed secure blog
- [ ] Ran Part 2's tests in `jwt/`, and can explain access vs refresh tokens, rotation, and 401 vs 403
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

Part 2's `jwt/` folder is its own Maven project:

| File | What it does |
|---|---|
| `JwtConfig.java` | The signing key (at least 32 bytes, from an environment variable), and an encoder and decoder pinned to HS256 |
| `SecurityConfig.java` | Checks `Authorization: Bearer` on every request, no sessions, only organisers may `POST /events`, two test accounts |
| `TokenService.java` | Makes a 15-minute access token |
| `RefreshTokenService.java` | Issues, rotates and revokes refresh tokens, storing only their hashes, and catches reuse |
| `AuthController.java` | `POST /auth/login`, `/auth/refresh` and `/auth/logout`, with the refresh cookie |
| `EventController.java` | `GET /me` from the token alone, and `POST /events` for organisers |
| `RefreshTokenServiceTest.java`, `TokenApiTest.java` | Rotation and reuse, then the whole API: forged, `alg: none` and expired tokens, 401 vs 403, refresh and logout |

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

Part 2:

```bash
cd day68/jwt
mvn test
```

Runs `RefreshTokenServiceTest` and `TokenApiTest` (17 tests).

## 🚀 Next Steps
**Ready for Day 69?** You'll add users to your blog!
