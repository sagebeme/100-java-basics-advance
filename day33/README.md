# Day 33 - REST API Endpoints and HTTP Client

## 📚 Learning Objectives
- Understand REST APIs
- Make HTTP requests
- Parse JSON responses
- Work with API endpoints
- Handle API errors

## 🎯 Topics Covered
- REST API concepts
- HTTP methods (GET, POST, PUT, DELETE)
- HTTP client (HttpClient)
- JSON parsing
- API authentication
- Error handling

## 📝 Step-by-Step Instructions

### Step 1: HTTP Client Setup
Use Java 11+ HttpClient:

```java
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

HttpClient client = HttpClient.newHttpClient();
```

### Step 2: GET Request
Make GET request:

```java
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/data"))
    .GET()
    .build();

HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());

System.out.println(response.body());
```

### Step 3: POST Request
Make POST request with JSON:

```java
String jsonBody = "{\"name\":\"John\",\"age\":30}";

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .header("Content-Type", "application/json")
    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
    .build();

HttpResponse<String> response = client.send(request,
    HttpResponse.BodyHandlers.ofString());
```

### Step 4: Parse JSON Response
Parse API response:

```java
import com.google.gson.Gson;
import com.google.gson.JsonObject;

Gson gson = new Gson();
JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
String value = jsonObject.get("key").getAsString();
```

## 💻 Exercises

### Exercise 1: API Calls
Create programs that:
- Make GET requests
- Make POST requests
- Handle responses
- Parse JSON data

### Exercise 2: API Integration
Integrate with:
- Public APIs
- Weather APIs
- News APIs
- Quote APIs

### Exercise 3: Error Handling
Handle:
- Network errors
- API errors
- Invalid responses
- Timeout errors

## 🎮 Project: ISS Overhead Notifier & Kanye Quotes

### Requirements
Create two API projects:

**Project 1: ISS Overhead Notifier**
1. Get ISS location from API
2. Get your location
3. Check if ISS is overhead
4. Send notification if overhead

**Project 2: Kanye Quotes**
1. Fetch quote from Kanye API
2. Display quote
3. Get new quote on button click
4. Format quote nicely

### Example APIs
- ISS API: `http://api.open-notify.org/iss-now.json`
- Kanye API: `https://api.kanye.rest`

### Starter Code
```java
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class APIClient {
    private HttpClient client;
    
    public APIClient() {
        client = HttpClient.newHttpClient();
    }
    
    public String getData(String url) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();
        
        try {
            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
```

## 📚 Resources

### Official Documentation
- [HTTP Client - Oracle Docs](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html)
- [REST API Tutorial](https://restfulapi.net/)
- [JSON.org](https://www.json.org/)

### Tutorials
- [Java HTTP Client - Baeldung](https://www.baeldung.com/java-9-http-client)
- [REST API in Java](https://www.geeksforgeeks.org/rest-api-in-java/)
- [API Integration](https://www.baeldung.com/rest-template)

### Video Resources
- [Java HTTP Client Tutorial](https://www.youtube.com/results?search_query=java+http+client+tutorial)
- [REST API Java](https://www.youtube.com/results?search_query=rest+api+java+tutorial)

### Practice Platforms
- [Public APIs](https://github.com/public-apis/public-apis)
- [API Practice](https://jsonplaceholder.typicode.com/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| REST API | Web service interface | HTTP endpoints |
| HTTP Client | Make requests | `HttpClient.newHttpClient()` |
| GET Request | Retrieve data | `.GET()` |
| POST Request | Send data | `.POST(body)` |
| JSON Parsing | Extract data | `Gson.fromJson()` |

## ✅ Checklist
- [ ] Understand REST APIs
- [ ] Can make HTTP requests
- [ ] Can parse JSON responses
- [ ] Can handle API errors
- [ ] Can integrate APIs
- [ ] Completed API projects
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 33, you should be able to:
- Work with REST APIs
- Make HTTP requests
- Parse API responses
- Build API-integrated apps

**Ready for Day 34?** You'll create a GUI Quiz App using APIs!


