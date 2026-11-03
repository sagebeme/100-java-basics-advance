# Day 54 - Hello, Spring Boot: Your First Web Server

> Until today, your programs ran when you started them and stopped when they finished. Today's program keeps running and waits for a browser to ask it something.

## 📖 The story

For three weeks you wrote bots that visited websites. Today you switch sides and write the website. Not a big one: four addresses that answer when a browser, a phone app or `curl` asks:

```
$ curl http://localhost:8080/
Welcome to Spring Boot!

$ curl http://localhost:8080/hello/Amina
{"message":"Hello, Amina!"}

$ curl http://localhost:8080/api/info
{"application":"Day 54 - Introduction to Spring Boot","version":"1.0.0"}
```

That's a **web server**. Every app you use, from M-Pesa to your bank's website, is a much bigger version of this: a program waiting for **requests** and sending back **responses**.

You'll write it with **Spring Boot**, the most widely used Java framework for web back ends. It starts a web server inside your program, turns your Java maps into JSON, and sends each request to the right method, so you write only the parts that are yours.

## 🎯 Learning Objectives
- Create and run a Spring Boot project with Maven
- Write a `@RestController` with `@GetMapping` methods
- Read part of the address with `@PathVariable`
- Return text, and return JSON by returning a `Map`
- Understand what Spring does for you: the embedded server, routing, JSON and dependency injection
- Test a controller with MockMvc, without starting a real server

## 🧰 What you'll use

**The starting point.** One class with `@SpringBootApplication` and a `main` that calls `SpringApplication.run(Application.class, args)`. That starts the web server on port 8080 (set in `application.properties`).

**A controller** is a class whose methods answer requests:

```java
@RestController
public class HelloController {

    @GetMapping("/hello/{name}")
    public Map<String, String> helloName(@PathVariable String name) {
        return Map.of("message", "Hello, " + name + "!");
    }
}
```

`{name}` in the address is handed to the `name` parameter. The `Map` you return is turned into JSON for you.

**Where did `new` go?** You never write `new HelloController()`. Spring finds every class marked `@RestController` (or `@Service`, `@Component`…), creates it once, and passes it to whatever needs it. That's **dependency injection**, and from Day 55 on you'll use it on purpose.

**Maven** reads `pom.xml`, downloads Spring Boot (the first run takes a few minutes), compiles, tests and runs.

## 🪜 Steps

**Project: Hello, Spring Boot** (the `day54/` folder is the whole project). There are no exercises today: getting your first server running is the exercise.

1. Read `pom.xml`: the parent `spring-boot-starter-parent`, and the `spring-boot-starter-web` dependency.
2. `Application.java`: the `@SpringBootApplication` class with its `main`.
3. `HelloController`: `GET /` (plain text), `GET /hello`, `GET /hello/{name}` and `GET /api/info` (JSON).
4. Run it, and visit each address in a browser and with `curl`.
5. Run the tests, then add an endpoint of your own, such as `GET /time`, with a test.

## 🧪 Run it and test it

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day54
mvn spring-boot:run
```

Open http://localhost:8080/hello/Amina, and stop the server with Ctrl+C. Or run `Application` from your IDE.

```bash
cd day54
mvn test
```

`HelloControllerTest` sends each request through MockMvc and checks the answer. A clean run ends with `BUILD SUCCESS`.

## 🆘 Stuck?

- **`Port 8080 was already in use`**: another server is still running, maybe yesterday's. Stop it, or set `server.port=8081` in `application.properties`.
- **`Whitelabel Error Page` with 404**: no method matches that address. Check the spelling in `@GetMapping`, and that the controller is in `com.learning` or a package inside it, where Spring looks.
- **The first `mvn` run is very slow**: it's downloading Spring Boot into `~/.m2`. The second run is fast.
- **`release version 21 not supported`**: Maven is using an older JDK. Check `mvn -v`.

## ✅ Checklist
- [ ] Ran a Spring Boot server and visited it in a browser
- [ ] Wrote `@GetMapping` methods returning text and JSON
- [ ] Used `@PathVariable`
- [ ] Added an endpoint of my own, with a test
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 55 builds a real REST API: create, read, update and delete, with the right status code for each answer.
