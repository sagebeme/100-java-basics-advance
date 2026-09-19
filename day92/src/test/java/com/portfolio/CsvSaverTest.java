package com.portfolio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvSaverTest {

    private final CsvSaver csvSaver = new CsvSaver();

    @Test
    void savesItemsAsCsvWithAHeaderRow(@TempDir Path tempDir) throws IOException {
        File output = tempDir.resolve("items.csv").toFile();
        List<ScrapedItem> items = List.of(
                new ScrapedItem("First Post", "https://example.test/first"),
                new ScrapedItem("Second Post", "https://example.test/second"));

        csvSaver.save(items, output);

        List<String> lines = Files.readAllLines(output.toPath());
        assertEquals("title,link", lines.get(0));
        assertEquals("First Post,https://example.test/first", lines.get(1));
        assertEquals("Second Post,https://example.test/second", lines.get(2));
    }

    @Test
    void quotesAndEscapesFieldsContainingCommasOrQuotes(@TempDir Path tempDir) throws IOException {
        File output = tempDir.resolve("items.csv").toFile();
        List<ScrapedItem> items = List.of(new ScrapedItem("Title, with a comma and \"quotes\"", "https://example.test/x"));

        csvSaver.save(items, output);

        List<String> lines = Files.readAllLines(output.toPath());
        assertEquals("\"Title, with a comma and \"\"quotes\"\"\",https://example.test/x", lines.get(1));
    }

    @Test
    void savingAnEmptyListStillWritesJustTheHeader(@TempDir Path tempDir) throws IOException {
        File output = tempDir.resolve("empty.csv").toFile();

        csvSaver.save(List.of(), output);

        List<String> lines = Files.readAllLines(output.toPath());
        assertEquals(1, lines.size());
        assertEquals("title,link", lines.get(0));
    }
}
