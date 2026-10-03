# Day 7 - Exercise 2: Biggest sack of the day

At closing time the clerk announces who brought the heaviest single delivery. Write `biggestDelivery(ArrayList<String> farmers, ArrayList<Double> kgs)`, which walks both lists together:

```java
farmers: ["Wanjiru", "Otieno", "Achieng"]
kgs:     [23.5,      31.0,     12.0]
```

returns `"Otieno with 31.0 kg"`. If two deliveries tie, the first one wins. With no deliveries at all, return `"No deliveries yet"`.

## Files

- `src/BiggestDelivery.java`: your code goes here.
- `src/BiggestDeliveryTest.java`: the tests.
- `solution/BiggestDelivery.java`: one answer.

## 📌 Stuck?

This is the same idea as Day 4's driest day, only looking for the biggest instead of the smallest. Remember the *index* of the biggest so far, then use it in both lists at the end.
