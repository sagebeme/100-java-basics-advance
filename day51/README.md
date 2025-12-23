# Day 51 - Internet Speed Twitter Bot

## 📚 Learning Objectives
- Measure internet speed programmatically
- Integrate with speed test APIs
- Automate Twitter posting
- Handle API rate limits
- Build automated monitoring tools

## 🎯 Topics Covered
- Internet speed testing
- Twitter API integration
- Automated posting
- Data formatting
- Scheduling tasks
- Error handling

## 📝 Step-by-Step Instructions

### Step 1: Speed Test Integration
Measure internet speed:

```java
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class SpeedTest {
    public SpeedResult testSpeed() {
        long startTime = System.currentTimeMillis();
        
        // Download test file
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://example.com/test-file"))
            .GET()
            .build();
        
        try {
            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
            long endTime = System.currentTimeMillis();
            
            long duration = endTime - startTime;
            double speedMbps = calculateSpeed(response.body().length(), duration);
            
            return new SpeedResult(speedMbps, duration);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private double calculateSpeed(long bytes, long milliseconds) {
        double seconds = milliseconds / 1000.0;
        double bits = bytes * 8;
        return (bits / seconds) / 1_000_000; // Convert to Mbps
    }
}
```

### Step 2: Twitter API Setup
Set up Twitter API:

```java
public class TwitterBot {
    private String bearerToken;
    
    public void postTweet(String text) {
        String url = "https://api.twitter.com/2/tweets";
        
        String jsonBody = String.format("{\"text\":\"%s\"}", 
            text.replace("\"", "\\\""));
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Authorization", "Bearer " + bearerToken)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();
        
        // Send request and handle response
    }
}
```

### Step 3: Format Speed Tweet
Create tweet message:

```java
public String formatSpeedTweet(SpeedResult result) {
    return String.format(
        "Internet Speed Test Results:\n" +
        "Download: %.2f Mbps\n" +
        "Upload: %.2f Mbps\n" +
        "Ping: %d ms\n" +
        "Time: %s",
        result.getDownloadSpeed(),
        result.getUploadSpeed(),
        result.getPing(),
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
    );
}
```

### Step 4: Scheduled Testing
Automate speed tests:

```java
public class SpeedMonitor {
    private SpeedTest speedTest;
    private TwitterBot twitterBot;
    
    public void startMonitoring(int intervalHours) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        scheduler.scheduleAtFixedRate(() -> {
            SpeedResult result = speedTest.testSpeed();
            if (result != null) {
                String tweet = formatSpeedTweet(result);
                twitterBot.postTweet(tweet);
            }
        }, 0, intervalHours, TimeUnit.HOURS);
    }
}
```

## 💻 Exercises

### Exercise 1: Speed Testing
Implement:
- Download speed test
- Upload speed test
- Ping measurement
- Result formatting

### Exercise 2: Twitter Integration
Practice with:
- Posting tweets
- Formatting messages
- Handling errors
- Rate limiting

### Exercise 3: Automation
Build:
- Scheduled tests
- Automatic posting
- Error recovery
- Logging

## 🎮 Project: Internet Speed Twitter Bot

### Requirements
Create automated speed test bot:
1. Test internet speed periodically
2. Format results nicely
3. Post to Twitter automatically
4. Handle API errors
5. Schedule regular tests
6. Log results

### Example Output
```
Internet Speed Test Results:
Download: 45.23 Mbps
Upload: 12.45 Mbps
Ping: 15 ms
Time: 2024-12-01 14:30

Posted to Twitter successfully!
```

### Starter Code
```java
public class InternetSpeedBot {
    public static void main(String[] args) {
        SpeedTest speedTest = new SpeedTest();
        TwitterBot twitterBot = new TwitterBot();
        
        // Test speed
        SpeedResult result = speedTest.testSpeed();
        
        // Format and post
        String tweet = formatTweet(result);
        twitterBot.postTweet(tweet);
    }
}
```

## 📚 Resources

### Official Documentation
- [Twitter API v2](https://developer.twitter.com/en/docs/twitter-api)
- [Speed Test APIs](https://www.speedtest.net/api)

### Tutorials
- [Twitter API Java](https://developer.twitter.com/en/docs)
- [Internet Speed Testing](https://www.geeksforgeeks.org/)

### Video Resources
- [Twitter Bot Tutorial](https://www.youtube.com/results?search_query=twitter+bot+java)
- [Speed Test Automation](https://www.youtube.com/results?search_query=internet+speed+test+java)

### Practice Platforms
- [Twitter Developer](https://developer.twitter.com/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Speed Testing | Measure internet speed | Download/upload tests |
| Twitter API | Social media API | Post tweets |
| Automation | Scheduled tasks | Regular speed tests |
| Data Formatting | Format results | Create tweet messages |
| Rate Limiting | API limits | Handle 429 errors |

## ✅ Checklist
- [ ] Can test internet speed
- [ ] Can use Twitter API
- [ ] Can format tweets
- [ ] Can schedule tasks
- [ ] Can handle errors
- [ ] Completed Speed Twitter Bot
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 51, you should be able to:
- Measure internet speed
- Integrate with Twitter
- Automate social media
- Build monitoring tools

**Ready for Day 52?** You'll build an Instagram Automation Bot!






