# Day 70 - Shipping It: Docker, Profiles and a Real Database

> "It works on my laptop" isn't a deployment. Today the app goes into a container that runs the same everywhere, reads its settings from the environment, and talks to a real PostgreSQL database instead of H2.

## 📖 The story

Mtaa News has outgrown Kevo's laptop. A friend at a Nairobi hosting company offers them a server, and asks one question: "Can you give it to me as a Docker image?" On a server, the app needs to:

- run without anyone installing Java or Maven by hand;
- use **PostgreSQL**, a database that runs as its own server, not H2 inside the app;
- get the database's address and password from the **environment**, never from a file in Git;
- answer `GET /health` with `OK`, so the host can tell whether it's alive and restart it if not;
- run as an ordinary user, not as root.

Today's app is deliberately small (a health check and a list of items) so all the attention goes on packaging it. On your laptop it still uses H2 and `mvn test` needs nothing else; with Docker it runs against PostgreSQL.

## 🎯 Learning Objectives
- Write a multi-stage `Dockerfile`: build with Maven and a JDK, run with a slim JRE
- Run the app and its database together with Docker Compose, and wait for the database to be ready
- Switch configuration with Spring **profiles** (`application-docker.properties`)
- Read settings from environment variables with defaults: `${DB_HOST:localhost}`
- Add a health endpoint a host can check
- Keep secrets out of images and out of Git

## 🧰 What you'll use

**Two stages.** The first stage has Maven and a full JDK to build the jar. The second starts again from a small JRE image and copies in only the jar, so the image you ship has no compiler, no build tool and no source code:

```dockerfile
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
RUN useradd --system --uid 10001 app
COPY --from=build /app/target/*.jar app.jar
USER app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Profiles.** `application.properties` is always read. Set `SPRING_PROFILES_ACTIVE=docker` and Spring also reads `application-docker.properties`, whose settings win:

```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${DB_NAME:day70}
spring.datasource.username=${DB_USER:day70}
spring.datasource.password=${DB_PASSWORD:day70}
```

`${DB_HOST:localhost}` means "the environment variable `DB_HOST`, or `localhost` if it isn't set".

**Compose** starts both containers on one private network, where the app reaches the database by its service name, `database`. `depends_on` with `condition: service_healthy` waits until PostgreSQL's own health check (`pg_isready`) passes, so the app doesn't start, fail to connect and crash.

**About the passwords in `docker-compose.yml`.** `day70` is fine for a database that only exists on your laptop. On a real server the password comes from the host's secret settings, and the file says `DB_PASSWORD=${DB_PASSWORD}`.

## 🪜 Steps

**Project: Mtaa News in a box** (the `day70/` folder is a finished version: build your own first, then compare).

1. `HealthController`: `GET /health`, `POST /items?name=...` and `GET /items`.
2. `application.properties` for H2 on your laptop, and `application-docker.properties` for PostgreSQL from the environment.
3. The `Dockerfile`, a `.dockerignore`, and `docker-compose.yml` with the health check.
4. Build and run it, add an item, stop everything, start it again, and see the item is still there... or isn't. (Hint: where does PostgreSQL keep its files? Add a named volume so it survives.)
5. Tests: the health check, and adding and listing items against whatever database is configured.

## 🧪 Run it and test it

Without Docker, from the repository root:

```bash
cd day70
mvn test
mvn spring-boot:run          # H2, on http://localhost:8080/health
```

With Docker:

```bash
cd day70
docker compose up --build
curl localhost:8080/health
curl -X POST "localhost:8080/items?name=Unga"
curl localhost:8080/items
docker compose down
```

## 🆘 Stuck?

- **`Connection to localhost:5432 refused` inside Docker**: in a container, `localhost` is the container itself. Use the database's service name, `database`, which is what `DB_HOST` is set to.
- **The app starts before PostgreSQL is ready and crashes**: use `depends_on` with `condition: service_healthy`, and give the database a `healthcheck`.
- **The image is huge**: you shipped the build stage. The final `FROM` must be the JRE image, with only the jar copied in.
- **The data is gone after `docker compose down`**: the database stored it inside its container. Add a named volume for `/var/lib/postgresql/data`.
- **`Permission denied` writing a file after `USER app`**: the app user can't write to `/app`. Write to `/tmp`, or give it a folder of its own.

## ✅ Checklist
- [ ] A multi-stage `Dockerfile` that runs as a normal user
- [ ] The app and PostgreSQL running together with Docker Compose
- [ ] Settings from environment variables, through a profile
- [ ] A health endpoint
- [ ] No real secrets in the image or in Git
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 71 starts the data days: reading a dataset and answering questions about it with streams and statistics.
