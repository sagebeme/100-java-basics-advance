package com.learning.service;

import com.learning.model.RgbColor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaletteExporterTest {

    private final PaletteExporter exporter = new PaletteExporter();
    private final List<RgbColor> palette = List.of(new RgbColor(255, 0, 0), new RgbColor(0, 255, 0));

    @Test
    void toCssProducesNumberedCustomProperties() {
        String css = exporter.toCss(palette);

        assertTrue(css.contains(":root {"));
        assertTrue(css.contains("--color-1: #FF0000;"));
        assertTrue(css.contains("--color-2: #00FF00;"));
    }

    @Test
    void toJsonProducesAnArrayOfHexStrings() {
        String json = exporter.toJson(palette);

        assertEquals("[\"#FF0000\", \"#00FF00\"]", json);
    }
}
