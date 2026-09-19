package com.portfolio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WatermarkToolTest {

    private final WatermarkTool tool = new WatermarkTool();

    private BufferedImage whiteCanvas(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.dispose();
        return image;
    }

    @Test
    void addingATextWatermarkDoesNotMutateTheSourceImage() {
        BufferedImage source = whiteCanvas(200, 100);
        int originalPixel = source.getRGB(5, 5);

        tool.addTextWatermark(source, "COPYRIGHT", WatermarkPosition.BOTTOM_RIGHT, 1.0f);

        assertEquals(originalPixel, source.getRGB(5, 5));
    }

    @Test
    void aBottomRightTextWatermarkChangesPixelsNearTheBottomRightButNotTopLeft() {
        BufferedImage source = whiteCanvas(300, 150);
        BufferedImage watermarked = tool.addTextWatermark(source, "SAMPLE", WatermarkPosition.BOTTOM_RIGHT, 1.0f);

        // Top-left corner is far from the watermark and should still be plain white.
        assertEquals(Color.WHITE.getRGB(), watermarked.getRGB(2, 2));

        // Somewhere in the bottom-right region should no longer be pure white.
        boolean foundNonWhitePixel = false;
        for (int x = 200; x < 290 && !foundNonWhitePixel; x++) {
            for (int y = 110; y < 140 && !foundNonWhitePixel; y++) {
                if (watermarked.getRGB(x, y) != Color.WHITE.getRGB()) {
                    foundNonWhitePixel = true;
                }
            }
        }
        assertTrue(foundNonWhitePixel, "Expected the bottom-right region to contain watermark pixels");
    }

    @Test
    void addingAnImageWatermarkOverlaysItAtTheRequestedCorner() {
        BufferedImage source = whiteCanvas(200, 200);
        BufferedImage redSquare = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = redSquare.createGraphics();
        g.setColor(Color.RED);
        g.fillRect(0, 0, 20, 20);
        g.dispose();

        BufferedImage result = tool.addImageWatermark(source, redSquare, WatermarkPosition.TOP_LEFT, 1.0f);

        // Inside the pasted square (with a 10px margin) should now be red.
        Color pixelInsideWatermark = new Color(result.getRGB(15, 15), true);
        assertEquals(255, pixelInsideWatermark.getRed());
        assertEquals(0, pixelInsideWatermark.getGreen());

        // Far from the watermark should remain white.
        assertEquals(Color.WHITE.getRGB(), result.getRGB(150, 150));
    }

    @Test
    void centerPositionPlacesTheWatermarkAroundTheMiddleOfTheImage() {
        BufferedImage source = whiteCanvas(200, 200);
        BufferedImage redSquare = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = redSquare.createGraphics();
        g.setColor(Color.RED);
        g.fillRect(0, 0, 20, 20);
        g.dispose();

        BufferedImage result = tool.addImageWatermark(source, redSquare, WatermarkPosition.CENTER, 1.0f);

        Color centerPixel = new Color(result.getRGB(100, 100), true);
        assertEquals(255, centerPixel.getRed());
        assertEquals(0, centerPixel.getGreen());
    }

    @Test
    void savingAndReloadingAnImagePreservesItsExactPixelData(@TempDir Path tempDir) throws IOException {
        BufferedImage source = whiteCanvas(50, 40);
        Graphics2D g = source.createGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(10, 10, 15, 15);
        g.dispose();

        File output = tempDir.resolve("test-image.png").toFile();
        tool.saveImage(source, output, "png");

        BufferedImage reloaded = tool.loadImage(output);
        assertEquals(source.getWidth(), reloaded.getWidth());
        assertEquals(source.getHeight(), reloaded.getHeight());
        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {
                assertEquals(source.getRGB(x, y), reloaded.getRGB(x, y), "Mismatch at (" + x + "," + y + ")");
            }
        }
    }

    @Test
    void loadingANonexistentFileThrows(@TempDir Path tempDir) {
        File missing = tempDir.resolve("does-not-exist.png").toFile();
        assertThrows(IOException.class, () -> tool.loadImage(missing));
    }

    @Test
    void batchWatermarkProducesAWatermarkedOutputFileForEveryInput(@TempDir Path tempDir) throws IOException {
        File input1 = tempDir.resolve("photo1.png").toFile();
        File input2 = tempDir.resolve("photo2.png").toFile();
        ImageIO.write(whiteCanvas(100, 100), "png", input1);
        ImageIO.write(whiteCanvas(120, 80), "png", input2);

        File outputDir = tempDir.resolve("out").toFile();
        List<File> outputs = tool.batchWatermark(List.of(input1, input2), outputDir, "BATCH", WatermarkPosition.BOTTOM_RIGHT, 1.0f);

        assertEquals(2, outputs.size());
        for (File output : outputs) {
            assertTrue(output.exists());
            BufferedImage watermarked = ImageIO.read(output);
            assertNotNull(watermarked);

            boolean hasNonWhitePixel = false;
            for (int x = 0; x < watermarked.getWidth() && !hasNonWhitePixel; x++) {
                for (int y = 0; y < watermarked.getHeight() && !hasNonWhitePixel; y++) {
                    if ((watermarked.getRGB(x, y) | 0xFF000000) != 0xFFFFFFFF) {
                        hasNonWhitePixel = true;
                    }
                }
            }
            assertTrue(hasNonWhitePixel, "Expected " + output + " to contain watermark pixels");
        }
    }
}
