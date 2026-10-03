# Day 40 - Capstone Part 2: Safari Club

> Yesterday's deal finder worked for one person. Today it becomes a club: members sign up with their own wishlists, and each gets an email with only their deals.

## 📖 The story

Word got around about your long weekend deals. Your cousins want them, your colleagues want them, and your neighbour wants Lamu but not Kisumu. So Part 2 turns the Day 39 program into the **Safari Club**:

```
$ java SafariClub join "Wanjiku Kamau" wanjiku@example.com Mombasa:MBA:7000 Zanzibar:ZNZ:22000
Karibu, Wanjiku Kamau! You'll hear from us before every long weekend.

$ java SafariClub send
  to wanjiku@example.com: 2 long weekend deals for you, Wanjiku
  to achieng@example.com: 3 long weekend deals for you, Achieng
Searched 5 destinations for Fri 25 Dec – Sun 27 Dec (Christmas Day, Boxing Day). Sent 2 emails (in outbox/), skipped 1 member with no deals.
```

Wanjiku's email, as saved in `outbox/001.txt`:

```
Subject: 2 long weekend deals for you, Wanjiku

Habari Wanjiku,

Flights from Nairobi for Fri 25 Dec – Sun 27 Dec (Christmas Day, Boxing Day):

• Mombasa: KES 6,200 with Ndege Link, direct (KES 800 under your limit)
• Zanzibar: KES 21,500 with Ndege Link, direct (KES 500 under your limit)

Safari njema!
The Safari Club
```

Everything from Day 39 comes along unchanged: the holidays, the practice fare API and the client. From Day 32 come the `Email` record, the `Mailer` interface and the outbox.

## 🎯 Learning Objectives
- Reuse earlier work instead of rewriting it
- Validate data at the door with a record's **compact constructor**
- Store members in a JSON file, with each email address allowed only once
- Search each destination once, however many members want it
- Personalise output per user, and send nothing when there's nothing useful to say

## 🧰 Java you'll use

**A compact constructor** runs before a record's fields are set. It's the natural place to refuse bad data and tidy good data:

```java
public record Member(String name, String email, List<WishItem> wishes) {
    public Member {
        if (!isValidEmail(email)) throw new IllegalArgumentException("Not a valid email address: " + email);
        email = email.trim().toLowerCase();   // reassign the parameter; the field gets the tidy value
        wishes = List.copyOf(wishes);         // a copy nobody outside can change
    }
}
```

Every `Member` that exists has therefore been checked. No other code has to wonder.

**One search per destination.** If 200 members want Mombasa, the program should ask the API about Mombasa once, not 200 times. Collect the codes in a `Set`, search each one, and keep the results in a `Map<String, List<Fare>>` that every member's digest reads from.

**No deals, no email.** `Digest.emailFor` returns `Optional<Email>`, which is empty when a member has nothing to see. People unsubscribe from newsletters that waste their time.

## 🪜 Steps

1. **Exercise 1: The sign-up form** (`exercise1/`). Collect *every* problem with a form, not just the first.
2. **Exercise 2: Unsubscribe links** (`exercise2/`). Make links that can't be guessed, using SHA-256.
3. **Project: Safari Club** (`_start/src/`). Day 39's classes, `Member`, `MemberStore` and the `SafariClub` commands are ready.
   1. `Digest.dealsFor(member, fares)`: the cheapest fare within the limit for each of the member's wishes, biggest saving first.
   2. `Digest.emailFor(member, weekend, fares)`: the personal email in the example, or empty if there are no deals.
   3. Try the commands: `join` a few members with different wishes, `list` them, `send`, and read `outbox/`.

## 🧪 Run it and test it

From the repo root (on Windows, use `;` instead of `:`):

```bash
javac -cp "lib/*" -d day40/_end/src day40/_end/src/*.java
cd day40/_end/src
java -cp ".:../../../lib/*" SafariClub join "Wanjiku Kamau" wanjiku@example.com Mombasa:MBA:7000 Zanzibar:ZNZ:22000
java -cp ".:../../../lib/*" SafariClub join "Otieno Ouma" otieno@example.com Kisumu:KIS:4000
java -cp ".:../../../lib/*" SafariClub send 2026-10-03
cd ../../..
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp "day40/_end/src:lib/*" --scan-classpath
```

`members.json` holds real people's email addresses once you use this for real, so think before you commit it. A file of addresses is personal data under Kenya's Data Protection Act.

## 🆘 Stuck?

- **`Wanjiku@Example.com` and `wanjiku@example.com` both joined**: lower-case the email in the compact constructor, and look members up by the lower-case form.
- **The subject says "1 long weekend deals"**: pick the word with `deals.size() == 1 ? "deal" : "deals"`.
- **A member with no deals got an empty email**: return `Optional.empty()` before building anything.
- **Searches are slow with many members**: search each destination code once, outside the loop over members.

## ✅ Checklist
- [ ] Reused Day 32's mailer and Day 39's deal finder
- [ ] Validated members in a compact constructor
- [ ] Stored members in JSON, one per email address
- [ ] Searched each destination once
- [ ] Sent personal emails, and nothing to members with no deals
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Days 41 and 42 step away from Java for a moment: HTML and CSS, the language of every web page you'll build from Day 54 on.
