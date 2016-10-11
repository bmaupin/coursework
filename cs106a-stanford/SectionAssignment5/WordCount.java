import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordCount {
    private int charCount = 0;
    private int lineCount = 0;
    private int wordCount = 0;

    private String inputFilename;
    private BufferedReader reader;
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
        inputFilename = scanner.nextLine();
    }

    private void calculateCounts() {
        try {
            reader = new BufferedReader(new FileReader(inputFilename));
            String line = null;
            while ((line = reader.readLine()) != null) {
                updateLineCount();
                updateWordCount(line);
                updateCharCount(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateLineCount() {
        lineCount++;
    }

    private void updateWordCount(String line) {
        wordCount += line.split("[^\\w]+").length;
    }

    private void updateCharCount(String line) {
        charCount += line.length();
    }

    private void displayCounts() {
        System.out.printf("Lines: %d\n", lineCount);
        System.out.printf("Words: %d\n", wordCount);
        System.out.printf("Chars: %d\n", charCount);
    }
}
