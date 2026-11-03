# Day 31 - Capstone Project: Kadi, a Swahili Flash Card App

> The Intermediate capstone. A JavaFX flash card app that saves your cards, remembers which ones you know, and spends your practice time on the ones you don't.

## 📖 The story

Hannah has just moved to Nairobi from Hamburg for work. Her colleagues are patient, but she wants to say more than "jambo" by Christmas. Her Kenyan flatmate wrote fifteen words on index cards and pinned them to a cork board, and now Hannah wants them on her laptop: *kadi* is Swahili for card.

The app:

- shows one card at a time: `Question: Asante sana`;
- **Show Answer** flips it: `Answer: Thank you very much`;
- **I Know This** and **I Don't Know** mark the card;
- **Next Card** moves on, *skipping the cards you already know*, until you know them all;
- **Add Card** puts a new card in the deck;
- **Save Cards** writes the deck to `flashcards.txt`, and the app loads it again next time (or press **Load Cards**).

A starter deck of fifteen Swahili words is in `flashcards.txt`, one card per line:

```
Asante sana|Thank you very much|false
Hakuna matata|No worries|false
```

This day brings together most of Days 15 to 30: classes, a list of objects, JavaFX controls and CSS, events, reading and writing files, and logic you can test on its own (`FlashCardAppLogic`).

## 🎯 Learning Objectives
- Plan and build a complete GUI application on your own
- Keep the app's data (cards, the current card, what's known) in objects and fields
- Save to and load from a file in a format you designed
- Choose what to show next from the data, not just in a fixed order
- Pull the rules out into a class with tests

## 🧰 Java you'll use

Nothing new. A few reminders:

**A card** is a small class: `question`, `answer`, and `boolean known`.

**Choosing the next card.** `FlashCardAppLogic.nextToStudy(currentIndex, known)` looks at the cards after the current one, going round to the start, and returns the first one not yet known, or `-1` when every card is known:

```java
for (int step = 1; step <= known.length; step++) {
    int i = (currentIndex + step) % known.length;
    if (!known[i]) return i;
}
return -1;
```

The `%` makes it wrap around, and the last step lands back on the current card, so if it's the only one left you keep practising it.

**The file format** is one card per line: question, answer and whether it's known, separated by `|`. `formatCard` and `parseCard` turn a card into a line and back.

## 🪜 Steps

**Project: Kadi** (`_start/src/FlashCardApp.java`). There are no exercises today: this is the exercise.

1. `FlashCardAppLogic`: `formatCard`, `parseCard`, `nextIndex` and `nextToStudy`. Copy the tests from `_end/src/FlashCardAppLogicTest.java` and make them pass first.
2. The window: the card, the buttons, two fields to add a card, and a status line. `kadi.css` makes it look like an index card on a cork board.
3. Loading at start-up, **Load Cards** and **Save Cards**.
4. **Show Answer**, **I Know This**, **I Don't Know** and **Next Card**.
5. Add a few cards of your own. Swahili, Sheng, French, chemistry symbols: your deck, your choice.

## 🧪 Run it and test it

The logic and its tests need no JavaFX. From the repo root:

```bash
javac -cp "lib/*" -d day31/_end/src day31/_end/src/FlashCardAppLogic.java day31/_end/src/FlashCardAppLogicTest.java
java -cp "day31/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

To open the app, with `FX` pointing at your JavaFX SDK's `lib` folder (see Day 18):

```bash
cd day31/_end/src
javac --module-path $FX --add-modules javafx.controls *.java
java --module-path $FX --add-modules javafx.controls FlashCardApp
```

Use `_start/src` for your own version. On Windows, use `;` instead of `:` in `-cp`. Saving writes your progress into `flashcards.txt`, so Git will show it as changed: commit it if you want your deck in your history.

## 🆘 Stuck?

- **Next Card shows the same card forever**: it's the only card you don't know yet. That's on purpose.
- **Known cards keep coming back**: Next Card still uses `nextIndex`, which goes through every card. Use `nextToStudy`.
- **The deck is empty when the app starts**: run it from the folder that holds `flashcards.txt`.
- **`IndexOutOfBoundsException` with no cards**: check `cards.isEmpty()` at the top of every button's handler.
- **Where do I start?** The logic class and its tests. Then a window that shows one hard-coded card. Then the file.

## ✅ Checklist
- [ ] Cards load from the file when the app starts
- [ ] Show Answer flips the card, and flips it back
- [ ] Next Card skips the cards you know, and says so when you know them all
- [ ] New cards can be added and saved
- [ ] The logic tests pass
- [ ] Committed code to Git

## ➡️ Next

That's the Intermediate stage done. Day 32 starts Intermediate+, where your programs talk to the outside world, beginning with sending email.
