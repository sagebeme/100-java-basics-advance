# Day 50 - Exercise 2: Check before you post

Before the bot posts anything, it should check it. Write `problems(String text, String lastPost)` in `src/PostCheck.java`, returning every problem, in this order:

| Problem | Message |
|---|---|
| empty or only spaces | `Nothing to post.` |
| over 280 characters | `Too long: 281 characters (max 280).` |
| more than 3 hashtags (words starting with `#`) | `Too many hashtags.` |
| 10 or more letters and every letter is a capital | `Don't shout.` |
| the same as `lastPost`, ignoring spaces at the ends and capitals | `You just posted that.` |

## 📌 Stuck?

Count hashtags with `text.split("\\s+")` and `startsWith("#")`. For shouting, keep only the letters (`text.replaceAll("[^\\p{L}]", "")`) and compare with their upper-case version. One answer is in `solution/PostCheck.java`.
