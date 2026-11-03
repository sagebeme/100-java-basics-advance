# Day 81 - Portfolio Project: Text to Morse Code Converter

> The portfolio days begin. From now on each day is a small, complete app you could show an employer. First, a converter between text and Morse code, which turns out to be a neat lesson in two-way maps and careful parsing.

## 📖 The story

Baraka's scout troop in Nyeri is working towards a signalling badge. They practise Morse with torches at night, but checking each other's messages by hand is slow and full of arguments about whether that was a dash. Baraka wants a little tool: type text and get Morse, paste Morse and get text, and have the result copied, ready to send in the troop's WhatsApp group.

```
1) Text to Morse
2) Morse to Text
3) Exit
Choose an option: 1
Enter input: SOS Nairobi
Result: ... --- ... / -. .- .. .-. --- -... ..
(copied to clipboard)
```

In Morse, letters are separated by a space and words by ` / `, so the tool has to read that format back exactly.

## 🎯 Learning Objectives
- Build a two-way lookup from one table, so the two directions can never disagree
- Split and join text precisely: letters, words, and the separators between them
- Decide what happens with input that can't be converted, and test it
- Prove a conversion works with a round-trip test
- Copy to the system clipboard, and carry on without one on a server
- Write a console menu that handles bad choices and the end of input

## 🧰 What you'll use

**One table, two maps.** Each `put` fills both directions at once:

```java
private static void put(char letter, String morse) {
    TEXT_TO_MORSE.put(letter, morse);
    MORSE_TO_TEXT.put(morse, letter);
}
```

**Splitting.** `text.trim().toUpperCase().split("\\s+")` gives the words, however many spaces there are between them. Back from Morse, `split(" / ")` gives the words and `split("\\s+")` the letters.

**Round trips.** For any text made of known characters, `morseToText(textToMorse(text))` should give the same text back in capitals. One test can check dozens of inputs that way.

**The clipboard** comes from AWT:

```java
Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
```

On a machine with no display it throws `HeadlessException`, so the app catches it and simply skips the copy.

## 🪜 Steps

**Your brief** (the `day81/` folder is one finished version: build your own first, then compare).

1. `MorseConverter` with letters, digits and common punctuation.
2. `textToMorse`: letters joined by a space, words by ` / `, case ignored, unknown characters dropped.
3. `morseToText`: the reverse, skipping a token it doesn't recognise rather than crashing.
4. Tests: one word, mixed case, several words, digits and punctuation, unknown characters, and a round trip over many inputs.
5. `ConverterApp`: a menu that rejects bad choices before asking for input, copies results, and exits cleanly at the end of input.
6. Make it yours: play the Morse as beeps (`javax.sound.sampled`), or build a JavaFX window with two text areas.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day81
mvn test
mvn -q compile exec:java
```

## 🆘 Stuck?

- **Words run together when converting back**: the word separator is ` / ` with spaces round it. Split on that before splitting into letters.
- **`null` appears in the Morse**: a character has no mapping. Filter out the nulls, or decide to show `?`, and test whichever you choose.
- **`NoSuchElementException` after Ctrl+D**: check `scanner.hasNextLine()` before every `nextLine()`.
- **Clipboard error on a server or in CI**: catch `HeadlessException`; there's no clipboard without a display.

## ✅ Checklist
- [ ] Text to Morse and back, from one table
- [ ] Unknown characters and tokens handled on purpose
- [ ] A round-trip test
- [ ] A console app that copies results and handles bad input
- [ ] Added one extra of my own (sound or a window)
- [ ] Committed code to Git

## ➡️ Next

Day 82 builds your personal portfolio website with Spring Boot: projects, an about page and a contact form.
