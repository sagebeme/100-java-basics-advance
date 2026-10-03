# Day 32 - Solution

The finished Chama Reminders, checked by `ChamaRemindersTest.java`.

Things worth noticing:

- **Dates are never built for "this year" from a birthday.** `isBirthday` compares month and day numbers, so a 29 February birthday never tries to create a date that doesn't exist.
- **`Math.min(day, lengthOfMonth())`** turns "the 31st" into the last day of any month in one line.
- **`remindersFor` returns emails instead of sending them.** Deciding what to send and actually sending it are separate jobs, so the tests can check the decisions without a mailer at all.
- **`Mailer recording = received::add;`** A one-method interface can be implemented with a method reference. That's how small a fake can be.
- **`main` takes the date as an argument.** A program that only knows `LocalDate.now()` can only be tested once a year.
