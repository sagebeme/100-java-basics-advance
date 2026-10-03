# Day 34 - Exercise 2: Shuffle without losing anything

A quiz should ask its questions in a different order each round. Write `shuffled(List<T> items, Random random)` in `src/Shuffle.java`. It returns a **new** list with the same items in a random order, using the **Fisher–Yates** shuffle, and leaves the original list alone:

1. Copy the list.
2. For `i` from the last index down to `1`: pick `j = random.nextInt(i + 1)` and swap the items at `i` and `j`.

The tests pass a seeded `Random`, so your "random" order is the same every run and can be checked.

## Files

- `src/Shuffle.java`: your code goes here.
- `src/ShuffleTest.java`: the tests.
- `solution/Shuffle.java`: one answer.

## 📌 Stuck?

`new ArrayList<>(items)` copies a list, and `Collections.swap(list, i, j)` swaps two items. (`Collections.shuffle(list, random)` exists too, but write it yourself first: it's eight lines.)
