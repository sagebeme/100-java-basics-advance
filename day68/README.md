# Day 68 - Logging In: Spring Security, Sessions and Tokens

> Mtaa News needs accounts: only its writers should see the editor's desk. Today you add registration and login with Spring Security, store passwords so a stolen database doesn't give them away, and then, for Brian's Android app, learn how tokens work and how they go wrong.

## 📖 The story

**Part 1, Ngome** ("fortress"). Kevo and Shiru want the editor's pages locked: anyone can read the news, but only someone with an account can reach the dashboard. That means:

- a **register** page that refuses a blank username, a password shorter than 8 characters and a username someone already has;
- passwords stored as **BCrypt hashes**, never as the password itself, so a leaked database doesn't leak passwords;
- a **login** form, a **session** that remembers you, and **logout**;
- every page except home, register, login and the stylesheet needing a login: try `/dashboard` without one and you're sent to `/login`.

**Part 2, Tikiti** ("ticket"). Brian's app isn't a browser with a form. Apps and APIs usually use **tokens** instead of sessions. `jwt/` is a small event-ticket API where fans can see who they are and only organisers can create events. It shows how JWT access tokens and rotating refresh tokens work, and tests every classic mistake: a forged token, `alg: none`, an expired token, a stolen refresh token.

## 🎯 Learning Objectives
- Configure Spring Security: which addresses are open, the login page, logout
- Store passwords with `BCryptPasswordEncoder`, and check them with `matches`
- Load accounts for Spring Security with a `UserDetailsService`
- Refuse duplicate usernames, in the code and in the database
- Understand CSRF protection, and why Thymeleaf forms include a hidden token
- Secure an API with JWT access tokens and rotating refresh tokens, and know when to choose them over sessions
- Tell 401 ("who are you?") from 403 ("I know who you are, and no")

## 🧰 What you'll use

### Part 1: sessions

**The security rules:**

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/register", "/css/**").permitAll()
                .anyRequest().authenticated())
        .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/dashboard").permitAll())
        .logout(logout -> logout.logoutSuccessUrl("/").permitAll());
    return http.build();
}
```

**Hashing.** `passwordEncoder.encode("matatu-sunset-42")` gives something like `$2a$10$N9qo8uLO...`, different every time because of a random *salt*, and slow on purpose, so guessing billions of passwords takes years. `passwordEncoder.matches(typed, stored)` checks a login. You can't turn a hash back into the password, and neither can an attacker.

**CSRF.** Another website could make your browser send a `POST /logout` or `POST /register` with your session cookie. Spring Security refuses any POST without a secret token from your own page. Thymeleaf adds it to every `th:action` form for you; the tests prove a POST without it gets 403.

**Unique usernames, twice.** `register` checks `findByUsername` first so it can show a friendly message, and `@Column(unique = true)` makes the database refuse a duplicate even if two registrations arrive at the same moment.

### Part 2: tokens

**A JWT** is three pieces of base64url text joined by dots: `header.payload.signature`.

- **Header**: `{"alg":"HS256"}`, which algorithm signed it.
- **Payload**: the *claims*: `sub` (who), `iat` (issued at), `exp` (expires at), plus your own, like `role`.
- **Signature**: HMAC-SHA256 of `header.payload` with a secret key only the server has.

Two facts, and most JWT bugs come from forgetting one:

1. **Anyone can read it.** base64url is an encoding, not encryption. Never put a secret in a payload.
2. **Nobody can change it.** Edit one character and the signature no longer matches, so the server can trust `"role": "ORGANISER"` without asking the database.

`JwtConfig.java` pins the algorithm to HS256. Some libraries once trusted the token's own header, so attackers sent `"alg": "none"` with no signature and got in. The tests try exactly that.

**Access tokens and refresh tokens.** A JWT is checked without the database, which is fast, but **it can't be taken back**: it works until it expires, even after logout. So there are two tokens:

| | Access token | Refresh token |
|---|---|---|
| What it is | a JWT | a random string, stored as a **hash** |
| Lives | **15 minutes** | 30 days |
| Sent with | every call, as `Authorization: Bearer <token>` | only `POST /auth/refresh`, in a cookie |
| Cookie settings | (not a cookie) | `HttpOnly`, `Secure`, `SameSite=Strict`, `Path=/auth` |
| Can be taken back | no, it just expires | yes |

**Rotation and reuse detection.** Every refresh **uses up** the refresh token and issues a new one in the same *family* (one family per login, so per device). If a used-up token ever comes back, two people hold it, so `RefreshTokenService` revokes the whole family and both must log in again. Only the real owner can. The same user's other devices keep working.

A client app must refresh **once at a time**: if five requests all get a 401 and each refreshes, four of them present a used-up token and the user is logged out for nothing. Share one refresh between them (the TypeScript course builds this on Day 61).

**401 or 403?**

- **401**: "Who are you?" No token, a forged one, an expired one. Spring adds a `WWW-Authenticate: Bearer ...` header; `error="invalid_token"` tells the client a refresh may help.
- **403**: "I know who you are, and no." A fan creating an event.

**Sessions or JWTs?**

| | Sessions (Part 1) | JWT + refresh (Part 2) |
|---|---|---|
| Check per request | session lookup | signature check, no database |
| Log someone out or ban them | instant | only when their access token expires |
| Many services or a mobile app | awkward | a header works anywhere |
| Ways to get it wrong | few | many: algorithm, storage, refresh races, revoking |

For one website talking to its own back end, **sessions are simpler and safer**. Choose tokens for mobile apps, public APIs and several back ends.

## 🪜 Steps

**Part 1: Ngome** (the `day68/` folder is a finished version: build your own first, then compare).

1. `AppUser` (a unique username and a password hash) and `AppUserRepository.findByUsername`.
2. `SecurityConfig`: the rules above and a `BCryptPasswordEncoder` bean.
3. `UserDetailsServiceImpl`: load an account by username for Spring Security.
4. `AuthController`: register (with its three checks), login, and a dashboard that greets you by name.
5. Tests: the stored password is a hash, the dashboard needs a login, a right and a wrong password, a taken username, a short password, and a POST without its CSRF token.

**Part 2: Tikiti** (the `day68/jwt/` folder, its own Maven project). Read the code alongside the tables above, run its 17 tests, then try the requests below.

| File | What it does |
|---|---|
| `JwtConfig.java` | The signing key (at least 32 bytes, from an environment variable), and an encoder and decoder pinned to HS256 |
| `SecurityConfig.java` | Checks `Authorization: Bearer` on every request, no sessions, only organisers may `POST /events`, two test accounts |
| `TokenService.java` | Makes a 15-minute access token |
| `RefreshTokenService.java` | Issues, rotates and revokes refresh tokens, storing only their hashes, and catches reuse |
| `AuthController.java` | `POST /auth/login`, `/auth/refresh` and `/auth/logout`, with the refresh cookie |
| `EventController.java` | `GET /me` from the token alone, and `POST /events` for organisers |

## 🧪 Run it and test it

Needs JDK 21 and Maven. Part 1, from the repository root:

```bash
cd day68
mvn spring-boot:run      # then open http://localhost:8080/register
mvn test
```

Part 2 runs on port 8068; the two accounts are in `jwt/.../SecurityConfig.java`:

```bash
cd day68/jwt
mvn test
mvn spring-boot:run
curl -i -c jar.txt -X POST localhost:8068/auth/login -H 'Content-Type: application/json' -d '{"username":"amina","password":"matatu-sunset-42"}'
curl -i localhost:8068/me -H "Authorization: Bearer <paste the accessToken>"
curl -i -X POST localhost:8068/events -H "Authorization: Bearer <same token>"   # 403: amina is a fan
```

Paste an access token into any JWT decoder to see that the payload is readable. On `http://localhost`, curl still sends the `Secure` refresh cookie, but a browser wouldn't: in production everything runs over HTTPS. Spring accepts a token during its `exp` second, and by default allows 60 more seconds for servers whose clocks disagree; `JwtConfig` turns that allowance off, because Tikiti is a single server.

HTTP methods and status codes, from `1xx` to `5xx`: [the reference page](https://github.com/sagebeme/100-Days-of-TypeScript/blob/main/reference/http.md) (from the 100 Days of TypeScript course; HTTP works the same in every language).

## 🆘 Stuck?

- **Every POST gets 403**: the form has no CSRF token. Use `th:action="@{/register}"`, not a plain `action`, so Thymeleaf adds it.
- **The stylesheet doesn't load on the login page**: `/css/**` must be in `permitAll()`, or the CSS needs a login too.
- **Login always fails for a user you just registered**: the password was saved without `encode`, so `matches` compares a hash with plain text.
- **`IncorrectResultSizeDataAccessException` at login**: two accounts share a username. Make the column unique and check before saving.
- **A JWT signing key error at start-up**: HS256 needs at least 32 bytes. Set `TIKITI_JWT_SECRET=$(openssl rand -base64 32)`.

## ✅ Checklist
- [ ] Registration refuses blank, short and taken usernames and passwords
- [ ] Passwords are stored as BCrypt hashes
- [ ] Pages need a login, and logout works
- [ ] Ran Part 2's tests, and can explain access vs refresh tokens, rotation, and 401 vs 403
- [ ] The tests pass in both projects
- [ ] Committed code to Git, with no secrets in it

## ➡️ Next

Day 69 links the blog's posts to their authors: one user, many posts, joined in the database.
