# Day 91 - Portfolio Project: Color Palette Generator

> Designers don't pick colours at random: they start from one and use the colour wheel to find others that go with it. Today's web app does that maths, shows the swatches, and exports them ready to paste into CSS.

## 📖 The story

Akinyi is opening a juice bar in Kisumu, and her cousin is designing the menu, the sign and an Instagram page. They agree on one colour, a deep mango orange, but then argue for a week about what goes with it. Akinyi wants a tool: pick a colour, choose a kind of harmony, see the palette, and download it for the designer.

- **Complementary**: the colour and its opposite on the wheel (strong contrast).
- **Analogous**: the colour and its neighbours, 30° either side (calm, related).
- **Triadic**: three colours 120° apart (lively).
- **Monochromatic**: one hue in five lightnesses (quiet, consistent).

```
GET /export?baseColor=%23E8892B&scheme=ANALOGOUS&format=css

:root {
  --color-1: #E82B2C;
  --color-2: #E8892B;
  --color-3: #E8E72B;
}
```

## 🎯 Learning Objectives
- Convert colours between RGB and HSL, and know why HSL is the one to do maths in
- Generate harmonies by rotating the hue around the colour wheel
- Model colours as records that check their own values
- Validate input strictly: a hex colour is exactly six hex digits
- Serve a file download with the right content type and `Content-Disposition`
- Test colour maths with known values and round trips

## 🧰 What you'll use

**RGB vs HSL.** RGB says how much red, green and blue light to mix. HSL says *hue* (the angle on the colour wheel, 0–360°), *saturation* and *lightness*. "The opposite colour" is just `hue + 180` in HSL, and there's no simple equivalent in RGB. So: convert to HSL, rotate or adjust, convert back.

```java
HslColor baseHsl = colorMath.rgbToHsl(base);
colorMath.hslToRgb(baseHsl.withHue(baseHsl.h() + 180));   // the complement
```

`withHue` wraps the angle back into 0–360, so `350 + 30` becomes `20`.

**Records that check themselves.** `RgbColor`'s compact constructor clamps each value to 0–255, and `fromHex` accepts only `#` plus six hex digits. (Checking the length alone isn't enough: `Integer.parseInt("-1", 16)` happily returns −1.)

**A download:**

```java
return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType("text/css"))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"palette.css\"")
        .body(css);
```

`attachment` makes the browser save the file instead of showing it.

## 🪜 Steps

**Your brief** (the `day91/` folder is one finished version: build your own first, then compare).

1. `RgbColor` (with `toHex` and a strict `fromHex`) and `HslColor`.
2. `ColorMath`: `rgbToHsl` and `hslToRgb`.
3. `PaletteGenerator` with the four schemes, and `PaletteExporter` for CSS custom properties and JSON.
4. `PaletteController`: the page with a colour picker and scheme choice, and `/export`. A bad colour shows a message on the page, and a 400 from `/export`.
5. Tests: pure red, green, blue, white and black; round trips; each scheme's hues; both export formats; hex parsing; and bad colours.
6. Make it yours: show each colour's contrast against white and black text (aim for 4.5:1), or pull a palette out of an uploaded photo.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day91
mvn test
mvn spring-boot:run
```

Open <http://localhost:8080>.

## 🆘 Stuck?

- **The complement of red comes out as red**: the hue didn't wrap. 0 + 180 is fine, but 300 + 180 needs `% 360`.
- **Colours are slightly off after a round trip**: HSL uses decimals and RGB uses whole numbers, so rounding loses a little. Test round trips with values that convert exactly, or allow ±1.
- **White and black give strange hues**: with no saturation, hue doesn't mean anything. Set it to 0.
- **The download opens in the browser instead of saving**: add `Content-Disposition: attachment`.
- **`#` disappears from the colour in the URL**: `#` starts a URL fragment. It must be sent as `%23`, which Thymeleaf's `@{...}` and the form do for you.

## ✅ Checklist
- [ ] RGB to HSL and back, tested
- [ ] Four palette schemes
- [ ] CSS and JSON downloads
- [ ] Bad colours handled with a message
- [ ] Added contrast checks or palettes from a photo
- [ ] Committed code to Git

## ➡️ Next

Day 92 is a web scraper: collecting data from web pages politely and turning it into a spreadsheet.
