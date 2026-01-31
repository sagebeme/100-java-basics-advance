# Day 35 - Environment Variables and Configuration

## 📚 Learning Objectives
- Understand environment variables
- Work with configuration files
- Secure sensitive data
- Use properties files
- Implement configuration management

## 🎯 Topics Covered
- Environment variables
- Properties files
- Configuration management
- Secrets management
- .env files
- System properties

## 📝 Step-by-Step Instructions

### Step 1: Environment Variables
Access environment variables:

```java
// Get environment variable
String apiKey = System.getenv("API_KEY");

// Get with default
String apiKey = System.getenv().getOrDefault("API_KEY", "default-value");

// List all environment variables
Map<String, String> env = System.getenv();
env.forEach((key, value) -> System.out.println(key + " = " + value));
```

### Step 2: Properties Files
Use properties files:

**config.properties:**
```properties
api.key=your-api-key-here
api.url=https://api.example.com
database.host=localhost
database.port=3306
```

**Loading properties:**
```java
import java.io.FileInputStream;
import java.util.Properties;

Properties properties = new Properties();
try {
    FileInputStream input = new FileInputStream("config.properties");
    properties.load(input);
    input.close();
    
    String apiKey = properties.getProperty("api.key");
    String apiUrl = properties.getProperty("api.url");
} catch (IOException e) {
    e.printStackTrace();
}
```

### Step 3: .env Files
Use .env for local development:

**Add dependency (dotenv-java):**
```xml
<dependency>
    <groupId>io.github.cdimascio</groupId>
    <artifactId>dotenv-java</artifactId>
    <version>3.0.0</version>
</dependency>
```

**Using dotenv:**
```java
import io.github.cdimascio.dotenv.Dotenv;

Dotenv dotenv = Dotenv.load();
String apiKey = dotenv.get("API_KEY");
```

### Step 4: System Properties
Use system properties:

```java
// Set system property
System.setProperty("my.property", "value");

// Get system property
String value = System.getProperty("my.property");

// Get with default
String value = System.getProperty("my.property", "default");
```

## 💻 Exercises

### Exercise 1: Environment Variables
Create programs that:
- Read environment variables
- Use environment variables
- Set defaults
- Validate configuration

### Exercise 2: Properties Files
Work with:
- Reading properties
- Writing properties
- Default values
- Nested properties

### Exercise 3: Configuration Management
Create systems for:
- Multiple environments
- Configuration validation
- Secrets management
- Configuration reloading

## 🎮 Project: Send SMS with Configuration

### Requirements
Create SMS sending application:
1. Use environment variables for API keys
2. Load configuration from properties
3. Send SMS using API (Twilio or similar)
4. Secure credential storage
5. Error handling

### Example Configuration
**.env:**
```
TWILIO_ACCOUNT_SID=your-account-sid
TWILIO_AUTH_TOKEN=your-auth-token
TWILIO_PHONE_NUMBER=+1234567890
```

### Starter Code
```java
import io.github.cdimascio.dotenv.Dotenv;

public class SMSSender {
    private String accountSid;
    private String authToken;
    private String phoneNumber;
    
    public SMSSender() {
        Dotenv dotenv = Dotenv.load();
        this.accountSid = dotenv.get("TWILIO_ACCOUNT_SID");
        this.authToken = dotenv.get("TWILIO_AUTH_TOKEN");
        this.phoneNumber = dotenv.get("TWILIO_PHONE_NUMBER");
    }
    
    public void sendSMS(String to, String message) {
        // Send SMS using API
    }
}
```

## 📚 Resources

### Official Documentation
- [System Properties - Oracle Docs](https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html)
- [Properties Class - Java API](https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html)

### Tutorials
- [Environment Variables in Java](https://www.baeldung.com/java-system-get-property-vs-system-getenv)
- [Properties Files - Baeldung](https://www.baeldung.com/java-properties)
- [Configuration Management](https://www.geeksforgeeks.org/java-util-properties-class-java/)

### Video Resources
- [Environment Variables Java](https://www.youtube.com/results?search_query=java+environment+variables)
- [Properties Files Tutorial](https://www.youtube.com/results?search_query=java+properties+file)

### Practice Platforms
- [Configuration Exercises](https://www.w3resource.com/java-exercises/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Environment Variable | System-level config | `System.getenv("KEY")` |
| Properties File | Config file | `config.properties` |
| .env File | Local environment | `.env` file |
| System Properties | JVM properties | `System.getProperty()` |
| Secrets Management | Secure storage | Environment variables |

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- **Env vars:** Re-read **Step 1** — `System.getenv("KEY")` returns null if not set — see **Step 2**
- **Properties:** Re-read **Step 2** — `Properties.load(InputStream)`; `getProperty("key")` — see **Step 2**
- **.env:** Parse manually (read file, split on `=`) or use a small library — see **Step 3**
- **Main day README** → **Step 1 & 2** for config; **Key Concepts** for env vs properties

**Related days:** Day 24 (read config file); Day 32 (email credentials). **Quick reference:** Never commit secrets; use .env in .gitignore

## ✅ Checklist
- [ ] Understand environment variables
- [ ] Can use properties files
- [ ] Can work with .env files
- [ ] Can manage configuration
- [ ] Can secure credentials
- [ ] Completed SMS project
- [ ] Committed code to Git

## 💻 How to Run Java Files

### Prerequisites
Make sure you have Java installed on your system:
- **Check installation**: Open terminal/command prompt and run `java -version`
- **If not installed**: Download JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)

### Running Java Files

#### **Windows**

1. **Open Command Prompt or PowerShell**
   - Press `Win + R`, type `cmd` or `powershell`, press Enter

2. **Navigate to your project directory**
   ```cmd
   cd "path\to\100-java-basics-advance\day35\src"
   ```

3. **Compile** (Twilio/API JARs: use Maven or add to classpath)
   ```cmd
   javac SMSSender.java
   ```

4. **Run**
   ```cmd
   java SMSSender
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to your project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day35/src
   ```

3. **Compile and run**
   ```bash
   javac SMSSender.java
   java SMSSender
   ```

### Using an IDE (Recommended)

**IntelliJ IDEA / Eclipse / VS Code:**
1. Open the project
2. Right-click on the main class
3. Select "Run 'SMSSender.main()'"

### Troubleshooting

- **"javac: command not found"** - Java is not installed or not in PATH
- **API keys**: Use environment variables; never commit secrets

## 🚀 Next Steps
After completing Day 35, you should be able to:
- Manage configuration properly
- Secure sensitive data
- Work with environment variables
- Build configurable applications

**Ready for Day 36?** You'll build a Stock Trading News Alert project!







