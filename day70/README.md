# Day 70 - Deploying with Docker and Cloud

## 📚 Learning Objectives
- Containerize applications with Docker
- Deploy to cloud platforms
- Configure production environments
- Set up CI/CD
- Deploy Spring Boot apps

## 🎯 Topics Covered
- Docker containers
- Dockerfile creation
- Cloud deployment
- CI/CD pipelines
- Production configuration
- Environment variables

## 📝 Step-by-Step Instructions

### Step 1: Dockerfile
Create Dockerfile:

```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/myapp.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Step 2: Docker Compose
Create docker-compose.yml:

```yaml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
  database:
    image: postgres:15
    environment:
      - POSTGRES_DB=myapp
      - POSTGRES_USER=user
      - POSTGRES_PASSWORD=password
```

### Step 3: Build and Run
Build and deploy:

```bash
docker build -t myapp .
docker run -p 8080:8080 myapp
```

## 🎮 Project: Deploy Application

### Requirements
Deploy application:
1. Create Dockerfile
2. Build Docker image
3. Deploy to cloud
4. Configure environment
5. Set up monitoring

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- Re-read **Step 1** (Dockerfile: FROM, COPY, ENTRYPOINT); **Step 2** (docker-compose: services, ports, env); **Step 3** (build & run)
- **Related days:** Day 69 (Blog/users); Day 71 (data analysis). **Quick reference:** `docker build -t myapp .` then `docker run -p 8080:8080 myapp`

## ✅ Checklist
- [ ] Created Dockerfile
- [ ] Built Docker image
- [ ] Deployed to cloud
- [ ] Configured environment
- [ ] Set up CI/CD
- [ ] Completed deployment
- [ ] Committed code to Git

## 💻 How to Run

**Docker:** `docker build -t myapp .` then `docker run -p 8080:8080 myapp`. Or run Spring Boot from IDE: `mvn spring-boot:run`.

## 🚀 Next Steps
**Ready for Day 71?** You'll learn data analysis with Java!






