package com.portfolio;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Usage: java com.portfolio.WatermarkApp <input> <output> <text> [position] [opacity]
 * position is one of TOP_LEFT/TOP_RIGHT/BOTTOM_LEFT/BOTTOM_RIGHT/CENTER (default BOTTOM_RIGHT).
 */
public class WatermarkApp {

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("Usage: WatermarkApp <input> <output> <text> [position] [opacity]");
            return;
        }

        File input = new File(args[0]);
        File output = new File(args[1]);
        String text = args[2];
        WatermarkPosition position = args.length > 3 ? WatermarkPosition.valueOf(args[3]) : WatermarkPosition.BOTTOM_RIGHT;
        float opacity = args.length > 4 ? Float.parseFloat(args[4]) : 0.6f;

        WatermarkTool tool = new WatermarkTool();
        BufferedImage source = tool.loadImage(input);
        BufferedImage watermarked = tool.addTextWatermark(source, text, position, opacity);
        tool.saveImage(watermarked, output, "png");

        System.out.println("Watermarked image saved to " + output.getAbsolutePath());
    }
}
