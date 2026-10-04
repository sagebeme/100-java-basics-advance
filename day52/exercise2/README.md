# Day 52 - Exercise 2: Spotting spam accounts

Following back a spam account helps it look real to everyone else. Write `looksReal(Follower f)` in `src/SpamFilter.java`. An account looks real when it has **at least 3 posts**, **at least 20 followers**, and a username with none of: `free`, `win`, `giveaway`, `promo`, `xx`, or a run of **3 or more digits** (ignoring capitals).

Rules like these are never perfect. They're a first filter, and a person should still look at the edge cases.

## 📌 Stuck?

One regular expression with `|` between the alternatives and `Pattern.CASE_INSENSITIVE`; `\d{3,}` is "three or more digits". One answer is in `solution/SpamFilter.java`.
