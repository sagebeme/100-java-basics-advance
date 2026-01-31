# Day 39 - Capstone Part 1: Flight Deal Finder

## 📚 Learning Objectives
- Build complex application
- Integrate flight APIs
- Process large datasets
- Implement search algorithms
- Create data processing pipeline

## 🎯 Topics Covered
- Flight API integration
- Data processing
- Search algorithms
- Price comparison
- Notification systems
- Multi-step workflows

## 📝 Step-by-Step Instructions

### Step 1: Flight API Integration
Get flight data:

```java
public class FlightAPI {
    private static final String API_KEY = System.getenv("FLIGHT_API_KEY");
    private static final String BASE_URL = "https://api.example.com/flights";
    
    public List<Flight> searchFlights(String from, String to, String date) {
        String url = BASE_URL + "?from=" + from + "&to=" + to + "&date=" + date;
        
        // Make API request
        // Parse response
        // Return flight list
    }
    
    public Flight getCheapestFlight(List<Flight> flights) {
        return flights.stream()
            .min(Comparator.comparing(Flight::getPrice))
            .orElse(null);
    }
}
```

### Step 2: Data Model
Design flight data structure:

```java
public class Flight {
    private String from;
    private String to;
    private LocalDate date;
    private double price;
    private String airline;
    private int duration;
    
    // Constructors, getters, setters
}
```

### Step 3: Deal Detection
Find good deals:

```java
public class DealFinder {
    private double maxPrice;
    
    public boolean isGoodDeal(Flight flight) {
        // Check if price is below threshold
        // Check if it's a significant discount
        return flight.getPrice() <= maxPrice;
    }
    
    public List<Flight> findDeals(List<Flight> flights) {
        return flights.stream()
            .filter(this::isGoodDeal)
            .collect(Collectors.toList());
    }
}
```

### Step 4: Notification System
Alert users of deals:

```java
public class DealNotifier {
    public void notifyDeal(Flight flight) {
        String message = String.format(
            "Low price alert! Only $%.2f to fly from %s to %s on %s",
            flight.getPrice(),
            flight.getFrom(),
            flight.getTo(),
            flight.getDate()
        );
        
        EmailSender.sendEmail("user@example.com", "Flight Deal!", message);
    }
}
```

## 💻 Exercises

### Exercise 1: API Integration
Practice with:
- Flight APIs
- Data parsing
- Error handling
- Rate limiting

### Exercise 2: Data Processing
Process:
- Flight data
- Price comparisons
- Filtering
- Sorting

### Exercise 3: Notification Systems
Implement:
- Deal detection
- Alert formatting
- Multiple notification channels
- User preferences

## 🎮 Project: Flight Deal Finder Part 1

### Requirements
Create flight deal finder (Part 1):
1. Integrate flight search API
2. Search flights by route and date
3. Find cheapest flights
4. Detect deals (below threshold)
5. Store user preferences
6. Basic notification system

### Example Output
```
Flight Deal Finder

Searching flights from LON to PAR on 2024-12-25...

Found 15 flights
Cheapest: $250.00

Deals Found:
- Flight ABC123: $200.00 (20% below average)
- Flight XYZ789: $220.00 (12% below average)

Sending notifications...
```

### Starter Code
```java
public class FlightDealFinder {
    private FlightAPI flightAPI;
    private DealFinder dealFinder;
    private DealNotifier notifier;
    
    public void searchDeals(String from, String to, String date) {
        List<Flight> flights = flightAPI.searchFlights(from, to, date);
        List<Flight> deals = dealFinder.findDeals(flights);
        
        for (Flight deal : deals) {
            notifier.notifyDeal(deal);
        }
    }
}
```

## 📚 Resources

### Official Documentation
- [Flight APIs](https://developers.amadeus.com/)
- [HTTP Client - Java API](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/)

### Tutorials
- [Flight API Integration](https://developers.amadeus.com/get-started)
- [API Best Practices](https://www.baeldung.com/rest-api-best-practices)

### Video Resources
- [Flight API Tutorial](https://www.youtube.com/results?search_query=flight+api+java)
- [API Integration](https://www.youtube.com/results?search_query=java+api+integration)

### Practice Platforms
- [Travel APIs](https://github.com/public-apis/public-apis#travel)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| API Integration | Connect to services | Flight search API |
| Deal Detection | Find good prices | Price comparison |
| Data Processing | Analyze data | Filter, sort flights |
| Notification | Alert users | Email/SMS alerts |
| Multi-step | Complex workflow | Search → Filter → Notify |

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- **Flight API:** Re-read **Step 1** — HTTP GET (Day 33); parse JSON to flight list — see **Step 2**
- **Deal logic:** Compare price to threshold; filter by destination/dates — see **Step 2**
- **Notify:** Use Day 32 (email) or Day 35 (SMS); env vars for API keys — see **Step 3**
- **Main day README** → **Step 1 & 2** for API and filtering; **Key Concepts** for workflow

**Related days:** Day 33 (HTTP, Gson); Day 32 (email); Day 36 (multi-API). **Quick reference:** Store API key in env; handle rate limits and errors

## ✅ Checklist
- [ ] Can integrate flight APIs
- [ ] Can process flight data
- [ ] Can detect deals
- [ ] Can send notifications
- [ ] Can build complex workflows
- [ ] Completed Flight Deal Finder Part 1
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
   cd "path\to\100-java-basics-advance\day39\src"
   ```

3. **Compile** (multiple classes/API: use Maven or `javac *.java` with Gson JAR)
   ```cmd
   javac FlightDealFinder.java
   ```

4. **Run**
   ```cmd
   java FlightDealFinder
   ```

#### **Mac / Linux**

1. **Open Terminal**
   - Mac: Press `Cmd + Space`, type "Terminal", press Enter
   - Linux: Press `Ctrl + Alt + T` or search for "Terminal"

2. **Navigate to your project directory**
   ```bash
   cd ~/projects/100-java-basics-advance/day39/src
   ```

3. **Compile and run**
   ```bash
   javac FlightDealFinder.java
   java FlightDealFinder
   ```

### Using an IDE (Recommended)

**IntelliJ IDEA / Eclipse / VS Code:**
1. Open the project (Maven/Gradle recommended for APIs and Gson)
2. Right-click on the main class
3. Select "Run 'FlightDealFinder.main()'"

### Troubleshooting

- **"javac: command not found"** - Java is not installed or not in PATH
- **API keys**: Use environment variables for flight API keys; never commit secrets

## 🚀 Next Steps
After completing Day 39, you should be able to:
- Build complex applications
- Integrate multiple systems
- Process large datasets
- Create automated workflows

**Ready for Day 40?** You'll complete Flight Club in Part 2!







