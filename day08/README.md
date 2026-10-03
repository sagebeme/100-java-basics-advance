# Day 8 - Farmer Payout

> Months after the harvest, payday arrives. Work out what each farmer takes home, and learn what really happens to the values you pass into a method.

## 🌱 On the shamba

The cooperative has sold the season's coffee. Now every member is paid for the kilograms of cherry they delivered, at the same rate per kilogram. Before the money goes out, two things come off:

- **The levy**, a percentage that pays for running the factory: the clerk, the machines, the water.
- **Advances.** Many farmers borrow a little from the cooperative during the year, for school fees or fertiliser, and it's repaid from their payout.

Today's program prints a payslip:

```
=== Farmer Payout ===
Farmer?
Wanjiru
Kilograms of cherry this season?
520
Rate per kg (KES)?
85
Levy (%)?
5
Advance to repay (KES)?
5000
Payslip for Wanjiru
Cherry: 520.0 kg at KES 85.00 = KES 44,200.00
Levy (5.0%): KES 2,210.00
Advance repaid: KES 5,000.00
To pay: KES 36,990.00
```

(The rate here is made up. Real rates change every season, and with the price coffee fetches at auction.)

## 🎯 By the end of today
- Pass several parameters to a method, in the right order
- Understand that Java passes a *copy* of each value, so a method can't change your `int` or `double`
- See why a method *can* change the contents of an array you pass in
- Accept any number of arguments with varargs (`double... kgs`)
- Use `Math.max` to keep a result from dropping below zero

## ☕ Bean fact

Kenyan coffee farmers don't sell to a café or a roaster. Their cooperative sells green beans through the **Nairobi Coffee Exchange** or straight to an exporter. The price is set by buyers from around the world, so a farmer in Nyeri earns more in a year when coffee prices in New York are high.

## 🧰 Java you'll use

**Parameters are matched by position, not by name.** Get the order wrong and Java won't notice if the types match:

```java
netPay(520, 85, 5, 5000);  // kg, rate, levy %, advance
netPay(85, 520, 5, 5000);  // compiles, gives the same gross by luck, but read it aloud and it's wrong
```

**Java passes a copy of each value.** Changing a parameter inside a method changes the copy, not your variable:

```java
static void addBonus(double kg) {
    kg = kg + 10;        // changes the copy only
}

double mine = 50;
addBonus(mine);
// mine is still 50
```

To get a new value out, **return** it: `mine = withBonus(mine);`.

**An array is different.** The copy Java passes is a copy of the array's *address*, so the method looks at the same array you do and can change what's inside it:

```java
static void addBonusToAll(double[] kgs) {
    for (int i = 0; i < kgs.length; i++) {
        kgs[i] = kgs[i] + 10;   // changes the caller's array
    }
}
```

**Varargs** let a method take any number of values. Inside the method they arrive as an array:

```java
static double total(double... kgs) { ... }

total(12.5, 8, 20);  // kgs is {12.5, 8.0, 20.0}
total();             // kgs is an empty array
```

## 🪜 Steps

1. **Exercise 1: Copies and arrays** (`exercise1/`). See for yourself which changes stick.
2. **Exercise 2: Any number of sacks** (`exercise2/`). Varargs.
3. **Project: Farmer Payout** (`_start/src/FarmerPayout.java`).
   1. `grossPay(kg, rate)`.
   2. `netPay(kg, rate, levyPercent, advance)`: gross, minus the levy, minus the advance. A big advance can't make the payout negative, because the farmer simply gets nothing. Use `Math.max(0, ...)`.
   3. `fillPayouts(kgs, rate, levyPercent, payouts)`: a `void` method that writes each farmer's net pay into the `payouts` array (no advances here). It returns nothing, because the caller's array is changed.
   4. `payslip(...)`: the five lines in the example. `String.format("%,.2f", 44200.0)` gives `44,200.00`.
   5. In `main`, ask the questions and print the payslip.

## 🧪 Taste test

```bash
cd day08/_start/src
javac FarmerPayout.java
java FarmerPayout
```

To run the tests, copy `_end/src/FarmerPayoutTest.java` next to your file, then from the repo root:

```bash
javac -cp lib/junit-platform-console-standalone-6.1.3.jar -d day08/_start/src day08/_start/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp day08/_start/src --scan-classpath
```

For an exercise, use `day08/exercise1/src` or `day08/exercise2/src` in both commands.

## 🆘 Stuck?

- **The net pay is far too big or small**: check the order of your arguments against the method's parameter list.
- **The levy is 100 times too big**: `5` means 5 percent, so divide by 100: `gross * levyPercent / 100`.
- **My method changed a number, but the caller still has the old one**: that's pass-by-value. Return the new value instead.
- **Negative pay**: wrap the result in `Math.max(0, ...)`.

## ✅ Checklist
- [ ] Called a method with several parameters in the right order
- [ ] Can explain why a method can't change your `double`, but can change your array's contents
- [ ] Wrote a varargs method
- [ ] Finished both exercises
- [ ] Farmer Payout's tests pass
- [ ] Committed code to Git

## ➡️ Next

Coffee from Nyeri doesn't taste like coffee from Kisii. On Day 9 you'll map regions to their flavours with `Map` and `Set`.
