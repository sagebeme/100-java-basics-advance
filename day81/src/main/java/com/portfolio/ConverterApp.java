package com.portfolio;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

/**
 * Console interface for the converter (a GUI is optional per the day's brief - see Day 18-22 for
 * JavaFX if one is wanted later). Copies each result to the system clipboard when a display is
 * available; audio playback was left out since it needs real audio hardware/output to verify and
 * this environment can't confirm it actually plays anything.
 */
public class ConverterApp {

    public static void main(String[] args) {
        MorseConverter converter = new MorseConverter();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Text <-> Morse Code Converter");
            while (true) {
                System.out.println();
                System.out.println("1) Text to Morse");
                System.out.println("2) Morse to Text");
                System.out.println("3) Exit");
                System.out.print("Choose an option: ");
                if (!scanner.hasNextLine()) {
                    break; // input ended (Ctrl+D, or a piped file ran out)
                }
                String choice = scanner.nextLine().trim();

                if (choice.equals("3")) {
                    break;
                }
                if (!choice.equals("1") && !choice.equals("2")) {
                    System.out.println("Unrecognized option.");
                    continue;
                }

                System.out.print("Enter input: ");
                if (!scanner.hasNextLine()) {
                    break;
                }
                String input = scanner.nextLine();

                String result = choice.equals("1") ? converter.textToMorse(input) : converter.morseToText(input);

                System.out.println("Result: " + result);
                copyToClipboard(result);
            }
        }
    }

    private static void copyToClipboard(String text) {
        try {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
            System.out.println("(copied to clipboard)");
        } catch (HeadlessException e) {
            // No display available (e.g. running in a terminal-only or CI environment) - skip silently.
        }
    }
}
