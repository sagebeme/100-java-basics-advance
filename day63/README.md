# Day 63 - Databases With JPA: A Member Register That Remembers

> Since Day 55, everything your apps stored vanished when the server stopped. Today the data goes into a database, and you write almost no SQL to put it there.

## 📖 The story

The Tumaini Sacco in Nyeri started its member list in the Day 55 style: a map in memory. Then the laptop restarted for an update, and 140 members disappeared. Today the register moves into a **database**, where restarts don't matter and two members can never share an email address.

On Day 38 you talked to a database with JDBC: writing SQL strings, filling in `?`s and reading `ResultSet`s row by row. That works, and it's a lot of typing. **JPA** (with Hibernate doing the work) lets you describe the table *as a Java class*, and **Spring Data** writes the queries for you from method names:

```java
Optional<User> findByEmail(String email);          // SELECT ... WHERE email = ?
List<User> findByNameContaining(String name);      // SELECT ... WHERE name LIKE %?%
```

You never write the body of either method. Spring reads the name and builds the query when the app starts.

The database is H2 again, running inside the app. Pointing the same code at PostgreSQL or MySQL later is a change to `application.properties`, not to the code.

## 🎯 Learning Objectives
- Map a class to a table with `@Entity`, `@Table`, `@Id`, `@GeneratedValue` and `@Column`
- Get create, read, update and delete for free from `JpaRepository`
- Write queries as method names (`findByEmail`, `findByNameContaining`)
- Let the database enforce rules: `nullable = false` and `unique = true`
- Put the business rules in a `@Service` between controllers and the repository
- Test repositories with `@DataJpaTest` and services with `@SpringBootTest`

## 🧰 What you'll use

**An entity** is a class that's also a table:

```java
@Entity
@Table(name = "users")                 // "user" is a reserved word in SQL
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // the database picks it

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;               // no two members with the same email
}
```

JPA also needs a constructor with no arguments, and getters and setters.

**A repository** is an interface. Spring writes the class:

```java
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByNameContaining(String name);
}
```

You get `save`, `findById`, `findAll`, `deleteById` and more without writing them.

**`ddl-auto=update`** in `application.properties` tells Hibernate to create or change the tables to match your entities when the app starts. That's handy while learning; real projects use migration tools (Flyway or Liquibase) so every change to the database is written down and repeatable.

**Updating** is: load it, change it, save it. If the id doesn't exist, say so loudly instead of creating a new row:

```java
User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("No user with id " + id));
```

## 🪜 Steps

**Project: The Tumaini Member Register** (the `day63/` folder is a finished version: build your own first, then compare). There's no web page today; the tests are how you use it.

1. `User` as an entity, with the rules above.
2. `UserRepository` with `findByEmail` and `findByNameContaining`.
3. `UserService`: `getAllUsers`, `saveUser`, `getUserByEmail`, `updateUser` (throwing for an unknown id) and `deleteUser`.
4. `UserRepositoryTest` with `@DataJpaTest`: a saved user gets an id, lookups by email and by part of a name, and a duplicate email is refused by the database.
5. `UserServiceTest`: save then find, update, update an unknown id, and delete.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day63
mvn test
```

A clean run ends with `BUILD SUCCESS`. To watch the SQL that Hibernate writes for you, add `spring.jpa.show-sql=true` to `application.properties` and run the tests again.

## 🆘 Stuck?

- **`No default constructor for entity`**: JPA creates objects itself, so the entity needs a constructor with no arguments (it can be `protected`).
- **`No property 'mail' found for type 'User'`**: a query method's name must use the field's exact name: `findByEmail`, not `findByMail`.
- **`Syntax error in SQL statement ... USER`**: rename the table with `@Table(name = "users")`.
- **The duplicate-email test doesn't throw**: `save` only queues the insert. Use `saveAndFlush` (or `flush()`) so it reaches the database inside the test.
- **An update creates a second row**: you saved a `new User(...)` without its id. Load the existing one with `findById` and change that.

## ✅ Checklist
- [ ] Mapped a class to a table with JPA annotations
- [ ] Used `JpaRepository` and wrote two queries as method names
- [ ] The database refuses a duplicate email
- [ ] A service with create, read, update and delete
- [ ] Repository and service tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 64 puts a database behind a web page: a ranked list of your top 10 films, where moving one film reorders the rest.
