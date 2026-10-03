# Day 39 - Exercise 2: Cheapest per city

A fare search returns many fares for many places. Write `cheapestPerCity(List<SimpleFare> fares)` in `src/CheapestFares.java`. It returns a map from each city to its **cheapest** fare, with the cities in A-to-Z order.

```java
record SimpleFare(String city, int priceKes, String airline) {}

[Mombasa 7900 Twiga Air, Kisumu 4800 Ndege Link, Mombasa 6200 Ndege Link]
→ {Kisumu=Kisumu 4800 Ndege Link, Mombasa=Mombasa 6200 Ndege Link}
```

Write it with **streams**: `Collectors.groupingBy` with a `TreeMap`, and `Collectors.minBy` inside it.

## Files

- `src/CheapestFares.java`: your code goes here (`SimpleFare` is in it).
- `src/CheapestFaresTest.java`: the tests.
- `solution/CheapestFares.java`: one answer.

## 📌 Stuck?

```java
fares.stream().collect(Collectors.groupingBy(SimpleFare::city, TreeMap::new,
        Collectors.minBy(Comparator.comparingInt(SimpleFare::priceKes))))
```

gives a `Map<String, Optional<SimpleFare>>`. Each group has at least one fare, so the `Optional`s are never empty. Unwrap them with `Collectors.collectingAndThen(..., Optional::get)`.
