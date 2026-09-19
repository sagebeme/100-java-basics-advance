package com.learning.service;

import com.learning.model.HslColor;
import com.learning.model.RgbColor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaletteGeneratorTest {

    private final ColorMath colorMath = new ColorMath();
    private final PaletteGenerator generator = new PaletteGenerator(colorMath);
    private final RgbColor red = new RgbColor(255, 0, 0); // hue 0

    @Test
    void complementaryReturnsTheBaseAndItsOppositeHue() {
        List<RgbColor> palette = generator.generate(red, PaletteScheme.COMPLEMENTARY);

        assertEquals(2, palette.size());
        assertEquals(red, palette.get(0));

        HslColor secondHsl = colorMath.rgbToHsl(palette.get(1));
        assertEquals(180, secondHsl.h(), 0.5);
    }

    @Test
    void analogousReturnsThreeColorsThirtyDegreesApartCenteredOnTheBase() {
        List<RgbColor> palette = generator.generate(red, PaletteScheme.ANALOGOUS);

        assertEquals(3, palette.size());
        assertEquals(red, palette.get(1));

        assertEquals(330, colorMath.rgbToHsl(palette.get(0)).h(), 0.5); // 0 - 30 wraps to 330
        assertEquals(30, colorMath.rgbToHsl(palette.get(2)).h(), 0.5);
    }

    @Test
    void triadicReturnsThreeColorsOneHundredTwentyDegreesApart() {
        List<RgbColor> palette = generator.generate(red, PaletteScheme.TRIADIC);

        assertEquals(3, palette.size());
        assertEquals(red, palette.get(0));
        assertEquals(120, colorMath.rgbToHsl(palette.get(1)).h(), 0.5);
        assertEquals(240, colorMath.rgbToHsl(palette.get(2)).h(), 0.5);
    }

    @Test
    void monochromaticReturnsFiveColorsWithTheSameHueAndSaturationButDifferentLightness() {
        List<RgbColor> palette = generator.generate(red, PaletteScheme.MONOCHROMATIC);

        assertEquals(5, palette.size());
        List<Double> lightnesses = palette.stream().map(c -> colorMath.rgbToHsl(c).l()).toList();

        for (RgbColor color : palette) {
            HslColor hsl = colorMath.rgbToHsl(color);
            assertEquals(0, hsl.h(), 0.5);
            assertEquals(100, hsl.s(), 0.5);
        }

        // Lightness should be strictly increasing across the five swatches.
        for (int i = 1; i < lightnesses.size(); i++) {
            assertTrue(lightnesses.get(i) > lightnesses.get(i - 1));
        }
    }
}
