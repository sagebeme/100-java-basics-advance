# Multi-stage build: compile with a Maven+JDK image, run with a slim JRE-only image so the
# final image doesn't ship a compiler and build tool it will never use again.

FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
# Run as an ordinary user, not root: if someone breaks into the app, they don't own the container.
RUN useradd --system --uid 10001 app
COPY --from=build /app/target/*.jar app.jar
USER app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
