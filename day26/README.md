# Day 26 - Streams and Lambdas: Spelling It Out at the Call Centre

> "Is that M for Mike or N for November?" Call-centre agents spell names with the NATO alphabet so nothing gets lost on a bad line. Today's program does it in one stream.

## 📖 The story

Faith works the night shift at a customer care centre in Nairobi. All night, callers spell out account names and M-Pesa codes over crackly lines, and **B**, **D**, **P** and **T** all sound the same. So agents use the NATO phonetic alphabet: Alpha, Bravo, Charlie… Faith wants a small helper on her screen: type a word, see how to say it.

```
Enter a word: Faith
F for Foxtrot
A for Alpha
I for India
T for Tango
H for Hotel
```

The whole conversion is one **stream**: take the characters, keep only letters, turn each one into a line, join the lines. No loop, no counter, no list to fill.

## 🎯 Learning Objectives
- Write lambdas: small functions you can store and pass around (`c -> c + " for " + ...`)
- Use method references such as `Character::isLetter`
- Build a stream pipeline: a source, then `filter`, `map` and other steps, then a collector
- Collect results with `Collectors.joining`, `toList()`, `groupingBy` and `counting`
- Sort with `Comparator.comparing`, and combine conditions with `Predicate.and` and `negate`

## 🧰 Java you'll use

**A lambda** is a function without a name. These two do the same thing:

```java
Predicate<Character> isLetter = c -> Character.isLetter(c);
Predicate<Character> same = Character::isLetter;          // a method reference
```

**A stream** reads like a sentence. Today's pipeline:

```java
String spelled = word.toUpperCase().chars()      // the characters, as numbers
        .mapToObj(c -> (char) c)                 // ... as Characters
        .filter(Character::isLetter)             // keep the letters
        .map(c -> c + " for " + nato.get(c))     // "F for Foxtrot"
        .collect(Collectors.joining("\n"));      // one line each
```

Nothing happens until the last step, the **collector**, asks for the result.

**A map from letters to words.** `Map<Character, String>` filled once in a `static` block: `'A' → "Alpha"`, and so on to `'Z' → "Zulu"`.

**Comparators** are lambdas too: `Comparator.comparing(Matatu::getSeats).reversed().thenComparing(Matatu::getPlate)` sorts by seats, most first, then by plate. Exercise 1 is full of them.

## 🪜 Steps

1. **[Exercise 1: Sorting the Sacco's fleet with lambdas](exercise1/README.md)** (`exercise1/`): comparators, predicates and functions as small lambdas that can be sorted with, combined with `and` and `negate`, and passed around.
2. **[Exercise 2: Mama Oliech's till, in streams](exercise2/README.md)** (`exercise2/`): totals, distinct sorted lists, counts per hour, the largest payment and joined codes, each as one stream with no loop.
3. **Project: The NATO Speller** (`_start/src/NATOPhonetic.java`).
   1. Fill the map with all 26 letters.
   2. `toNato(word)`: one stream, as above. Capitals don't matter, and anything that isn't a letter (spaces, digits, dashes) is skipped.
   3. `main`: ask for a word and print the result.

## 🧪 Run it and test it

```bash
cd day26/_end/src
javac NATOPhonetic.java
java NATOPhonetic
```

To test your own version, copy `_end/src/NATOPhoneticTest.java` into `_start/src`, then from the repo root:

```bash
javac -cp "lib/*" -d day26/_start/src day26/_start/src/*.java
java -cp "day26/_start/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

For an exercise, use `day26/exercise1/src` or `day26/exercise2/src` in both commands. On Windows, use `;` instead of `:` in `-cp`.

## 🆘 Stuck?

- **`incompatible types` on the `map` line**: `chars()` gives an `IntStream` of numbers, not characters. Turn them into characters with `.mapToObj(c -> (char) c)` first.
- **Lower-case letters give `null`**: the map's keys are capitals. Call `toUpperCase()` first.
- **`É for null`**: `Character.isLetter` is true for letters outside A–Z too. Filter with `nato.containsKey(c)` if you'd rather skip them.
- **Nothing prints**: a stream with no collector at the end never runs. Finish with `collect`, `toList()`, `count()` or `forEach`.
- **`local variables referenced from a lambda must be final`**: a lambda can read a local variable, but not change it. Let the stream build the result instead of adding to an outside counter.

## ✅ Checklist
- [ ] Wrote lambdas and method references
- [ ] Built a stream with `filter`, `map` and a collector
- [ ] The speller ignores capitals and skips anything that isn't a letter
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 27 is back to JavaFX, this time with buttons, text fields and labels: a unit converter.
