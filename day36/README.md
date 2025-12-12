# Day 36 - Stock Trading News Alert Project

## 📚 Learning Objectives
- Integrate multiple APIs
- Work with financial data
- Send notifications
- Process news data
- Build automated alerts

## 🎯 Topics Covered
- API integration
- Data processing
- Notification systems
- News APIs
- Stock APIs
- Automation

## 📝 Step-by-Step Instructions

### Step 1: Stock API
Get stock prices:

```java
public class StockAPI {
    private static final String API_KEY = System.getenv("STOCK_API_KEY");
    private static final String BASE_URL = "https://www.alphavantage.co/query";
    
    public double getStockPrice(String symbol) {
        String url = BASE_URL + "?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + API_KEY;
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();
        
        try {
            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
            
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(response.body(), JsonObject.class);
            JsonObject quote = json.getAsJsonObject("Global Quote");
            return quote.get("05. price").getAsDouble();
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
```

### Step 2: News API
Get news articles:

```java
public class NewsAPI {
    private static final String API_KEY = System.getenv("NEWS_API_KEY");
    private static final String BASE_URL = "https://newsapi.org/v2/everything";
    
    public List<Article> getNews(String query) {
        String url = BASE_URL + "?q=" + query + "&apiKey=" + API_KEY;
        
        // Make HTTP request and parse response
        // Return list of articles
    }
}
```

### Step 3: Alert System
Send alerts:

```java
public class AlertSystem {
    public void sendAlert(String message) {
        // Send email
        EmailSender.sendEmail("user@example.com", "Stock Alert", message);
        
        // Or send SMS
        SMSSender.sendSMS("+1234567890", message);
    }
}
```

### Step 4: Main Logic
Combine everything:

```java
public class StockAlert {
    public void checkStock(String symbol) {
        double price = stockAPI.getStockPrice(symbol);
        double changePercent = calculateChange(price);
        
        if (Math.abs(changePercent) > 5) { // 5% change
            List<Article> news = newsAPI.getNews(symbol);
            String alert = formatAlert(symbol, price, changePercent, news);
            alertSystem.sendAlert(alert);
        }
    }
}
```

## 💻 Exercises

### Exercise 1: API Integration
Practice with:
- Multiple API calls
- Data aggregation
- Error handling
- Rate limiting

### Exercise 2: Data Processing
Process:
- Stock data
- News data
- Calculate metrics
- Format output

### Exercise 3: Notification Systems
Implement:
- Email notifications
- SMS notifications
- Multiple channels
- Alert formatting

## 🎮 Project: Stock Trading News Alert

### Requirements
Create stock alert system:
1. Get stock price from API
2. Calculate price change
3. Get related news articles
4. Send alert if significant change
5. Format alert with price and news
6. Support multiple stocks

### Example Output
```
Stock Alert: TSLA

Price: $250.50
Change: +5.2%

Recent News:
- Tesla announces new model
- Stock surges on earnings report
```

### Starter Code
```java
public class StockNewsAlert {
    private StockAPI stockAPI;
    private NewsAPI newsAPI;
    private AlertSystem alertSystem;
    
    public void monitorStock(String symbol) {
        // Get stock data
        // Get news
        // Check conditions
        // Send alert
    }
}
```

## 📚 Resources

### Official Documentation
- [Alpha Vantage API](https://www.alphavantage.co/documentation/)
- [News API](https://newsapi.org/docs)
- [HTTP Client - Java API](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/)

### Tutorials
- [Stock API Integration](https://www.geeksforgeeks.org/stock-price-analysis-using-java/)
- [News API Tutorial](https://newsapi.org/docs)

### Video Resources
- [Stock API Java](https://www.youtube.com/results?search_query=stock+api+java)
- [News API Integration](https://www.youtube.com/results?search_query=news+api+java)

### Practice Platforms
- [Financial APIs](https://github.com/public-apis/public-apis#finance)
- [News APIs](https://github.com/public-apis/public-apis#news)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| API Integration | Connect multiple APIs | Stock + News APIs |
| Data Aggregation | Combine data sources | Price + News |
| Alert System | Send notifications | Email/SMS alerts |
| Automation | Scheduled checks | Monitor stocks |
| Data Processing | Analyze data | Calculate changes |

## ✅ Checklist
- [ ] Can integrate multiple APIs
- [ ] Can process financial data
- [ ] Can send notifications
- [ ] Can automate alerts
- [ ] Can format alerts
- [ ] Completed Stock Alert project
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 36, you should be able to:
- Integrate multiple APIs
- Process real-world data
- Build automated systems
- Create alert systems

**Ready for Day 37?** You'll build a Habit Tracking application!

