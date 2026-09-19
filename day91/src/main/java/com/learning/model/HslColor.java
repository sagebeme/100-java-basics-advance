package com.learning.model;

/** Hue in degrees [0,360), saturation and lightness as percentages [0,100]. */
public record HslColor(double h, double s, double l) {

    public HslColor {
        h = ((h % 360) + 360) % 360;
        s = Math.max(0, Math.min(100, s));
        l = Math.max(0, Math.min(100, l));
    }

    public HslColor withHue(double newHue) {
        return new HslColor(newHue, s, l);
    }

    public HslColor withLightness(double newLightness) {
        return new HslColor(h, s, newLightness);
    }
}
