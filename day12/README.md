# Day 12 - Farmer Records

> A farmer's total is worth money. Protect it with `private`, and learn where each variable lives.

## 🌱 On the shamba

Last season, someone at a cooperative in another county quietly added 200 kg to a cousin's record. Nobody noticed until payday, and by then the money was gone from everyone else's share.

In Day 11's program, any line of code could change any number. Today you'll build a **farmer record** that only changes in ways the rules allow: deliveries can be added, but never negative ones, and nobody can type in a new total directly.

```
=== Records Office ===
Opened 2 records.
WK-0042 Wanjiru: 33.5 kg
OA-0107 Otieno: 31.0 kg
Tried to add -200 kg to Otieno: refused.
OA-0107 Otieno: 31.0 kg
```

## 🎯 By the end of today
- Know where a variable can be used: its **scope**
- Tell local variables, fields and `static` fields apart
- Hide a class's data with `private`, and let others in only through `public` methods
- Use `this` when a parameter has the same name as a field
- Make a value unchangeable with `final`

## ☕ Bean fact

Many Kenyan coffee cooperatives have moved from paper books to digital weighing: the scale sends the weight straight to a computer, and the farmer gets an SMS receipt. It's harder to fiddle with a number that nobody types in.

## 🧰 Java you'll use

**Scope** is the part of the code where a variable exists. A variable lives from the line that declares it to the closing `}` of the block it's in:

```java
static int countRed(String[] cherries) {
    int count = 0;                   // lives until the method's last }
    for (String c : cherries) {      // c lives only inside the loop
        if (c.equals("red")) {
            count++;
        }
    }
    // c doesn't exist here
    return count;
}
```

**A class with private fields.** Until now every class has been a bag of `static` methods. Today a class also holds data, and you make objects from it with `new`. Day 16 covers objects properly; for now you need just this much:

```java
public class FarmerRecord {
    private final String name;      // set once, never changed
    private double deliveredKg;     // only this class can touch it

    public FarmerRecord(String name) {   // the constructor runs on `new`
        this.name = name;                // this.name is the field, name is the parameter
    }

    public boolean addDelivery(double kg) {
        if (kg <= 0) return false;       // the rule, enforced in one place
        deliveredKg += kg;
        return true;
    }

    public double getDeliveredKg() {     // read-only access from outside
        return deliveredKg;
    }
}

FarmerRecord w = new FarmerRecord("Wanjiru");
w.addDelivery(23.5);
w.getDeliveredKg();   // 23.5
w.deliveredKg = 999;  // compile error: deliveredKg has private access
```

| Keyword | Who can use it |
|---|---|
| `private` | Only code inside the same class |
| `public` | Any code anywhere |
| (none) | Code in the same folder (package) |

**`static` fields** belong to the class, not to any one object, so all records share one counter:

```java
private static int recordsOpened = 0;  // one number for the whole class
```

## 🪜 Steps

1. **Exercise 1: Scope bugs** (`exercise1/`). Two programs that compile but give wrong answers because a variable lives in the wrong place.
2. **Exercise 2: A scale nobody can fiddle** (`exercise2/`). A private calibration setting with a guarded way to change it.
3. **Project: Farmer Records** (`_start/src/FarmerRecord.java` and `RecordsOffice.java`).
   1. Give `FarmerRecord` three `private` fields: `name` and `memberCode` (both `final`) and `deliveredKg`.
   2. In the constructor, use `this.` to copy the parameters into the fields, and add 1 to the `static` counter `recordsOpened`.
   3. `addDelivery(double kg)`: refuse `0` or less by returning `false`.
   4. Fill in the getters `getName()` and `getDeliveredKg()`, and `summary()`, which gives `"WK-0042 Wanjiru: 33.5 kg"`. Don't write a `setDeliveredKg`.
   5. In `RecordsOffice.main`, open two records, add deliveries, and try a negative one. Then uncomment the line that sets `deliveredKg` directly and read the compiler's error.

## 🧪 Taste test

```bash
cd day12/_start/src
javac *.java
java RecordsOffice
```

To run the tests, copy `_end/src/FarmerRecordTest.java` next to your files, then from the repo root:

```bash
javac -cp lib/junit-platform-console-standalone-6.1.3.jar -d day12/_start/src day12/_start/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp day12/_start/src --scan-classpath
```

For an exercise, use `day12/exercise1/src` or `day12/exercise2/src` in both commands.

## 🆘 Stuck?

- **`cannot find symbol`** for a variable you can see a few lines up: it was declared inside a block (`{ }`) that has already closed. Declare it before the block.
- **The field stays `null` or `0` after the constructor**: you wrote `name = name;`, which copies the parameter onto itself. Write `this.name = name;`.
- **`cannot assign a value to final variable`**: a `final` field can only be set once, in the constructor.
- **`non-static variable cannot be referenced from a static context`**: `main` is `static`, so it has no record of its own. Make one with `new FarmerRecord(...)` and call its methods.

## ✅ Checklist
- [ ] Can say where a variable's scope starts and ends
- [ ] Fixed both scope bugs in Exercise 1
- [ ] Made fields `private` and read them through getters
- [ ] Used `this.` in a constructor
- [ ] Used a `static` field shared by every object
- [ ] Saw the compiler refuse to let `RecordsOffice` touch a private field
- [ ] Farmer Records' tests pass
- [ ] Committed code to Git

## ➡️ Next

On Day 13 the weighbridge is giving the wrong totals. You'll find the bugs with stack traces and a debugger.
