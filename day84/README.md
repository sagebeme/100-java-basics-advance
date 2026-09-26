# Day 84 - Portfolio Project: Image Watermark Tool

## 📚 Learning Objectives
- Work with image processing
- Add watermarks to images
- Handle file operations
- Create utility tool
- Build practical application

## 🎯 Project Requirements
Create Image Watermark Tool:
1. Load images
2. Add text/image watermark
3. Adjust watermark position
4. Save watermarked image
5. Batch processing (optional)

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** Java ImageIO or BufferedImage; run from IDE or `mvn exec:java`.

## ✅ Checklist
- [ ] Can load images
- [ ] Can add watermarks
- [ ] Can save images
- [ ] Completed tool
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `WatermarkApp.java` | The command-line app |
| `WatermarkPosition.java` | Where the watermark goes |
| `WatermarkTool.java` | Adds a text or image watermark to an image, or a whole folder of images |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day84
mvn compile exec:java -Dexec.args="photo.jpg watermarked.jpg '© Amina 2026' BOTTOM_RIGHT 0.6"
```

Arguments: input image, output image, text, then optionally the position (`TOP_LEFT`, `TOP_RIGHT`, `BOTTOM_LEFT`, `BOTTOM_RIGHT`, `CENTER`) and opacity (0 to 1).

## 🧪 How to Test

```bash
cd day84
mvn test
```

Runs `WatermarkToolTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 85?** You'll build a Typing Speed Test App!
