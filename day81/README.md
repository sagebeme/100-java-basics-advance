# Day 81 - Portfolio Project: Text to Morse Code Converter

## 📚 Learning Objectives
- Build a complete application
- Implement text conversion
- Create user interface
- Handle input/output
- Build portfolio project

## 🎯 Project Requirements
Create a Text to Morse Code Converter:
1. Convert text to Morse code
2. Convert Morse code to text
3. GUI or web interface
4. Copy to clipboard
5. Audio playback (optional)

## 📝 Implementation

### Step 1: Morse Code Mapping
Create mapping:

```java
public class MorseCodeConverter {
    private static final Map<Character, String> TEXT_TO_MORSE = new HashMap<>();
    
    static {
        TEXT_TO_MORSE.put('A', ".-");
        TEXT_TO_MORSE.put('B', "-...");
        // Add all mappings
    }
    
    public String textToMorse(String text) {
        return text.toUpperCase().chars()
            .mapToObj(c -> TEXT_TO_MORSE.getOrDefault((char)c, ""))
            .collect(Collectors.joining(" "));
    }
}
```

## 🎮 Project Structure
```
day81/
├── README.md
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── portfolio/
│                   ├── MorseConverter.java
│                   └── ConverterApp.java
```

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Implementation** and **Project Requirements** above; use main README for structure. **Quick reference:** `mvn compile exec:java` runs the console app; a JavaFX window (Days 18–22) is an optional extra.

## ✅ Checklist
- [ ] Implemented conversion
- [ ] Created interface
- [ ] Added features
- [ ] Tested application
- [ ] Completed project
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `ConverterApp.java` | A console app: type text or Morse, get the other back |
| `MorseConverter.java` | Text to Morse and back, with ` / ` between words |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day81
mvn compile exec:java
```

Or run the main class from your IDE.

## 🧪 How to Test

```bash
cd day81
mvn test
```

Runs `MorseConverterTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 82?** You'll build a Personal Website!
