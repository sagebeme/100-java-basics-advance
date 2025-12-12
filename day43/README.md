# Day 43 - Web Scraping with Jsoup

## 📚 Learning Objectives
- Understand web scraping
- Use Jsoup library
- Parse HTML documents
- Extract data from websites
- Handle web scraping challenges

## 🎯 Topics Covered
- Jsoup library
- HTML parsing
- Element selection
- Data extraction
- Web scraping ethics
- Error handling

## 📝 Step-by-Step Instructions

### Step 1: Jsoup Setup
Add Jsoup dependency:

**pom.xml:**
```xml
<dependency>
    <groupId>org.jsoup</groupId>
    <artifactId>jsoup</artifactId>
    <version>1.17.2</version>
</dependency>
```

### Step 2: Connect and Parse
Load HTML document:

```java
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// From URL
Document doc = Jsoup.connect("https://example.com")
    .userAgent("Mozilla/5.0")
    .get();

// From HTML string
String html = "<html><body><p>Hello</p></body></html>";
Document doc = Jsoup.parse(html);

// From file
File file = new File("page.html");
Document doc = Jsoup.parse(file, "UTF-8");
```

### Step 3: Select Elements
Use CSS selectors:

```java
// By tag
Elements paragraphs = doc.select("p");

// By class
Elements items = doc.select(".item");

// By ID
Element header = doc.selectFirst("#header");

// By attribute
Elements links = doc.select("a[href]");

// Complex selectors
Elements titles = doc.select("div.content > h2.title");
```

### Step 4: Extract Data
Get text and attributes:

```java
// Get text
String text = element.text();

// Get HTML
String html = element.html();

// Get attribute
String href = element.attr("href");
String src = element.attr("src");

// Get all elements
for (Element item : items) {
    String title = item.select("h3").text();
    String link = item.select("a").attr("href");
    System.out.println(title + " - " + link);
}
```

## 💻 Exercises

### Exercise 1: Basic Scraping
Scrape:
- Headings and paragraphs
- Links and images
- Lists
- Tables

### Exercise 2: Data Extraction
Extract:
- News headlines
- Product prices
- Article content
- Structured data

### Exercise 3: Complex Scraping
Handle:
- Multiple pages
- Pagination
- Dynamic content
- Error handling

## 🎮 Project: News Headlines Scraper

### Requirements
Create a news scraper:
1. Connect to news website
2. Extract headlines
3. Extract article links
4. Extract summaries
5. Save to file
6. Handle errors gracefully

### Example Output
```
News Headlines:

1. Breaking: Major Event Happens
   Link: https://example.com/article1
   Summary: Brief description...

2. Technology Advances
   Link: https://example.com/article2
   Summary: Brief description...
```

### Starter Code
```java
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsScraper {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://news.example.com")
                .userAgent("Mozilla/5.0")
                .get();
            
            Elements articles = doc.select(".article");
            
            for (Element article : articles) {
                String title = article.select("h2").text();
                String link = article.select("a").attr("href");
                String summary = article.select("p.summary").text();
                
                System.out.println(title);
                System.out.println(link);
                System.out.println(summary);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## 📚 Resources

### Official Documentation
- [Jsoup Documentation](https://jsoup.org/)
- [Jsoup Cookbook](https://jsoup.org/cookbook/)
- [CSS Selectors - MDN](https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Selectors)

### Tutorials
- [Jsoup Tutorial - Baeldung](https://www.baeldung.com/java-with-jsoup)
- [Web Scraping Java](https://www.geeksforgeeks.org/web-scraping-in-java-with-jsoup/)
- [Jsoup Examples](https://jsoup.org/cookbook/)

### Video Resources
- [Jsoup Tutorial](https://www.youtube.com/results?search_query=jsoup+tutorial)
- [Web Scraping Java](https://www.youtube.com/results?search_query=web+scraping+java)

### Practice Platforms
- [Web Scraping Exercises](https://www.scrapehero.com/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Web Scraping | Extract web data | Parse HTML |
| Jsoup | HTML parser | `Jsoup.connect()` |
| CSS Selectors | Find elements | `.class`, `#id` |
| Element | HTML element | `Element`, `Elements` |
| Data Extraction | Get content | `.text()`, `.attr()` |

## ✅ Checklist
- [ ] Understand web scraping
- [ ] Can use Jsoup
- [ ] Can parse HTML
- [ ] Can extract data
- [ ] Can handle errors
- [ ] Completed News Scraper
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 43, you should be able to:
- Scrape websites
- Extract data from HTML
- Parse complex structures
- Build data collection tools

**Ready for Day 44?** You'll learn advanced web scraping techniques!


