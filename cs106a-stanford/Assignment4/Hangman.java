
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import java.util.Random;
import java.util.Scanner;

public class Hangman {
    private static final int STARTING_GUESSES = 8;

    private String guessedWord;
    private HangmanLexicon lexicon;
    private String secretWord;

    private Scanner scanner;

    public static void main(String[] args) throws Exception {
        Hangman hangman = new Hangman();

        hangman.setUpGame();
        hangman.playGame();
    }

    private void setUpGame() throws Exception {
        populateLexicon();
        chooseSecretWord();
        initializeGuessedWord();
    }

    private void populateLexicon() {
        lexicon = new HangmanLexicon();
    }

    private void chooseSecretWord() throws Exception {
        secretWord = lexicon.getWord(new Random().nextInt(lexicon.getWordCount()));
    }

    private void playGame() {
        System.out.println("Welcome to Hangman!");

        scanner = new Scanner(System.in);
        int guessesRemaining = STARTING_GUESSES;

        while (true) {
            System.out.println(String.format("The word now looks like this: %s", guessedWord));
            System.out.println(String.format("You have %d guesses left.", guessesRemaining));
            System.out.print("Your guess: ");
            String guess = scanner.nextLine();

            if (isLegalGuess(guess)) {
                guess = guess.toUpperCase();

                if (isCorrectGuess(guess)) {
                    updateGuessedWord(guess);
                } else {
                    System.out.println(String.format("There are no %s's in the word", guess));
                    guessesRemaining--;
                }

            } else {
                System.out.println("Illegal guess. Please guess again.");
            }

            if (guessesRemaining == 0) {
                System.out.println("You're completely hung.");
                System.out.println(String.format("The word was: %s", secretWord));
                break;
            }

            if (isSecretWordGuessed()) {
                System.out.println(String.format("You guessed the word: %s", secretWord));
                System.out.println("You win");
                break;
            }
        }
    }

    private void initializeGuessedWord() {
        guessedWord = "";

        for (int i = 0; i < secretWord.length(); i++) {
            guessedWord += "-";
        }
    }

    private boolean isLegalGuess(String guess) {
        if (guess.length() < 1) {
            return false;
        }

        char ch = guess.charAt(0);
        return guess.length() == 1 && ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'));
    }

    private boolean isCorrectGuess(String guess) {
        return secretWord.contains(guess);
    }

    private void updateGuessedWord(String guess) {
        int fromIndex = 0;

        while (true) {
            int indexOfGuess = secretWord.indexOf(guess, fromIndex);
            if (indexOfGuess == -1) {
                break;
            } else {
                fromIndex = indexOfGuess + 1;
                guessedWord = guessedWord.substring(0, indexOfGuess) + guess
                        + guessedWord.substring(indexOfGuess + 1, guessedWord.length());
            }
        }
    }

    private boolean isSecretWordGuessed() {
        return !guessedWord.contains("-");
    }
}
