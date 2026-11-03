# Day 57 - Exercise 1: Harambee cards

Achieng got into Moi University, and her family in Kisumu is holding a harambee to raise her fees. Instead of printing cards, they're sending each guest a link to their own card. `CardController` (already written) puts everything the card needs into the model. Write the template, `templates/card.html`, so that `/card/7` shows:

| Line | For guest 7 |
|---|---|
| Page title | `Harambee card for Mama Atieno` |
| Heading | `Karibu, Mama Atieno!` |
| What it's for | `You are invited to a harambee for Achieng's school fees at Moi University.` |
| When and where | `Saturday 12 December 2026 at St. Paul's Hall, Kisumu` |
| How to give | `Send by M-Pesa: Paybill 247247, account FEES-ACHIENG` |
| Only if they've pledged | `Thank you for pledging KES 5,000.` |
| Two links | `Make a pledge` to `/card/7/pledge`, and `I'll be there` to `/rsvp?guest=7&coming=yes` |

The model has `guest` (with `id`, `name` and `pledgedShillings`), `cause`, `date` (a `LocalDate`), `venue`, `paybill` and `account`.

Guest 9's "name" is `<script>alert('hi')</script>`, typed in by someone testing the form. `th:text` escapes it, so it shows as harmless text. `th:utext` ("unescaped") would run it as code in every browser that opens the card. Use `th:text` for anything a person typed.

## Files

- `src/main/resources/templates/card.html`: your code goes here.
- `src/main/java/com/learning/CardController.java` and `Guest.java`, and `static/css/kadi.css`: done.
- `src/test/java/com/learning/CardControllerTest.java`: the tests.
- `solution/src/main/resources/templates/card.html`: one answer.

```bash
cd day57/exercise1
mvn test
```

`mvn spring-boot:run` and open http://localhost:8080/card/7.

## 📌 Stuck?

- `${guest.name}` calls `getName()`. Between bars, text and expressions mix: `th:text="|Karibu, ${guest.name}!|"`.
- Dates: `${#temporals.format(date, 'EEEE d MMMM yyyy')}`. `EEEE` is the day's full name, `MMMM` the month's.
- Numbers: `${#numbers.formatInteger(guest.pledgedShillings, 1, 'COMMA')}` gives `5,000`.
- Links: `@{/card/{id}/pledge(id=${guest.id})}` fills `{id}` in the path; names in the brackets that aren't in the path become the query string: `@{/rsvp(guest=${guest.id},coming=yes)}`.
- `th:if="${guest.pledgedShillings > 0}"` keeps the tag only when it's true.
