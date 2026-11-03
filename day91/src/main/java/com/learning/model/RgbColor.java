package com.learning.model;

public record RgbColor(int r, int g, int b) {

    public RgbColor {
        r = clamp(r);
        g = clamp(g);
        b = clamp(b);
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }

    public String toHex() {
        return String.format("#%02X%02X%02X", r, g, b);
    }

    public static RgbColor fromHex(String hex) {
        String cleaned = hex.startsWith("#") ? hex.substring(1) : hex;
        // Check the characters too: Integer.parseInt would happily read "-1" or "+1" as numbers.
        if (!cleaned.matches("[0-9A-Fa-f]{6}")) {
            throw new IllegalArgumentException("Expected a 6-digit hex color like #3B6EA5, got: " + hex);
        }
        int r = Integer.parseInt(cleaned.substring(0, 2), 16);
        int g = Integer.parseInt(cleaned.substring(2, 4), 16);
        int b = Integer.parseInt(cleaned.substring(4, 6), 16);
        return new RgbColor(r, g, b);
    }
}
