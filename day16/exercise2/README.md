# Day 16 - Exercise 2: The sacco account

Boda boda riders in Kisumu save through a sacco. Each rider has an account, and every deposit and withdrawal is written down so nobody can argue about it later. Write `src/SaccoAccount.java`.

| Part | What it does |
|---|---|
| `new SaccoAccount("SAC-0042")` | An account with a balance of 0. |
| `deposit(shillings)` | Adds money. `0` or less throws `IllegalArgumentException`. |
| `withdraw(shillings)` | Takes money out and returns `true`. If the balance is too small, nothing changes and it returns `false`. |
| `getBalance()`, `getAccountNumber()` | Read the fields. |
| `history()` | Every deposit and withdrawal so far, oldest first. |

The history lines look like this:

```
Deposit 500
Withdrawal 200
Refused 900: balance 300
```

Money is a whole number of shillings, an `int`. A `double` can't hold most amounts exactly (`0.1 + 0.2` isn't `0.3`), which is why real money code never uses it.

`history()` must hand back a list nobody can change. A caller that does `history().clear()` would wipe the sacco's records, so return `List.copyOf(...)` (or `Collections.unmodifiableList(...)`), not the list itself.

## Files

- `src/SaccoAccount.java`: your code goes here.
- `src/SaccoAccountTest.java`: the tests.
- `solution/SaccoAccount.java`: one answer.

```bash
javac -cp "lib/*" -d day16/exercise2/src day16/exercise2/src/*.java
java -cp "day16/exercise2/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- Keep the history in a `private final List<String> history = new ArrayList<>();` field and `add` a line in each method.
- In `withdraw`, check the balance first. If it's too small, write the "Refused" line and `return false` before touching the balance.
