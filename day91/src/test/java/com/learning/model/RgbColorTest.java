package com.learning.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RgbColorTest {

    @Test
    void toHexFormatsAsUppercaseSixDigitHex() {
        assertEquals("#3B6EA5", new RgbColor(59, 110, 165).toHex());
        assertEquals("#000000", new RgbColor(0, 0, 0).toHex());
        assertEquals("#FFFFFF", new RgbColor(255, 255, 255).toHex());
    }

    @Test
    void fromHexParsesWithOrWithoutTheHashPrefix() {
        assertEquals(new RgbColor(59, 110, 165), RgbColor.fromHex("#3B6EA5"));
        assertEquals(new RgbColor(59, 110, 165), RgbColor.fromHex("3B6EA5"));
        assertEquals(new RgbColor(59, 110, 165), RgbColor.fromHex("3b6ea5"));
    }

    @Test
    void fromHexRejectsTheWrongLength() {
        assertThrows(IllegalArgumentException.class, () -> RgbColor.fromHex("#FFF"));
        assertThrows(IllegalArgumentException.class, () -> RgbColor.fromHex("#TOOLONGVALUE"));
    }

    @Test
    void clampsOutOfRangeValuesToValidByteRange() {
        assertEquals(new RgbColor(0, 255, 0), new RgbColor(-10, 300, 0));
    }

    @Test
    void toHexAndFromHexRoundTrip() {
        RgbColor original = new RgbColor(200, 50, 90);
        assertEquals(original, RgbColor.fromHex(original.toHex()));
    }
}
