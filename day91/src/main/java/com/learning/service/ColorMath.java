package com.learning.service;

import com.learning.model.HslColor;
import com.learning.model.RgbColor;
import org.springframework.stereotype.Component;

@Component
public class ColorMath {

    public HslColor rgbToHsl(RgbColor rgb) {
        double r = rgb.r() / 255.0;
        double g = rgb.g() / 255.0;
        double b = rgb.b() / 255.0;

        double max = Math.max(r, Math.max(g, b));
        double min = Math.min(r, Math.min(g, b));
        double l = (max + min) / 2.0;

        double h;
        double s;
        if (max == min) {
            h = 0;
            s = 0;
        } else {
            double d = max - min;
            s = l > 0.5 ? d / (2 - max - min) : d / (max + min);
            if (max == r) {
                h = (g - b) / d + (g < b ? 6 : 0);
            } else if (max == g) {
                h = (b - r) / d + 2;
            } else {
                h = (r - g) / d + 4;
            }
            h /= 6;
        }

        return new HslColor(h * 360, s * 100, l * 100);
    }

    public RgbColor hslToRgb(HslColor hsl) {
        double h = hsl.h() / 360.0;
        double s = hsl.s() / 100.0;
        double l = hsl.l() / 100.0;

        double r;
        double g;
        double b;

        if (s == 0) {
            r = g = b = l;
        } else {
            double q = l < 0.5 ? l * (1 + s) : l + s - l * s;
            double p = 2 * l - q;
            r = hueToRgb(p, q, h + 1.0 / 3);
            g = hueToRgb(p, q, h);
            b = hueToRgb(p, q, h - 1.0 / 3);
        }

        return new RgbColor(
                (int) Math.round(r * 255),
                (int) Math.round(g * 255),
                (int) Math.round(b * 255));
    }

    private double hueToRgb(double p, double q, double t) {
        if (t < 0) t += 1;
        if (t > 1) t -= 1;
        if (t < 1.0 / 6) return p + (q - p) * 6 * t;
        if (t < 1.0 / 2) return q;
        if (t < 2.0 / 3) return p + (q - p) * (2.0 / 3 - t) * 6;
        return p;
    }
}
