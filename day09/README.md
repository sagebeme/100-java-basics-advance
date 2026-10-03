# Day 9 - Flavour Map

> Coffee from Nyeri doesn't taste like coffee from Kisii. Map each region to its flavours.

## 🌱 On the shamba

A buyer visits the cooperative and asks, "What does your coffee taste like?" Wanjiru has never thought about it. Coffee is coffee, isn't it?

Not to a professional taster. Soil, altitude, rain and the way the beans are processed all change the flavour. Tasters describe it with **tasting notes**: everyday flavours the coffee reminds them of. A Nyeri coffee might be described as "blackcurrant and grapefruit". Nobody added fruit; that's just what it tastes like.

Today's program stores the notes for each region and answers questions like "Where would I find a coffee that tastes of honey?":

```
=== Flavour Map ===
Nyeri: blackcurrant, grapefruit, brown sugar
Kirinyaga: blackberry, citrus, honey
Kiambu: lemon, black tea, caramel
Murang'a: red currant, plum, honey
Embu: orange, molasses, black tea
Kisii: milk chocolate, red apple, caramel
Which flavour do you like?
honey
Try coffee from: Kirinyaga, Murang'a
```

(These notes are typical examples. Every farm and every harvest tastes a little different.)

## 🎯 By the end of today
- Store pairs of keys and values in a `Map`, and look a value up by its key
- Loop over a map's entries
- Keep only unique values with a `Set`
- Find what two sets have in common with `retainAll`
- Choose between `HashMap`, `LinkedHashMap` and `TreeSet` by the order you need

## ☕ Bean fact

Kenyan coffees are famous for a bright, juicy sourness that tasters call **acidity**, and for a dark berry flavour often described as **blackcurrant**. If you've never had a blackcurrant, think of the deep, sharp taste of **zambarau**.

## 🧰 Java you'll use

**A `Map`** links each key to one value. Keys are unique, so putting a key in twice replaces its value:

```java
import java.util.*;

Map<String, String> capital = new HashMap<>();
capital.put("Nyeri", "Nyeri town");
capital.put("Kiambu", "Kiambu town");
capital.get("Nyeri");               // "Nyeri town"
capital.get("Mombasa");             // null: no such key
capital.getOrDefault("Mombasa", "?"); // "?"
capital.containsKey("Kiambu");      // true
```

**Looping over a map**:

```java
for (Map.Entry<String, String> entry : capital.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
```

A `HashMap` doesn't promise any order. A `LinkedHashMap` keeps the order you added things in, which is nicer for printing.

**A `Set`** holds each value at most once. Adding a duplicate does nothing:

```java
Set<String> notes = new TreeSet<>();  // TreeSet keeps them sorted
notes.add("honey");
notes.add("caramel");
notes.add("honey");                   // ignored
// notes is [caramel, honey]
```

**Common items**: `retainAll` keeps only what's also in another collection.

```java
Set<String> shared = new TreeSet<>(List.of("honey", "plum"));
shared.retainAll(List.of("honey", "citrus"));
// shared is [honey]
```

## 🪜 Steps

1. **Exercise 1: Totals by farmer** (`exercise1/`). Replace Day 7's two parallel lists with one `Map`.
2. **Exercise 2: Who came both days?** (`exercise2/`). Sets.
3. **Project: Flavour Map** (`_start/src/FlavourMap.java`). `kenyanRegions()` already builds the map for you.
   1. `describe(regions, region)`: `"Nyeri: blackcurrant, grapefruit, brown sugar"`, or `"Unknown region: Mombasa"`. `String.join(", ", list)` joins a list with commas.
   2. `allNotes(regions)`: every note from every region, each once, in alphabetical order.
   3. `regionsWithNote(regions, note)`: the regions whose notes include `note`, ignoring capitals, in the map's order.
   4. `sharedNotes(regions, a, b)`: the notes two regions have in common, sorted. An unknown region shares nothing.
   5. In `main`, print every region with `describe`, then ask for a flavour and suggest regions.

## 🧪 Taste test

```bash
cd day09/_start/src
javac FlavourMap.java
java FlavourMap
```

To run the tests, copy `_end/src/FlavourMapTest.java` next to your file, then from the repo root:

```bash
javac -cp lib/junit-platform-console-standalone-6.1.3.jar -d day09/_start/src day09/_start/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp day09/_start/src --scan-classpath
```

For an exercise, use `day09/exercise1/src` or `day09/exercise2/src` in both commands.

## 🆘 Stuck?

- **`NullPointerException`**: `get` returned `null` for a key that isn't in the map. Check with `containsKey` first, or use `getOrDefault`.
- **`UnsupportedOperationException`**: `List.of(...)` and `Set.of(...)` can't be changed. Copy one into a new collection before adding or removing: `new TreeSet<>(regions.get(a))`.
- **`retainAll` emptied the region's own list**: you called it on the map's list itself. Always copy first.
- **Wrong order**: `HashMap` and `HashSet` shuffle. Use `LinkedHashMap` for "the order I added them" and `TreeSet` for "sorted".

## ✅ Checklist
- [ ] Put keys and values in a `Map` and looked them up
- [ ] Looped over a map's entries
- [ ] Used a `Set` to remove duplicates
- [ ] Found common values with `retainAll`
- [ ] Finished both exercises
- [ ] Flavour Map's tests pass
- [ ] Committed code to Git

## ➡️ Next

The green beans travel abroad, and someone roasts them. On Day 10 you'll write methods that return the roast level, the weight lost, and how much caffeine ends up in your cup.
