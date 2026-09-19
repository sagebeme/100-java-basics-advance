package com.portfolio;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class CsvSaver {

    public void save(List<ScrapedItem> items, java.io.File outputFile) throws IOException {
        try (Writer writer = Files.newBufferedWriter(outputFile.toPath(), StandardCharsets.UTF_8)) {
            writer.write("title,link");
            writer.write(System.lineSeparator());
            for (ScrapedItem item : items) {
                writer.write(escape(item.title()) + "," + escape(item.link()));
                writer.write(System.lineSeparator());
            }
        }
    }

    private String escape(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
