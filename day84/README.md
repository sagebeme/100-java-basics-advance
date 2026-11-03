# Day 84 - Portfolio Project: Image Watermark Tool

> Photographers lose work every day to people who screenshot it and post it as their own. Today you build a tool that stamps a name on a photo, or on a whole folder of photos at once.

## 📖 The story

Amani photographs weddings and safari trips around Naivasha. She posts previews on Instagram, and twice this year she found her photos on other people's pages with no credit. She wants every preview to carry "© Amani 2026" in a corner: readable on dark and light photos, see-through enough not to spoil the picture, and done to 200 photos at a time, not by hand in an editor.

```
$ java -cp target/classes com.portfolio.WatermarkApp lake.png lake-preview.jpg "© Amani 2026" bottom_right 0.7
Watermarked image saved to .../lake-preview.jpg
```

## 🎯 Learning Objectives
- Load, draw on and save images with `BufferedImage`, `Graphics2D` and `ImageIO`
- Make drawing see-through with `AlphaComposite`
- Place text in a corner using font measurements
- Never change the caller's image: work on a copy
- Choose the file format from the output name, and handle JPEG's lack of transparency
- Process a whole batch of files, and test images by checking their pixels

## 🧰 What you'll use

**Drawing on an image:**

```java
Graphics2D g = result.createGraphics();
g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));  // 60% opaque
g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, source.getHeight() / 15));
g.setColor(Color.BLACK);
g.drawString(text, x + 1, y + 1);   // a shadow, so it reads on light photos
g.setColor(Color.WHITE);
g.drawString(text, x, y);           // the text, so it reads on dark ones
g.dispose();                         // always free the graphics
```

**Measuring text.** `FontMetrics.stringWidth(text)` and `getHeight()` tell you how big the text will be, so you can put it 10 pixels from the bottom-right corner on any size of photo.

**Formats.** PNG keeps transparency and every pixel exactly. JPEG is smaller, but has no transparency, and Java's JPEG writer refuses images that have an alpha channel. So `saveImage` draws the image onto a white background before writing a `.jpg`, and `formatFor(file)` picks the format from the name, so a file called `.jpg` really is a JPEG.

**Testing images.** Watermark a plain white image, then read pixels with `getRGB(x, y)`: the bottom-right corner should have changed and the top-left shouldn't. Save and reload, and check the size and the file's first bytes (`FF D8` for JPEG).

## 🪜 Steps

**Your brief** (the `day84/` folder is one finished version: build your own first, then compare).

1. `WatermarkPosition`: the four corners and the centre.
2. `WatermarkTool`: `loadImage`, `saveImage`, `addTextWatermark` and `addImageWatermark` (a logo), with opacity checked to be between 0 and 1.
3. `batchWatermark(files, outputDir, ...)`: a `-watermarked.png` for every input.
4. `WatermarkApp`: input, output, text, and optional position and opacity, with a clear message for bad arguments.
5. Tests: the source isn't changed, pixels change where the watermark goes, a logo, the centre, save and reload, a missing file, a batch, JPEG output, and bad opacity.
6. Make it yours: tile the watermark diagonally across the whole photo, or add a JavaFX window with a preview.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day84
mvn test
mvn -q compile exec:java -Dexec.args="photo.jpg photo-marked.jpg '© Your Name' bottom_right 0.6"
```

## 🆘 Stuck?

- **The original photo changed too**: you drew on the image you were given. Draw on a copy.
- **`ImageIO.write` returns `false` for a `.jpg`**: the image has transparency. Draw it onto an RGB image first.
- **The text is tiny on big photos and huge on small ones**: size the font from the image's height, not a fixed number.
- **The text is cut off at the edge**: place it using `stringWidth` and the font's ascent, not just `(width - 100, height - 10)`.
- **`HeadlessException` on a server**: fonts and images work without a screen; only windows don't. Run with `-Djava.awt.headless=true` if needed.

## ✅ Checklist
- [ ] Text and logo watermarks, at any corner or the centre, with opacity
- [ ] The source image is never changed
- [ ] PNG and real JPEG output
- [ ] A whole folder in one go
- [ ] Tests that check real pixels and files
- [ ] Added a tiled or previewed watermark
- [ ] Committed code to Git

## ➡️ Next

Day 85 measures how fast and how accurately you type.
