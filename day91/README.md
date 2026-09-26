# Day 91 - Portfolio Project: Color Palette Generator

## 📚 Learning Objectives
- Generate color palettes
- Work with colors
- Create visual tools
- Export palettes
- Build design tool

## 🎯 Project Requirements
Create Color Palette Generator:
1. Generate color schemes
2. Display colors
3. Copy color codes
4. Export palettes
5. Beautiful UI

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** `mvn spring-boot:run`, then open http://localhost:8080.

## ✅ Checklist
- [ ] Can generate palettes
- [ ] Can display colors
- [ ] Can copy codes
- [ ] Can export
- [ ] Completed generator
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `controller/PaletteController.java` | `GET /` shows a palette, `GET /export` downloads it as CSS or JSON |
| `model/HslColor.java` | A colour as hue, saturation, lightness |
| `model/RgbColor.java` | A colour as RGB and hex |
| `service/ColorMath.java` | Converts between RGB and HSL |
| `service/PaletteExporter.java` | Writes a palette as CSS variables or JSON |
| `service/PaletteGenerator.java` | Builds complementary, analogous, triadic and monochromatic palettes |
| `service/PaletteScheme.java` | The kinds of palette |

Also in `src/main/resources/`: `templates/index.html`.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day91
mvn spring-boot:run
```

Then open http://localhost:8080. Stop it with Ctrl+C. Or run `Application` from your IDE.

## 🧪 How to Test

```bash
cd day91
mvn test
```

Runs `ColorMathTest`, `PaletteControllerTest`, `PaletteExporterTest`, `PaletteGeneratorTest` and `RgbColorTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 92?** You'll build a Web Scraper!
