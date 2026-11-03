# Day 30 - Exercise 1: The M-Pesa agent's float

An M-Pesa agent in Machakos keeps a float of cash. When it runs low, a customer wanting KES 700 has to be told "sorry, I'm short by 200" and the agent carries on with the next person in the queue. Today is about the different ways things fail, and catching each one on purpose.

Three kinds of failure come up, and each is a different kind of exception:

| What went wrong | Exception | Kind |
|---|---|---|
| The customer typed `abc` | `NumberFormatException`, from `Integer.parseInt` | unchecked |
| The amount is 0 or less | `IllegalArgumentException`, which you throw | unchecked: a mistake by the caller |
| The float can't cover it | `InsufficientFundsException`, which you write | **checked**: a normal event that every caller must handle |

Write three classes:

- `src/InsufficientFundsException.java`: `new InsufficientFundsException(balance, amount)` has the message `"Short by KES 300"` and `getShortBy()` returns `300`.
- `src/AgentFloat.java`: `withdraw(amount)` throws an `IllegalArgumentException` with the message `"Amount must be above 0, not -50"` for 0 or less, the checked exception if it's more than the balance (leaving the balance alone), and otherwise takes it off.
- `src/Agent.java`: `serve(requests)` goes through the queue of typed amounts and returns one outcome for each, in order. Spaces around a number are fine.

| Request | Outcome |
|---|---|
| `"500"` | `Paid 500` |
| `"abc"` | `Not an amount: abc` |
| `"-50"` | `Refused: Amount must be above 0, not -50` |
| `"700"` with 500 left | `Declined: Short by KES 200` |

`customersServed()` counts everyone who reached the counter, whatever happened. Count them in a `finally` block.

## Files

- `src/InsufficientFundsException.java`, `src/AgentFloat.java` and `src/Agent.java`: your code goes here.
- `src/AgentTest.java`: the tests.
- `solution/`: one answer.

```bash
javac -cp "lib/*" -d day30/exercise1/src day30/exercise1/src/*.java
java -cp "day30/exercise1/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 📌 Stuck?

- A checked exception `extends Exception`. Pass the message up with `super("Short by KES " + (amount - balance))`.
- `NumberFormatException` is a kind of `IllegalArgumentException`. Catch blocks are tried top to bottom, so the narrower one must come first. Put it second and `javac` refuses: "exception NumberFormatException has already been caught".
- Put `customersServed++` in `finally` and it runs whether the `try` finished or a `catch` ran.
