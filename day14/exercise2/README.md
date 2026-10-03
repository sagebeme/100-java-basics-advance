# Day 14 - Exercise 2: Placing the order

The hardest part of a first visit is knowing what to *say*. Write two methods in `src/Order.java` that build the sentence for you:

`orderSentence(String size, String drink, boolean takeaway)`:

| Call | Result |
|---|---|
| `orderSentence("medium", "Latte", true)` | `"One medium latte to take away, please."` |
| `orderSentence("small", "Americano", false)` | `"One small americano to have here, please."` |

`orderFor(int people, String drink)`, for ordering for friends. Write the number as a word from one to five, then use digits. Add an `s` when there's more than one drink:

| Call | Result |
|---|---|
| `orderFor(1, "Cappuccino")` | `"One cappuccino, please."` |
| `orderFor(3, "Flat White")` | `"Three flat whites, please."` |
| `orderFor(7, "Espresso")` | `"7 espressos, please."` |

## Files

- `src/Order.java`: your code goes here.
- `src/OrderTest.java`: the tests.
- `solution/Order.java`: one answer.

## 📌 Stuck?

- Drinks are written in lower case inside a sentence: `drink.toLowerCase()`.
- Keep the number words in an array, `{"One", "Two", "Three", "Four", "Five"}`. The word for `people` is at index `people - 1`, but only when `people <= 5`.
