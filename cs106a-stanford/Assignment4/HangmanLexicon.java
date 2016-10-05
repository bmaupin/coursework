
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HangmanLexicon {
    private static final String LEXICON_SOURCE_FILE = "HangmanLexicon.txt";

    private List<String> wordList;

    public HangmanLexicon() {
        wordList = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader(LEXICON_SOURCE_FILE))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                wordList.add(line.trim());
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    /** Returns the number of words in the lexicon. */
    public int getWordCount() {
        return wordList.size();
    }

    /**
     * Returns the word at the specified index.
     */
    public String getWord(int index) {
        return wordList.get(index);
    };
}
