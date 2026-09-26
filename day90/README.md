# Day 90 - Portfolio Project: PDF to Audio Converter

## 📚 Learning Objectives
- Work with PDF files
- Text-to-speech conversion
- File processing
- Audio generation
- Build utility tool

## 🎯 Project Requirements
Create PDF to Audio Converter:
1. Load PDF files
2. Extract text
3. Convert to audio
4. Save audio file
5. Playback option

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** Run from IDE or `mvn exec:java`; Apache PDFBox reads the PDF; the speech step uses Windows' built-in speech engine.

## ✅ Checklist
- [ ] Can read PDFs
- [ ] Can extract text
- [ ] Can generate audio
- [ ] Can save files
- [ ] Completed converter
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `AudioPlayer.java` | Plays the .wav file |
| `PdfTextExtractor.java` | Reads the text out of a PDF with Apache PDFBox |
| `PdfToAudioApp.java` | The command-line app |
| `PdfToAudioConverter.java` | Puts the two together |
| `WindowsSpeechSynthesizer.java` | Speaks text into a .wav file with Windows' built-in speech engine, through PowerShell |

The speech step uses Windows' built-in speech engine, so converting to audio **only works on Windows**. Reading the PDF works everywhere.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day90
mvn compile exec:java -Dexec.args="book.pdf book.wav --play"
```

Arguments: the PDF, the .wav file to write, and `--play` to play it straight away.

## 🧪 How to Test

```bash
cd day90
mvn test
```

Runs `PdfTextExtractorTest`, `PdfToAudioConverterTest` and `WindowsSpeechSynthesizerTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 91?** You'll build a Color Palette Generator!
