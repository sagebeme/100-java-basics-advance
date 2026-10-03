# Day 32 - Chama Reminders: Email and Dates

> Your mum's chama has 14 members, two birthdays this month and a contribution due on the 5th. Write the program that never forgets.

## 📖 The story

A **chama** is a savings group: friends, neighbours or colleagues who each put in a fixed amount every month and take turns receiving the pot. The treasurer's hardest job isn't the money, it's the reminders: "Your KES 2,000 is due on Friday", "Happy birthday, Wanjiku!".

Today you'll build `ChamaReminders`. It reads the member list, works out what's due today, and sends the right emails:

```
$ java ChamaReminders members.csv 2026-10-02
Sent 2 emails (saved in outbox/):
  to wanjiku@example.com: Happy birthday, Wanjiku!
  to otieno@example.com: Your chama contribution is due on Mon 5 Oct
```

The program never talks to a real mail server in this lesson. It sends through a `Mailer` **interface**, and today's `Mailer` writes each email to a file in `outbox/`. At the end of the lesson you'll see how to swap in a real SMTP sender without changing anything else.

## 🎯 Learning Objectives
- Work with dates using `LocalDate`, `Period` and `ChronoUnit`
- Format and parse dates with `DateTimeFormatter`
- Handle awkward calendar cases: 29 February, and a "31st" in a 30-day month
- Hide a service behind an interface so you can test without it
- Read a CSV file and turn each row into a `record`
- Build the text of an email: headers, subject and body

## 🧰 Java you'll use

**`LocalDate`** is a date with no time and no time zone, which is exactly right for birthdays and due dates:

```java
LocalDate today = LocalDate.of(2026, 10, 2);
today.plusDays(3);                  // 2026-10-05
today.getDayOfWeek();               // FRIDAY
today.lengthOfMonth();              // 31
ChronoUnit.DAYS.between(today, LocalDate.of(2026, 12, 25)); // 84
Period.between(LocalDate.of(1990, 5, 1), today).getYears();  // 36
```

**`DateTimeFormatter`** turns dates into text and back:

```java
DateTimeFormatter nice = DateTimeFormatter.ofPattern("EEE d MMM", Locale.ENGLISH);
nice.format(LocalDate.of(2026, 10, 5));   // "Mon 5 Oct"
LocalDate.parse("1990-05-01");            // ISO format needs no formatter
```

Always pass a `Locale` when a pattern contains day or month names. Otherwise the output depends on the computer it runs on.

**An interface** says *what* something can do, not *how*:

```java
public interface Mailer {
    void send(Email email);
}
```

`OutboxMailer` writes files, a test uses a `RecordingMailer` that just remembers what it was given, and a real `SmtpMailer` could talk to Gmail. `ChamaReminders` only ever sees `Mailer`, so it works with all three.

**Records** are short classes for plain data:

```java
public record Member(String name, String email, LocalDate birthday, int contributionDay) {}
```

## 🪜 Steps

1. **Exercise 1: Date maths** (`exercise1/`). Ages, days until a birthday, and a friendly date format.
2. **Exercise 2: Build an email** (`exercise2/`). Check an address and turn an `Email` into the text a mail server sends.
3. **Project: Chama Reminders** (`_start/src/`).
   1. `Member.fromCsvLine` turns `Wanjiku,wanjiku@example.com,1991-10-02,5` into a `Member`.
   2. `isBirthday(member, today)`: same month and day. Someone born on 29 February celebrates on 28 February when the year isn't a leap year.
   3. `contributionDate(member, today)`: this month's due date. If the member's day is 31 and the month only has 30 days, it's the last day of the month.
   4. `remindersFor(members, today)`: a birthday email for each birthday today, and a contribution reminder for each member whose due date is exactly **3 days** away.
   5. `main`: read the CSV, send everything through an `OutboxMailer`, and print what was sent.

## 🧪 Run it and test it

From the repo root (on Windows, use `;` instead of `:` between classpath entries):

```bash
javac -cp "lib/*" -d day32/_end/src day32/_end/src/*.java
java -cp "day32/_end/src:lib/*" ChamaReminders day32/_end/src/members.csv 2026-10-02
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp "day32/_end/src:lib/*" --scan-classpath
```

Work in `_start/src` the same way: copy `_end/src/ChamaRemindersTest.java` next to your files and swap `_end` for `_start` in the commands. For an exercise, compile and run `day32/exercise1/src`.

### Sending real email later

When you have a mail account to send from, add [Jakarta Mail](https://jakarta.ee/specifications/mail/) and write a second `Mailer`:

```java
public class SmtpMailer implements Mailer {
    public void send(Email email) {
        // Session session = Session.getInstance(props, authenticator);
        // Transport.send(message built from email)
    }
}
```

Read the account and password from **environment variables** (Day 35), never from the code. Gmail needs an *app password*, not your normal one. Nothing else in the project changes, and that's the point of the interface.

## 🆘 Stuck?

- **`DateTimeParseException`**: `LocalDate.parse` wants `yyyy-MM-dd` with leading zeros: `1991-10-02`, not `1991-10-2`.
- **A 29 February birthday crashes**: `LocalDate.of(2027, 2, 29)` throws, because 2027 has no 29 February. Compare month and day numbers instead of building a date in this year.
- **The contribution reminder never fires at the start of a month**: if today is 30 September and the due date is the 3rd, the next due date is in October. Work out this month's due date, and if it has already passed, use next month's.
- **Day names come out in another language**: pass `Locale.ENGLISH` to `ofPattern`.

## ✅ Checklist
- [ ] Calculated an age and the days until a birthday
- [ ] Formatted a date with a pattern and a `Locale`
- [ ] Handled 29 February and short months
- [ ] Sent email through an interface, and tested it with a fake
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 33 leaves your own computer: call a real weather API over HTTP and decide whether today's laundry will dry.
