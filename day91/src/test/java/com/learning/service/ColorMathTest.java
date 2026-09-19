package com.learning.service;

import com.learning.model.HslColor;
import com.learning.model.RgbColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorMathTest {

    private final ColorMath colorMath = new ColorMath();

    @Test
    void pureRedConvertsToTheExpectedHsl() {
        HslColor hsl = colorMath.rgbToHsl(new RgbColor(255, 0, 0));
        assertEquals(0, hsl.h(), 0.01);
        assertEquals(100, hsl.s(), 0.01);
        assertEquals(50, hsl.l(), 0.01);
    }

    @Test
    void pureGreenConvertsToTheExpectedHsl() {
        HslColor hsl = colorMath.rgbToHsl(new RgbColor(0, 255, 0));
        assertEquals(120, hsl.h(), 0.01);
        assertEquals(100, hsl.s(), 0.01);
        assertEquals(50, hsl.l(), 0.01);
    }

    @Test
    void pureBlueConvertsToTheExpectedHsl() {
        HslColor hsl = colorMath.rgbToHsl(new RgbColor(0, 0, 255));
        assertEquals(240, hsl.h(), 0.01);
        assertEquals(100, hsl.s(), 0.01);
        assertEquals(50, hsl.l(), 0.01);
    }

    @Test
    void whiteHasZeroSaturationAndFullLightness() {
        HslColor hsl = colorMath.rgbToHsl(new RgbColor(255, 255, 255));
        assertEquals(0, hsl.s(), 0.01);
        assertEquals(100, hsl.l(), 0.01);
    }

    @Test
    void blackHasZeroSaturationAndZeroLightness() {
        HslColor hsl = colorMath.rgbToHsl(new RgbColor(0, 0, 0));
        assertEquals(0, hsl.s(), 0.01);
        assertEquals(0, hsl.l(), 0.01);
    }

    @Test
    void hslToRgbReversesRgbToHslForRoundNumbers() {
        RgbColor original = new RgbColor(59, 110, 165);
        HslColor hsl = colorMath.rgbToHsl(original);
        RgbColor roundTripped = colorMath.hslToRgb(hsl);

        // Allow +/-1 per channel for floating point rounding.
        assertTrue(Math.abs(original.r() - roundTripped.r()) <= 1);
        assertTrue(Math.abs(original.g() - roundTripped.g()) <= 1);
        assertTrue(Math.abs(original.b() - roundTripped.b()) <= 1);
    }

    @Test
    void hslToRgbProducesPureRedAtHueZeroFullSaturationHalfLightness() {
        RgbColor rgb = colorMath.hslToRgb(new HslColor(0, 100, 50));
        assertEquals(new RgbColor(255, 0, 0), rgb);
    }
}
