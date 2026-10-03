# Day 14 - Coffee Shop Menu Decoder

> The coffee reaches the cup. Walk into a coffee shop for the first time and know exactly what to order.

## 🌱 In town

Wanjiru's daughter Njeri has a new job in Nairobi. On her first day, her colleagues take her to a coffee shop. The menu board says *Espresso, Americano, Macchiato, Cappuccino, Latte, Flat White*, and she has no idea what any of them are. She orders a soda.

Here's the secret: almost every drink on that board is the same small shot of **espresso**, with different amounts of hot water, steamed milk and milk foam on top. Today's program decodes the menu. Tell it what's in the cup and it names the drink, explains a name in plain words, or suggests one for you:

```
=== Menu Decoder ===
1. What's in my cup?
2. What does this word on the menu mean?
3. What should I order?
4. Leave the café
Choose 1-4:
3
Do you like milk in your drinks? (yes/no)
yes
Do you like your coffee strong? (yes/no)
no
Try a Latte: An espresso in a big cup of steamed milk with a thin layer of foam. The closest thing on the menu to milky chai.
Choose 1-4:
4
Enjoy your coffee!
```

| What's in the cup | The drink |
|---|---|
| No espresso at all | `Not coffee` |
| Espresso only | `Espresso` |
| Espresso and hot water | `Americano` |
| Espresso and foam, but no milk | `Macchiato` |
| Milk, with at least ¾ as much foam as milk | `Cappuccino` |
| Milk, at least 4 times as much milk as espresso | `Latte` |
| Any other mix of espresso and milk | `Flat White` |

Check the rules **in that order**: the first one that fits wins.

## 🎯 By the end of today
- Plan and build a whole menu-driven program on your own
- Turn a table of rules into an ordered chain of decisions
- Keep text that belongs together in a `Map`
- Combine everything from the Beginner stage: input, decisions, loops, arrays, lists, maps, methods and private helpers

## ☕ Bean fact

If you've been to the Kenyan coast, you may already know a coffee drink: **kahawa chungu**, "bitter coffee", poured from a tall brass kettle into tiny cups in the streets of Mombasa and Lamu, often spiced with ginger or cardamom and eaten with sweet **kashata**. It's small, black and strong. In other words, the coast had its own espresso long before the coffee shops arrived.

## 🧰 Java you'll use

Nothing new. Today you're the one who decides how the program is put together. A few reminders:

**Ratios without decimals.** "At least ¾ as much foam as milk" is `foam >= milk * 0.75`. "At least 4 times as much milk" is `milk >= espresso * 4`.

**A map of explanations** keeps each drink next to its description, in menu order:

```java
Map<String, String> menu = new LinkedHashMap<>();
menu.put("Espresso", "A small, strong shot...");
```

**Looking a key up whatever its capitals** means looping over the keys, as on Day 13:

```java
for (String drink : menu.keySet()) {
    if (drink.equalsIgnoreCase(asked)) { ... }
}
```

## 🪜 Steps

1. **Exercise 1: What size is that?** (`exercise1/`). Total a cup's contents and name its size.
2. **Exercise 2: Placing the order** (`exercise2/`). Build the sentence you'll say at the counter.
3. **Project: Coffee Shop Menu Decoder** (`_start/src/MenuDecoder.java`).
   1. `identify(espressoMl, waterMl, milkMl, foamMl)`: the rules table, in order.
   2. `explanations()`: a `LinkedHashMap` of the six drinks and their plain-language descriptions. Write your own, or use the ones in `_end/`. At least one should compare a drink with something familiar, like chai.
   3. `explain(drink)`: look the drink up whatever its capitals. For a word not on the map, return `Not on this menu. Ask the barista!`
   4. `recommend(likesMilk, likesStrong)`: milky and strong → `Flat White`; milky and mild → `Latte`; no milk and strong → `Espresso`; no milk and mild → `Americano`.
   5. `main`: the four-option menu loop from Day 7, using your methods.

## 🧪 Taste test

```bash
cd day14/_start/src
javac MenuDecoder.java
java MenuDecoder
```

To run the tests, copy `_end/src/MenuDecoderTest.java` next to your file, then from the repo root:

```bash
javac -cp lib/junit-platform-console-standalone-6.1.3.jar -d day14/_start/src day14/_start/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp day14/_start/src --scan-classpath
```

For an exercise, use `day14/exercise1/src` or `day14/exercise2/src` in both commands.

## 🆘 Stuck?

- **A cappuccino comes out as a latte**: check the rules in the table's order. The foam rule must come before the "4 times the milk" rule.
- **`explain("latte")` says it's not on the menu**: the key is `"Latte"`. Compare with `equalsIgnoreCase`.
- **Where do I start?** `identify`, with the tests. It's the heart of the program, and everything else is a menu around it.

## ✅ Checklist
- [ ] `identify` follows the rules table in order
- [ ] Wrote an explanation for every drink
- [ ] `explain` ignores capitals and handles unknown words
- [ ] The menu loop runs until option 4
- [ ] Finished both exercises
- [ ] Menu Decoder's tests pass
- [ ] Committed code to Git
- [ ] Ordered something other than a soda 🙂

## ➡️ Next

That's the Beginner stage done: from a cherry on the bush to a cup on the table. On Day 15 you'll set up a proper development environment and build the machine that makes these drinks.
