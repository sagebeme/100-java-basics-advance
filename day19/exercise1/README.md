# Day 19 - Exercise 1: Matatu, boda or tuk-tuk?

Kevin has to get across Kisumu and wants the cheapest ride. Each kind of ride charges in its own way, so each is a subclass of `Ride` with its own `fare`. `src/Ride.java` is mostly written. You write the three subclasses and `Ride.cheapest`.

| Ride | Seats | Fare for the whole group |
|---|---|---|
| `Matatu` (name `"Matatu"`) | 14 | KES 50 a person for up to 10 km, then KES 5 a person for every km after that |
| `Boda` (name `"Boda"`) | 1 | KES 50 to start, plus KES 30 a km |
| `TukTuk` (name `"Tuk-tuk"`) | 3 | KES 100 to start, plus KES 20 a km, however many ride |

So 12 km by matatu for 5 people is 5 × (50 + 5 × 2) = KES 300.

`Ride.cheapest(rides, km, passengers)` returns the ride that fits the group and costs least. On a tie, the one earlier in the list wins. If nothing fits, it returns `null`.

Look at `quote` in `Ride`: it calls `fare`, which `Ride` itself never writes. When `quote` runs on a `Boda`, Java uses `Boda`'s `fare`. That's polymorphism, and it's why `cheapest` works on a `List<Ride>` without knowing which kinds are in it.

## Files

- `src/Ride.java`: done, except `cheapest`.
- `src/Matatu.java`, `src/Boda.java`, `src/TukTuk.java`: your code goes here.
- `src/RideTest.java`: the tests.
- `solution/`: one answer.

```bash
javac -cp "lib/*" -d day19/exercise1/src day19/exercise1/src/*.java
java -cp "day19/exercise1/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- Each constructor is one line: `super("Boda", 1);`. A subclass has to call its parent's constructor first, because `Ride` has no constructor without arguments.
- For the matatu's extra kilometres, `Math.max(0, km - 10)` is 0 on short trips.
- In `cheapest`, keep a `Ride best = null;`. Skip rides that don't fit; replace `best` only when a ride is strictly cheaper (`<`, not `<=`), which is what keeps the first one on a tie.
