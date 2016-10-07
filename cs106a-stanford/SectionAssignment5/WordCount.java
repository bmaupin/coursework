import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordCount {
    private int charCount = 0;
    private int lineCount = 0;
    private int wordCount = 0;

    private String fileName;
    private Scanner scanner;

    public static void main(String[] args) {
        WordCount wordCount = new WordCount();
        wordCount.run();
    }

    private void run() {
        getFileName();
        calculateCounts();
        displayCounts();
    }

    private void getFileName() {
        scanner = new Scanner(System.in);
        System.out.print("File: ");
        fileName = scanner.nextLine();
    }

    private void calculateCounts() {
        calculateLineCount();
    }

    private void calculateLineCount() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                updateWordCount(line);
                updateCharCount(line);

                System.out.println(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    private void updateWordCount(String line) {
        wordCount += line.split("[^\\w]+").length;
    }

    private void updateCharCount(String line) {
        char[] lineChars = line.toCharArray();

        for (int i = 1; i < lineChars.length; i++) {
            charCount++;
        }
    }

    private void displayCounts() {
        System.out.printf("Lines: %d\n", lineCount);
        System.out.printf("Words: %d\n", wordCount);
        System.out.printf("Chars: %d\n", charCount);
    }
}
