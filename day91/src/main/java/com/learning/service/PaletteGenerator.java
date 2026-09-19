package com.learning.service;

import com.learning.model.HslColor;
import com.learning.model.RgbColor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaletteGenerator {

    private static final double[] MONOCHROMATIC_LIGHTNESS_STEPS = {20, 35, 50, 65, 80};

    private final ColorMath colorMath;

    public PaletteGenerator(ColorMath colorMath) {
        this.colorMath = colorMath;
    }

    public List<RgbColor> generate(RgbColor base, PaletteScheme scheme) {
        HslColor baseHsl = colorMath.rgbToHsl(base);
        return switch (scheme) {
            case COMPLEMENTARY -> List.of(
                    base,
                    colorMath.hslToRgb(baseHsl.withHue(baseHsl.h() + 180)));
            case ANALOGOUS -> List.of(
                    colorMath.hslToRgb(baseHsl.withHue(baseHsl.h() - 30)),
                    base,
                    colorMath.hslToRgb(baseHsl.withHue(baseHsl.h() + 30)));
            case TRIADIC -> List.of(
                    base,
                    colorMath.hslToRgb(baseHsl.withHue(baseHsl.h() + 120)),
                    colorMath.hslToRgb(baseHsl.withHue(baseHsl.h() + 240)));
            case MONOCHROMATIC -> java.util.Arrays.stream(MONOCHROMATIC_LIGHTNESS_STEPS)
                    .mapToObj(lightness -> colorMath.hslToRgb(baseHsl.withLightness(lightness)))
                    .toList();
        };
    }
}
