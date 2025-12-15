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

## ✅ Checklist
- [ ] Can integrate flight APIs
- [ ] Can process flight data
- [ ] Can detect deals
- [ ] Can send notifications
- [ ] Can build complex workflows
- [ ] Completed Flight Deal Finder Part 1
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 39, you should be able to:
- Build complex applications
- Integrate multiple systems
- Process large datasets
- Create automated workflows

**Ready for Day 40?** You'll complete Flight Club in Part 2!



