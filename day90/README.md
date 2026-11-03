# Day 90 - Portfolio Project: PDF to Audio Converter

> Some documents you'd rather listen to than read: on a long matatu ride, while cooking, or when your eyes are tired. Today's app pulls the text out of a PDF and has the computer read it aloud into a WAV file.

## 📖 The story

Mzee Kariuki in Murang'a is a retired teacher whose eyesight is failing. His grandson Brian sends him PDFs (the church newsletter, county notices, articles about coffee prices) and Mzee can't read the small print any more. Brian wants a tool that turns a PDF into audio Mzee can play on his phone.

```
$ mvn -q compile exec:java -Dexec.args="newsletter.pdf newsletter.wav --play"
Extracted 2841 characters of text.
Audio saved to .../newsletter.wav
```

No internet and no API key: the speech comes from the voice engine already on the computer.

## 🎯 Learning Objectives
- Read text from a PDF with Apache PDFBox
- Run another program from Java with `ProcessBuilder`, with a timeout, and check that it worked
- Pass untrusted text to another program safely, through a file instead of the command line
- Hide three different speech engines behind one interface, and pick one by operating system
- Play a WAV file with `javax.sound.sampled`
- Test code that runs other programs, without needing those programs installed

## 🧰 What you'll use

**PDFBox** reads the text out of a PDF:

```java
try (PDDocument document = Loader.loadPDF(pdfFile)) {
    return new PDFTextStripper().getText(document);
}
```

A scanned PDF is just pictures of pages, so it has no text to extract. The converter says so instead of making a silent WAV.

**One interface, three engines.** There's no good offline speech library in pure Java, but every system has an engine:

| System | Engine | Used by |
|---|---|---|
| Windows | SAPI, through PowerShell | `WindowsSpeechSynthesizer` |
| macOS | the `say` command | `CommandSpeechSynthesizer.macSay()` |
| Linux | `espeak-ng` (install it first) | `CommandSpeechSynthesizer.espeakNg()` |

`SpeechSynthesizer.forThisComputer()` checks `os.name` and picks one, and the converter only knows about the interface.

**Running a program safely.** Text from a PDF could contain anything, including `--help; rm -rf ~`. So it's never put on the command line: it's written to a temp file, and the speech program reads that file.

```java
ProcessBuilder builder = new ProcessBuilder("espeak-ng", "-w", wav.toString(), "-f", textFile.toString());
builder.redirectErrorStream(true);
Process process = builder.start();
boolean finished = process.waitFor(60, TimeUnit.SECONDS);
// then check: finished? exit code 0? a WAV file that isn't empty?
```

**Testing without the engine.** `CommandSpeechSynthesizer` takes the command as a function, so a test can swap in a one-line shell command that checks it got the text file and copies a sample WAV into place. That tests the timeout, exit-code and missing-program handling on any Linux or Mac machine.

## 🪜 Steps

**Your brief** (the `day90/` folder is one finished version: build your own first, then compare).

1. `PdfTextExtractor`, tested with a PDF the test creates with PDFBox.
2. The `SpeechSynthesizer` interface, the Windows engine, and the command engine for macOS and Linux.
3. `PdfToAudioConverter`: extract, refuse a PDF with no text, speak.
4. `AudioPlayer` to play the result, and `PdfToAudioApp` with `--play`.
5. Tests: extraction, a text-free PDF, a stand-in speech program (text passed through a file, a failure, a missing program, no output), and the real engine where it's installed.
6. Make it yours: split long documents into one WAV per page, choose a voice and speed, or convert to MP3 for phones.

## 🧪 Run it and test it

Needs JDK 21 and Maven. On Linux, install the speech engine first (`sudo apt install espeak-ng`). From the repository root:

```bash
cd day90
mvn test
mvn -q compile exec:java -Dexec.args="some.pdf some.wav --play"
```

Tests for an engine that isn't on your computer are skipped, not failed.

## 🆘 Stuck?

- **`Couldn't start the speech program espeak-ng`**: it isn't installed. Install it with your package manager.
- **The WAV is silent or tiny**: the PDF is probably scanned images. Extracting text from those needs OCR (Tesseract is the usual free one).
- **The program hangs forever**: read its output before waiting for it, or a full output buffer blocks it. And always wait with a timeout.
- **`--play` returns before the audio finishes**: a `Clip` plays in the background. Wait for its `STOP` event before the program exits.

## ✅ Checklist
- [ ] Text extracted from a PDF, and text-free PDFs refused
- [ ] Speech on my operating system, behind an interface
- [ ] PDF text passed to the speech program through a file
- [ ] The process-handling tested with a stand-in program
- [ ] Added pages, voices or MP3
- [ ] Committed code to Git

## ➡️ Next

Day 91 builds a colour palette generator: pick one colour, get matching ones from the colour wheel, and export them as CSS or JSON.
