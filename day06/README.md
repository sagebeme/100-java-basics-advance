# Day 6 - Grade Labeller

> AA, AB, PB, C: Kenya's coffee grades, and the methods that print them.

## 🌱 On the shamba

Wanjiru's beans are dry. Before they're sold, the mill shakes them through a stack of metal sieves called **screens**, each with holes of a different size. Big beans stay on top and small ones fall through. Each size gets a grade, and buyers pay the most for the biggest.

Some cherries grow a single, round bean instead of two flat ones. These **peaberries** roll through the sieves differently, so they're picked out and get their own grade whatever their size.

Today you'll write the methods that decide a grade and print a label for the sack:

```
=== Grade Labeller ===
Factory name?
Othaya Hills
Screen size?
17
Is it a peaberry? (yes/no)
no
Othaya Hills Factory | Grade AA | screen 17
```

| Grade | Rule (simplified) |
|---|---|
| `PB` | a peaberry, whatever its size |
| `AA` | screen 17 or bigger |
| `AB` | screen 15 or 16 |
| `C` | smaller than screen 15 |

## 🎯 By the end of today
- Write your own methods with parameters and a return value
- Call one method from another instead of repeating code
- Overload a method: the same name with different parameters
- Know the difference between a method that returns a value and one that only prints

## ☕ Bean fact

A **screen size** is the width of a sieve's holes in 64ths of an inch, so a screen 17 hole is 17/64 of an inch, about 6.7 mm wide. Kenya's top grade, **AA**, is famous around the world, but size isn't flavour: a cup of AB from a great farm can taste better than an AA from an ordinary one.

## 🧰 Java you'll use

**A method** is a named piece of code. Its parameters are the inputs, and its return type says what comes back:

```java
//     return type   name    parameters
public static String grade(int screen) {
    if (screen >= 17) {
        return "AA";
    }
    return "C";
}

String g = grade(18); // "AA"
```

A method that returns nothing has the return type `void`. It *does* something, like printing, but you can't store its result.

**Overloading** means several methods with the same name but different parameters. Java picks the one that matches your call:

```java
static String grade(int screen) { ... }
static String grade(int screen, boolean peaberry) { ... }

grade(17);        // calls the first
grade(17, true);  // calls the second
```

Overloads should do the same job. The longer one often just adds something and then calls the shorter one, so the rule lives in one place.

## 🪜 Steps

1. **Exercise 1: Sieve maths** (`exercise1/`). Two methods that convert between screen sizes and millimetres.
2. **Exercise 2: Sack labels** (`exercise2/`). Three overloads of the same method.
3. **Project: Grade Labeller** (`_start/src/GradeLabeller.java`).
   1. `grade(int screen)`: AA, AB or C, using the table above.
   2. `grade(int screen, boolean peaberry)`: return `PB` for a peaberry, otherwise call `grade(screen)`. Don't copy the size rules.
   3. `label(String factory, int screen, boolean peaberry)`: build `"<factory> Factory | Grade <grade> | screen <screen>"`. For a peaberry, leave out the screen part: `"Othaya Hills Factory | Grade PB"`.
   4. In `main`, ask the three questions and print the label.

## 🧪 Taste test

```bash
cd day06/_start/src
javac GradeLabeller.java
java GradeLabeller
```

To run the tests, copy `_end/src/GradeLabellerTest.java` next to your file, then from the repo root:

```bash
javac -cp lib/junit-platform-console-standalone-6.1.3.jar -d day06/_start/src day06/_start/src/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp day06/_start/src --scan-classpath
```

For an exercise, use `day06/exercise1/src` or `day06/exercise2/src` in both commands.

## 🆘 Stuck?

- **`missing return statement`**: some path through the method doesn't return. Make sure the last line returns something.
- **`method grade(int,boolean) is already defined`**: two overloads have the same parameter types. They must differ in the number or types of parameters; different parameter *names* aren't enough.
- **`'void' type not allowed here`**: you tried to use the result of a `void` method, for example by printing it. Make the method return a `String` and print that instead.

## ✅ Checklist
- [ ] Wrote methods that take parameters and return a value
- [ ] Called one method from another
- [ ] Overloaded a method
- [ ] Finished both exercises
- [ ] Grade Labeller's tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 7 is your first project day: run the weighing station for a whole afternoon, with a menu that keeps going until the clerk goes home.
