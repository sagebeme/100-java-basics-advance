# Day 7 - Weighing Station

> Project day. Run the cooperative's weighing station for a whole afternoon.

## 🌱 On the shamba

Between 3pm and 6pm, the weighing station is the busiest place in the village. Farmers arrive with sacks of cherry, the clerk weighs each one and writes it down, and people keep asking, "How much have I brought this week?"

Today you'll build the clerk's program. It shows a menu, does what's chosen, and keeps going until the clerk closes the station. It brings together everything from Days 1 to 6: variables, input, decisions, lists, loops and methods.

```
=== Weighing Station ===
1. Record a delivery
2. A farmer's total
3. Today's summary
4. Close the station
Choose 1-4:
1
Farmer's name?
Wanjiru
Kilograms?
23.5
Recorded.
Choose 1-4:
1
Farmer's name?
Otieno
Kilograms?
31
Recorded.
Choose 1-4:
2
Farmer's name?
wanjiru
Wanjiru: 1 deliveries, 23.5 kg
Choose 1-4:
3
2 deliveries, 54.5 kg in total
Choose 1-4:
4
Station closed. Safe journey home!
```

## 🎯 By the end of today
- Plan a program as small methods before writing `main`
- Keep a program running with a loop and a menu
- Keep two lists in step: a name at index `i` belongs to the weight at index `i`
- Refuse bad input instead of storing it

## ☕ Bean fact

Farmers aren't paid when they deliver. The cooperative adds up every farmer's kilograms over the season, sells the coffee, and pays out months later. That's why the weighing records matter so much: they're the only proof of what each farmer brought.

## 🧰 Java you'll use

Nothing new today. That's the point of a project day. The patterns you'll lean on:

**A menu loop** keeps running until a `boolean` says stop:

```java
boolean open = true;
while (open) {
    String choice = scanner.nextLine().trim();
    if (choice.equals("1")) {
        // record
    } else if (choice.equals("4")) {
        open = false;
    } else {
        System.out.println("Please choose 1-4.");
    }
}
```

**Two lists in step.** Adding to both at once keeps index `i` meaning the same delivery in each:

```java
ArrayList<String> farmers = new ArrayList<>();
ArrayList<Double> kgs = new ArrayList<>();

farmers.add("Wanjiru");
kgs.add(23.5);
// farmers.get(0) delivered kgs.get(0)
```

## 🪜 Steps

1. **Exercise 1: The menu** (`exercise1/`). Turn whatever the clerk types into a menu choice, or `-1`.
2. **Exercise 2: Biggest sack of the day** (`exercise2/`). Walk two lists together.
3. **Project: Weighing Station** (`_start/src/WeighingStation.java`). Write and test the methods first, `main` last.
   1. `record`: refuse an empty name or a weight of 0 or less by returning `false`. Otherwise add to both lists and return `true`.
   2. `totalKg`: the weight of every delivery added together.
   3. `totalFor` and `deliveriesBy`: one farmer's kilograms and number of deliveries. Match names with `equalsIgnoreCase`, because the clerk types `wanjiru` as often as `Wanjiru`.
   4. `summary`: `"2 deliveries, 54.5 kg in total"`.
   5. `main`: the menu loop.

## 🧪 Taste test

```bash
cd day07/_start/src
javac WeighingStation.java
java WeighingStation
```

To run the tests, copy `_end/src/WeighingStationTest.java` next to your file, then from the repo root:

```bash
javac -cp lib/junit-platform-console-standalone-6.1.3.jar -d day07/_start/src day07/_start/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp day07/_start/src --scan-classpath
```

For an exercise, use `day07/exercise1/src` or `day07/exercise2/src` in both commands.

## 🆘 Stuck?

- **The totals are wrong after a refused delivery**: `record` must add to *both* lists or *neither*. Check for bad input before adding anything.
- **The menu skips a question**: mixing `nextInt()` and `nextLine()` leaves a leftover line break. Read everything with `nextLine()` and convert it yourself.
- **The program never ends**: option 4 must set the loop's `boolean` to `false`.
- **Where do I start?** Get option 4 working first, so you can always get out. Then option 1, then 3, then 2.

## ✅ Checklist
- [ ] Planned the methods before writing `main`
- [ ] The menu loop keeps going until option 4
- [ ] Bad weights and empty names are refused
- [ ] Finished both exercises
- [ ] Weighing Station's tests pass
- [ ] Committed code to Git

## ➡️ Next

At the end of the season, farmers get paid for those kilograms, minus a few deductions. On Day 8 you'll work out the payout, and see what really happens to the values you pass into a method.
