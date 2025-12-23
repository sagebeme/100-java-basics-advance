# Day 46 - Automated Price Tracker

## 📚 Learning Objectives
- Build automated price monitoring
- Track price changes
- Send price alerts
- Store price history
- Create monitoring tools

## 🎯 Topics Covered
- Price scraping
- Price comparison
- Database storage
- Alert systems
- Scheduled tasks
- Data visualization

## 📝 Step-by-Step Instructions

### Step 1: Price Scraping
Extract prices from websites:

```java
public class PriceScraper {
    public double getPrice(String url) {
        try {
            Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .get();
            
            // Find price element (adjust selector)
            Element priceElement = doc.selectFirst(".price, [data-price]");
            String priceText = priceElement.text();
            
            // Extract number
            return parsePrice(priceText);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    private double parsePrice(String text) {
        // Remove currency symbols and extract number
        String cleaned = text.replaceAll("[^\\d.]", "");
        return Double.parseDouble(cleaned);
    }
}
```

### Step 2: Price Storage
Store prices in database:

```java
public class PriceDatabase {
    public void savePrice(String productUrl, double price, LocalDateTime timestamp) {
        String sql = "INSERT INTO prices (url, price, timestamp) VALUES (?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productUrl);
            pstmt.setDouble(2, price);
            pstmt.setString(3, timestamp.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public double getLastPrice(String productUrl) {
        String sql = "SELECT price FROM prices WHERE url = ? ORDER BY timestamp DESC LIMIT 1";
        // Execute query and return price
    }
}
```

### Step 3: Price Comparison
Compare and detect changes:

```java
public class PriceTracker {
    public void checkPrice(String url, double targetPrice) {
        double currentPrice = scraper.getPrice(url);
        double lastPrice = database.getLastPrice(url);
        
        database.savePrice(url, currentPrice, LocalDateTime.now());
        
        if (lastPrice > 0) {
            double change = currentPrice - lastPrice;
            double percentChange = (change / lastPrice) * 100;
            
            if (currentPrice <= targetPrice) {
                sendAlert(url, currentPrice, "Price dropped to target!");
            } else if (percentChange < -10) {
                sendAlert(url, currentPrice, "Price dropped by " + percentChange + "%");
            }
        }
    }
}
```

### Step 4: Scheduled Monitoring
Automate price checks:

```java
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AutomatedTracker {
    private ScheduledExecutorService scheduler;
    
    public void startMonitoring(String url, double targetPrice, int intervalHours) {
        scheduler = Executors.newScheduledThreadPool(1);
        
        scheduler.scheduleAtFixedRate(() -> {
            tracker.checkPrice(url, targetPrice);
        }, 0, intervalHours, TimeUnit.HOURS);
    }
}
```

## 💻 Exercises

### Exercise 1: Price Scraping
Scrape prices from:
- E-commerce sites
- Different product pages
- Handle different formats
- Error handling

### Exercise 2: Price Tracking
Track:
- Price history
- Price changes
- Trends
- Statistics

### Exercise 3: Alert System
Implement:
- Price drop alerts
- Target price alerts
- Email notifications
- SMS notifications

## 🎮 Project: Amazon Price Tracker

### Requirements
Create automated price tracker:
1. Track product URLs
2. Check prices periodically
3. Store price history
4. Detect price drops
5. Send email alerts
6. Web interface (optional)

### Example Output
```
Price Tracker

Monitoring: https://amazon.com/product/123

Current Price: $99.99
Last Price: $129.99
Change: -$30.00 (-23.1%)

Price History:
2024-12-01: $129.99
2024-12-02: $99.99 ⬇️

Alert sent: Price dropped below $100!
```

### Starter Code
```java
public class PriceTrackerApp {
    public static void main(String[] args) {
        PriceTracker tracker = new PriceTracker();
        
        // Add products to track
        tracker.addProduct("https://amazon.com/product1", 50.0);
        tracker.addProduct("https://amazon.com/product2", 100.0);
        
        // Start monitoring
        tracker.startMonitoring(6); // Check every 6 hours
    }
}
```

## 📚 Resources

### Official Documentation
- [Jsoup Documentation](https://jsoup.org/)
- [SQLite Documentation](https://www.sqlite.org/docs.html)
- [ScheduledExecutorService - Java API](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html)

### Tutorials
- [Price Tracking Tutorial](https://www.geeksforgeeks.org/)
- [Scheduled Tasks Java](https://www.baeldung.com/java-executor-service-tutorial)

### Video Resources
- [Price Tracker Tutorial](https://www.youtube.com/results?search_query=price+tracker+java)
- [Web Scraping Automation](https://www.youtube.com/results?search_query=web+scraping+automation+java)

### Practice Platforms
- [Automation Projects](https://github.com/karan/Projects#automation)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Price Scraping | Extract prices | Parse HTML |
| Price History | Track over time | Database storage |
| Price Comparison | Detect changes | Calculate differences |
| Scheduled Tasks | Automated checks | `ScheduledExecutorService` |
| Alert System | Notify users | Email/SMS alerts |

## ✅ Checklist
- [ ] Can scrape prices
- [ ] Can store price history
- [ ] Can compare prices
- [ ] Can schedule tasks
- [ ] Can send alerts
- [ ] Completed Price Tracker
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 46, you should be able to:
- Build automated trackers
- Monitor prices
- Send alerts
- Create monitoring tools

**Ready for Day 47?** You'll learn Selenium WebDriver automation!







