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

## ✅ Checklist
- [ ] Implemented conversion
- [ ] Created interface
- [ ] Added features
- [ ] Tested application
- [ ] Completed project
- [ ] Committed code to Git

## 🚀 Next Steps
**Ready for Day 82?** You'll build a Personal Website!

