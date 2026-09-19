package com.learning.service;

import com.learning.model.RgbColor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PaletteExporter {

    public String toCss(List<RgbColor> palette) {
        String variables = IntStream.range(0, palette.size())
                .mapToObj(i -> "  --color-%d: %s;".formatted(i + 1, palette.get(i).toHex()))
                .collect(Collectors.joining("\n"));
        return ":root {\n" + variables + "\n}\n";
    }

    public String toJson(List<RgbColor> palette) {
        String colors = palette.stream()
                .map(c -> "\"%s\"".formatted(c.toHex()))
                .collect(Collectors.joining(", "));
        return "[" + colors + "]";
    }
}
