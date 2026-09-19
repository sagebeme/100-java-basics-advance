package com.portfolio;

import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WatermarkTool {

    private static final int MARGIN = 10;

    public BufferedImage loadImage(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
            throw new IOException("Unsupported or unreadable image file: " + file);
        }
        return image;
    }

    public void saveImage(BufferedImage image, File output, String formatName) throws IOException {
        boolean written = ImageIO.write(image, formatName, output);
        if (!written) {
            throw new IOException("No writer found for format: " + formatName);
        }
    }

    /**
     * Returns a new image with the text drawn onto it; the source image is left untouched.
     */
    public BufferedImage addTextWatermark(BufferedImage source, String text, WatermarkPosition position, float opacity) {
        BufferedImage result = copyOf(source);
        Graphics2D g = result.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Math.max(12, source.getHeight() / 15)));

            FontMetrics metrics = g.getFontMetrics();
            int textWidth = metrics.stringWidth(text);
            int textHeight = metrics.getHeight();
            Point p = computePosition(source.getWidth(), source.getHeight(), textWidth, textHeight, position);
            int baselineY = p.y + metrics.getAscent();

            // Draw a dark shadow offset behind light text so the watermark stays legible
            // regardless of whether the underlying image is light or dark.
            g.setColor(Color.BLACK);
            g.drawString(text, p.x + 1, baselineY + 1);
            g.setColor(Color.WHITE);
            g.drawString(text, p.x, baselineY);
        } finally {
            g.dispose();
        }
        return result;
    }

    /**
     * Returns a new image with the watermark image overlaid; the source image is left untouched.
     */
    public BufferedImage addImageWatermark(BufferedImage source, BufferedImage watermark, WatermarkPosition position, float opacity) {
        BufferedImage result = copyOf(source);
        Graphics2D g = result.createGraphics();
        try {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            Point p = computePosition(source.getWidth(), source.getHeight(), watermark.getWidth(), watermark.getHeight(), position);
            g.drawImage(watermark, p.x, p.y, null);
        } finally {
            g.dispose();
        }
        return result;
    }

    /**
     * Applies a text watermark to every input file and writes the results as PNGs into
     * outputDir, creating it if it doesn't already exist.
     */
    public List<File> batchWatermark(List<File> inputFiles, File outputDir, String text, WatermarkPosition position, float opacity) throws IOException {
        Files.createDirectories(outputDir.toPath());
        List<File> outputs = new ArrayList<>();
        for (File input : inputFiles) {
            BufferedImage source = loadImage(input);
            BufferedImage watermarked = addTextWatermark(source, text, position, opacity);
            File output = new File(outputDir, stripExtension(input.getName()) + "-watermarked.png");
            saveImage(watermarked, output, "png");
            outputs.add(output);
        }
        return outputs;
    }

    private Point computePosition(int imageWidth, int imageHeight, int contentWidth, int contentHeight, WatermarkPosition position) {
        int x;
        int y;
        switch (position) {
            case TOP_LEFT -> {
                x = MARGIN;
                y = MARGIN;
            }
            case TOP_RIGHT -> {
                x = imageWidth - contentWidth - MARGIN;
                y = MARGIN;
            }
            case BOTTOM_LEFT -> {
                x = MARGIN;
                y = imageHeight - contentHeight - MARGIN;
            }
            case BOTTOM_RIGHT -> {
                x = imageWidth - contentWidth - MARGIN;
                y = imageHeight - contentHeight - MARGIN;
            }
            case CENTER -> {
                x = (imageWidth - contentWidth) / 2;
                y = (imageHeight - contentHeight) / 2;
            }
            default -> throw new IllegalArgumentException("Unknown position: " + position);
        }
        return new Point(x, y);
    }

    private BufferedImage copyOf(BufferedImage source) {
        BufferedImage copy = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = copy.createGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return copy;
    }

    private String stripExtension(String fileName) {
        int dot = fileName.lastIndexOf('.');
        return dot == -1 ? fileName : fileName.substring(0, dot);
    }
}
