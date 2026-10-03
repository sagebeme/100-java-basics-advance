# Day 5 - Solution

`src/DryingBed.java` is one finished version, checked by `src/DryingBedTest.java`.

Things worth noticing:

- **The `-1` guard comes before the `while` loop.** Without it, a loss of `0` would loop forever. Always ask how a loop is going to end.
- **`while` for "until", `for` for "this many".** `daysToDry` doesn't know the number of days in advance, but `moistureBar` knows exactly how many `#`s it needs.
- **`return` inside a loop** ends the method the moment the beans are dry, so there's no need to finish the forecast.
- **`main` repeats the weather loop instead of calling `daysToDryWithWeather`**, because it needs to print every day as it goes. The method is the tested version of the same rule.
