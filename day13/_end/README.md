# Day 13 - Solution

`src/Weighbridge.java` is the fixed program, checked by `src/WeighbridgeTest.java`. Each fix has a comment saying what was wrong:

1. **`i <= grossKg.length` → `i < grossKg.length`.** The last index of an array of length 3 is 2. The stack trace pointed straight at this line.
2. **`factories[i] == factory` → `factories[i].equals(factory)`.** `==` asks "are these the same object?" Text typed at the keyboard is always a new object, so it never matched. `equals` compares the letters.
3. **`int total` → `double total`.** `total += kg` compiles even when `total` is an `int`, because `+=` quietly converts the result back to the variable's type. Every lorry lost its decimals, and then `total / netKg.length` was integer division too.

Bugs 2 and 3 never crash. They just give wrong answers, which makes them more dangerous. That's why the tests check exact numbers.
