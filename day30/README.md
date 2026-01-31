# Day 30 - Exceptions and JSON Data: Improving the Password Manager

## 📚 Learning Objectives
- Master exception handling
- Work with JSON data
- Improve error handling
- Use JSON libraries
- Create robust applications

## 🎯 Topics Covered
- Advanced exception handling
- JSON format
- JSON parsing and generation
- Error recovery
- Input validation
- Data serialization

## 📝 Step-by-Step Instructions

### Step 1: Advanced Exception Handling
Handle multiple exception types:

```java
try {
    // Code that might throw exceptions
    File file = new File("data.json");
    Scanner scanner = new Scanner(file);
    // Process file
    scanner.close();
} catch (FileNotFoundException e) {
    System.out.println("File not found: " + e.getMessage());
    // Create default file
} catch (IOException e) {
    System.out.println("IO Error: " + e.getMessage());
} catch (Exception e) {
    System.out.println("Unexpected error: " + e.getMessage());
    e.printStackTrace();
} finally {
    // Cleanup code
}
```

### Step 2: JSON with Gson Library
Add Gson dependency and use it:

**pom.xml:**
```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
```

**Using Gson:**
```java
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

// Convert object to JSON
Gson gson = new Gson();
String json = gson.toJson(passwordData);

// Parse JSON to object
PasswordData data = gson.fromJson(json, PasswordData.class);

// Parse JSON array
Type listType = new TypeToken<List<PasswordData>>(){}.getType();
List<PasswordData> passwords = gson.fromJson(json, listType);
```

### Step 3: Data Model
Create class for password data:

```java
public class PasswordData {
    private String website;
    private String email;
    private String password;
    
    // Constructors, getters, setters
    public PasswordData(String website, String email, String password) {
        this.website = website;
        this.email = email;
        this.password = password;
    }
    
    // Getters and setters
}
```

### Step 4: JSON File Operations
Read and write JSON:

```java
public void saveToJson(List<PasswordData> passwords) {
    try {
        Gson gson = new Gson();
        String json = gson.toJson(passwords);
        
        FileWriter writer = new FileWriter("passwords.json");
        writer.write(json);
        writer.close();
    } catch (IOException e) {
        System.out.println("Error saving JSON: " + e.getMessage());
    }
}

public List<PasswordData> loadFromJson() {
    try {
        Gson gson = new Gson();
        FileReader reader = new FileReader("passwords.json");
        
        Type listType = new TypeToken<List<PasswordData>>(){}.getType();
        List<PasswordData> passwords = gson.fromJson(reader, listType);
        reader.close();
        
        return passwords != null ? passwords : new ArrayList<>();
    } catch (FileNotFoundException e) {
        return new ArrayList<>(); // Return empty list if file doesn't exist
    } catch (IOException e) {
        System.out.println("Error loading JSON: " + e.getMessage());
        return new ArrayList<>();
    }
}
```

## 💻 Exercises

### Exercise 1: Exception Handling
Practice with:
- Try-catch-finally blocks
- Multiple catch blocks
- Custom exceptions
- Exception propagation

### Exercise 2: JSON Operations
Work with:
- Converting objects to JSON
- Parsing JSON to objects
- JSON arrays
- Nested JSON structures

### Exercise 3: Error Recovery
Implement:
- Graceful error handling
- Default values
- User-friendly error messages
- Data validation

## 🎮 Project: Improved Password Manager

### Requirements
Enhance password manager with:
1. JSON file storage (instead of text)
2. Better exception handling
3. Input validation
4. Error recovery
5. Data structure improvements
6. Search and filter features

### Example JSON Structure
```json
[
  {
    "website": "google.com",
    "email": "user@gmail.com",
    "password": "securePassword123"
  },
  {
    "website": "github.com",
    "email": "username",
    "password": "anotherPassword"
  }
]
```

### Starter Code
```java
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class ImprovedPasswordManager {
    private List<PasswordData> passwords;
    private Gson gson;
    
    public ImprovedPasswordManager() {
        gson = new Gson();
        passwords = loadFromJson();
    }
    
    public void addPassword(String website, String email, String password) {
        // Validate input
        // Add to list
        // Save to JSON
    }
    
    public List<PasswordData> search(String query) {
        // Search and return results
    }
}
```

## 📚 Resources

### Official Documentation
- [Exceptions - Oracle Docs](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
- [Gson Library](https://github.com/google/gson)
- [JSON Format](https://www.json.org/)

### Tutorials
- [Java Exception Handling - W3Schools](https://www.w3schools.com/java/java_try_catch.asp)
- [Gson Tutorial - Baeldung](https://www.baeldung.com/gson-json)
- [JSON in Java - GeeksforGeeks](https://www.geeksforgeeks.org/parse-json-java/)

### Video Resources
- [Java Exception Handling](https://www.youtube.com/results?search_query=java+exception+handling)
- [Gson JSON Tutorial](https://www.youtube.com/results?search_query=gson+json+java)

### Practice Platforms
- [Exception Handling Exercises](https://www.w3resource.com/java-exercises/exception-handling/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Exception Handling | Handle errors | `try-catch-finally` |
| JSON | Data format | `{"key": "value"}` |
| Gson | JSON library | `Gson gson = new Gson()` |
| Serialization | Object to JSON | `gson.toJson(object)` |
| Deserialization | JSON to object | `gson.fromJson(json, Class)` |

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- **Try-catch:** Re-read **Step 1** — catch specific then general; use `finally` for cleanup — see **Step 2**
- **Gson:** Add dependency; `Gson gson = new Gson();` — `toJson(obj)` and `fromJson(json, Class)` — see **Step 2**
- **Lists:** For List use `Type type = TypeToken.getParameterized(List.class, Item.class).getType(); gson.fromJson(json, type);`
- **Starter vs solution:** See `_start/README.md` for common challenges; see `_end/README.md` for JSON + exceptions

**Related days:** Day 13 (exceptions); Day 24 (file I/O); Day 31 (FlashCard JSON). **Quick reference:** Gson JAR on classpath or Maven · Validate input before parse

## ✅ Checklist
- [ ] Understand advanced exception handling
- [ ] Can work with JSON
- [ ] Can use Gson library
- [ ] Can validate input
- [ ] Can recover from errors
- [ ] Completed Improved Password Manager
- [ ] Committed code to Git

## 📂 Code Examples

This day includes starter and completed code examples:
- **`_start/`** - Starting code with TODO comments and learning notes
- **`_end/`** - Completed solutions with detailed explanations

Check these folders to see the progression from start to finish and learn from the learning curve notes!

## 💻 How to Run Java Files

### Prerequisites
Make sure you have Java installed on your system:
- **Check installation**: Open terminal/command prompt and run `java -version`
- **If not installed**: Download JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)

### Running Java Files

#### **Windows**

1. **Open Command Prompt or PowerShell**
   - Press `Win + R`, type `cmd` or `powershell`, press Enter

2. **Navigate to the project directory**
   ```cmd
   cd "path\to\100-java-basics-advance\day30\_end\src"
   ```

3. **Compile** (if using Gson/Maven: use `mvn compile exec:java` or run from IDE)
   ```cmd
   javac ImprovedPasswordManager.java
   ```

4. **Run the compiled program**
   ```cmd
   java ImprovedPasswordManager
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to the project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day30/_end/src
   ```

3. **Compile** (if using Gson: ensure gson.jar is in classpath)
   ```bash
   javac ImprovedPasswordManager.java
   ```

4. **Run the compiled program**
   ```bash
   java ImprovedPasswordManager
   ```

### Using an IDE (Recommended)

**IntelliJ IDEA:**
1. Open the project folder in IntelliJ IDEA
2. Right-click on the Java file
3. Select "Run 'ImprovedPasswordManager.main()'"

**VS Code:**
1. Install "Extension Pack for Java"
2. Open the Java file
3. Click "Run" button above `main` method or press `F5`

**Eclipse:**
1. Import project into Eclipse
2. Right-click on the Java file
3. Select "Run As" → "Java Application"

### Troubleshooting

- **"javac: command not found"** - Java is not installed or not in PATH
- **"Error: Could not find or load main class"** - Make sure you're in the correct directory and class name matches filename
- **Gson/JSON**: If using external JARs, add them to classpath: `javac -cp .:gson.jar ImprovedPasswordManager.java`

## 🚀 Next Steps
After completing Day 30, you should be able to:
- Handle exceptions properly
- Work with JSON data
- Build robust applications
- Create data-driven apps

**Ready for Day 31?** You'll build the Flash Card App capstone project!







