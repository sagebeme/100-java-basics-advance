# Day 19 - Inheritance and Polymorphism: Rides, and a Turtle Race

> A matatu, a boda boda and a tuk-tuk are all rides, but each works out its fare its own way. Today you teach Java the same idea, then race four turtles across a JavaFX window.

## 📖 The story

Kevin is building a fare app for Kisumu. Every ride has a pickup, a drop-off and a fare, but the fare rules differ: a matatu charges each passenger, a boda boda charges per kilometre, a tuk-tuk charges the whole group one price. He doesn't want a giant `if (type.equals("boda"))` in the middle of his app. He wants to say "give me the fare" to *any* ride and get the right answer. That's **inheritance** (one shared parent class) and **polymorphism** (each child answering the same question its own way). The two exercises are about exactly this.

The project is lighter, and it moves: four turtles race from the left edge of a JavaFX window to a checkered finish line. Every 50 milliseconds each turtle shuffles forward a random 0–9 pixels, and the first across the line wins:

```
Turtle 3 wins!
```

The race logic, moving forward and checking the finish line, sits in a plain class, `TurtleRaceLogic`, so you can test it without a window.

## 🎯 Learning Objectives
- Share fields and methods between classes with `extends`
- Override a method with `@Override`, and call the parent's version with `super`
- Write an `abstract` class with an `abstract` method that every subclass must fill in
- Use polymorphism: hold different objects in one `List<Ride>` and call the same method on each
- Override `toString`, `equals` and `hashCode`, and know why `HashSet` needs the last two
- Animate a JavaFX canvas by redrawing it on a timer

## 🧰 Java you'll use

**A parent and its children:**

```java
public abstract class Ride {
    private final String name;
    private final int seats;
    protected Ride(String name, int seats) { this.name = name; this.seats = seats; }
    public abstract int fare(int km, int passengers);   // every kind of ride must say how
}

public class Boda extends Ride {
    public Boda() { super("Boda", 1); }                 // run the parent's constructor first
    @Override
    public int fare(int km, int passengers) { return 50 + 30 * km; }
}
```

**Polymorphism.** The variable's type is `Ride`, but Java runs each child's own `fare`:

```java
List<Ride> options = List.of(new Matatu(), new Boda(), new TukTuk());
for (Ride r : options) System.out.println(r.getName() + ": KES " + r.fare(6, 1));
```

**Redrawing on a timer.** The race runs on a background thread that moves the turtles and sleeps 50ms. Only the JavaFX thread may draw, so the thread hands the drawing over with `Platform.runLater(() -> drawTrack(gc))`.

## 🪜 Steps

1. **[Exercise 1: Matatu, boda or tuk-tuk?](exercise1/README.md)** (`exercise1/`): an abstract `Ride` with three subclasses that each work out their own fare, and a method that finds the cheapest without knowing which kinds it's been given.
2. **[Exercise 2: The same number plate, typed three ways](exercise2/README.md)** (`exercise2/`): overriding `toString`, `equals` and `hashCode` so a `HashSet` keeps each car once.
3. **Project: Turtle Race** (`_start/src/TurtleRace.java`).
   1. `TurtleRaceLogic`: `advance(x, step)` and `hasFinished(x)`, with the finish line at 750. Write this first, with its test.
   2. `drawTrack`: striped grass, dashed lanes, a checkered finish line, and four numbered turtles at their `x` positions.
   3. `animateRace`: move each turtle a random step, redraw, and stop when one crosses the line, printing who won.

## 🧪 Run it and test it

The logic and its test need no JavaFX. From the repo root:

```bash
javac -cp "lib/*" -d day19/_end/src day19/_end/src/TurtleRaceLogic.java day19/_end/src/TurtleRaceLogicTest.java
java -cp "day19/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

To watch the race, with `FX` pointing at your JavaFX SDK's `lib` folder (see Day 18):

```bash
cd day19/_end/src
javac --module-path $FX --add-modules javafx.controls TurtleRace.java TurtleRaceLogic.java
java --module-path $FX --add-modules javafx.controls TurtleRace
```

Use `_start/src` for your own version, and `day19/exercise1/src` or `day19/exercise2/src` for the exercises. On Windows, use `;` instead of `:` in `-cp`.

## 🆘 Stuck?

- **`Ride is abstract; cannot be instantiated`**: that's on purpose. Create a `Boda` or a `Matatu`, never a plain `Ride`.
- **`constructor Ride in class Ride cannot be applied`**: a subclass constructor must call `super(...)` with the parent's arguments, as its first line.
- **`HashSet` keeps both copies of the same plate**: you overrode `equals` but not `hashCode`. Override both, using the same fields.
- **The window freezes during the race**: you're sleeping on the JavaFX thread. Run the loop on a `new Thread(...)` and draw through `Platform.runLater`.

## ✅ Checklist
- [ ] Wrote an abstract class and subclasses that override its method
- [ ] Called the same method on a list of different kinds of object
- [ ] Overrode `toString`, `equals` and `hashCode` together
- [ ] The turtles race, and the winner is printed
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 20 starts a two-day build of Snake: a grid, a game loop and the arrow keys.
