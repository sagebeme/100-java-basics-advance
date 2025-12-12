# Day 44 - Advanced Web Scraping

## 📚 Learning Objectives
- Master advanced scraping techniques
- Handle dynamic content
- Work with multiple pages
- Implement pagination
- Build robust scrapers

## 🎯 Topics Covered
- Pagination handling
- Multiple page scraping
- Data cleaning
- Rate limiting
- Error recovery
- Scraping best practices

## 📝 Step-by-Step Instructions

### Step 1: Pagination
Handle multiple pages:

```java
public class PaginatedScraper {
    public List<Item> scrapeAllPages(String baseUrl) {
        List<Item> allItems = new ArrayList<>();
        int page = 1;
        boolean hasMore = true;
        
        while (hasMore) {
            String url = baseUrl + "?page=" + page;
            List<Item> items = scrapePage(url);
            
            if (items.isEmpty()) {
                hasMore = false;
            } else {
                allItems.addAll(items);
                page++;
                
                // Rate limiting
                try {
                    Thread.sleep(1000); // Wait 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return allItems;
    }
}
```

### Step 2: Data Cleaning
Clean extracted data:

```java
public class DataCleaner {
    public String cleanText(String text) {
        // Remove extra whitespace
        text = text.trim().replaceAll("\\s+", " ");
        
        // Remove HTML entities
        text = text.replace("&nbsp;", " ");
        text = text.replace("&amp;", "&");
        
        // Remove special characters if needed
        text = text.replaceAll("[^\\w\\s]", "");
        
        return text;
    }
    
    public double parsePrice(String priceText) {
        // Extract number from "$99.99" or "99.99 USD"
        String cleaned = priceText.replaceAll("[^\\d.]", "");
        return Double.parseDouble(cleaned);
    }
}
```

### Step 3: Error Handling
Robust error handling:

```java
public class RobustScraper {
    public Document fetchWithRetry(String url, int maxRetries) {
        for (int i = 0; i < maxRetries; i++) {
            try {
                return Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .get();
            } catch (IOException e) {
                if (i == maxRetries - 1) {
                    throw new RuntimeException("Failed after " + maxRetries + " attempts", e);
                }
                try {
                    Thread.sleep(2000 * (i + 1)); // Exponential backoff
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return null;
    }
}
```

### Step 4: Save Data
Save scraped data:

```java
public void saveToCSV(List<Item> items, String filename) {
    try {
        FileWriter writer = new FileWriter(filename);
        writer.write("Title,Price,Link\n");
        
        for (Item item : items) {
            writer.write(String.format("%s,%.2f,%s\n",
                item.getTitle(),
                item.getPrice(),
                item.getLink()));
        }
        
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

## 💻 Exercises

### Exercise 1: Pagination
Implement:
- Page navigation
- Next page detection
- Page number extraction
- Complete site scraping

### Exercise 2: Data Processing
Process:
- Text cleaning
- Data normalization
- Format conversion
- Validation

### Exercise 3: Robust Scraping
Build:
- Retry mechanisms
- Error recovery
- Rate limiting
- Logging

## 🎮 Project: E-commerce Price Tracker

### Requirements
Create price tracking scraper:
1. Scrape product pages
2. Extract product names
3. Extract prices
4. Handle pagination
5. Save to CSV
6. Compare prices over time
7. Error handling and retries

### Example Output
```
Scraping products...

Page 1: 20 products found
Page 2: 20 products found
Page 3: 15 products found

Total: 55 products scraped
Saved to products.csv
```

### Starter Code
```java
public class PriceTracker {
    public void trackPrices(String baseUrl) {
        List<Product> products = new ArrayList<>();
        int page = 1;
        
        while (true) {
            String url = baseUrl + "/page/" + page;
            List<Product> pageProducts = scrapeProducts(url);
            
            if (pageProducts.isEmpty()) break;
            
            products.addAll(pageProducts);
            page++;
            
            // Rate limiting
            sleep(1000);
        }
        
        saveToCSV(products, "products.csv");
    }
}
```

## 📚 Resources

### Official Documentation
- [Jsoup Documentation](https://jsoup.org/)
- [Web Scraping Best Practices](https://www.scrapehero.com/web-scraping-best-practices/)

### Tutorials
- [Advanced Jsoup](https://jsoup.org/cookbook/)
- [Web Scraping Ethics](https://www.scrapehero.com/web-scraping-ethics/)

### Video Resources
- [Advanced Web Scraping](https://www.youtube.com/results?search_query=advanced+web+scraping+java)
- [Pagination Scraping](https://www.youtube.com/results?search_query=scraping+multiple+pages)

### Practice Platforms
- [Scraping Challenges](https://www.scrapehero.com/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Pagination | Multiple pages | Loop through pages |
| Data Cleaning | Normalize data | Remove whitespace, format |
| Rate Limiting | Control requests | `Thread.sleep()` |
| Error Recovery | Handle failures | Retry with backoff |
| Data Persistence | Save results | CSV, JSON files |

## ✅ Checklist
- [ ] Can handle pagination
- [ ] Can clean data
- [ ] Can implement retries
- [ ] Can limit request rate
- [ ] Can save scraped data
- [ ] Completed Price Tracker
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 44, you should be able to:
- Build advanced scrapers
- Handle complex scenarios
- Process large datasets
- Create production-ready tools

**Ready for Day 45?** You'll integrate with Spotify API!

