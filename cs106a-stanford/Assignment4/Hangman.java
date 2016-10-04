
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Hangman extends Application {
    private static final int STARTING_GUESSES = 8;

    private String currentGuessedLetter;
    private String guessedWord;
    private int guessesRemaining;
    private HangmanCanvas hangmanCanvas;
    private HangmanLexicon lexicon;
    private Scanner scanner;
    private String secretWord;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setUpGame();
        playGame(primaryStage);
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

    private void initializeGuessedWord() {
        guessedWord = "";

        for (int i = 0; i < secretWord.length(); i++) {
            guessedWord += "-";
        }
    }

    // TODO: refactor this beast
    private void playGame(Stage primaryStage) {
        System.out.println("Welcome to Hangman!");

        hangmanCanvas = new HangmanCanvas(primaryStage);
        hangmanCanvas.reset();
        scanner = new Scanner(System.in);
        guessesRemaining = STARTING_GUESSES;

        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                displayGuessedWord();

                while (true) {
                    System.out.println(String.format("The word now looks like this: %s", guessedWord));
                    System.out.println(String.format("You have %d guesses left.", guessesRemaining));
                    System.out.print("Your guess: ");
                    currentGuessedLetter = scanner.nextLine();

                    if (isLegalGuess(currentGuessedLetter)) {
                        currentGuessedLetter = currentGuessedLetter.toUpperCase();

                        if (isCorrectGuess(currentGuessedLetter)) {
                            System.out.println("That guess is correct.");
                            updateGuessedWord(currentGuessedLetter);
                            displayGuessedWord();

                        } else {
                            System.out.println(String.format("There are no %s's in the word", currentGuessedLetter));
                            guessesRemaining--;

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    hangmanCanvas.noteIncorrectGuess(currentGuessedLetter.charAt(0));
                                }
                            });
                        }

                    } else {
                        System.out.println("Illegal guess. Please guess again.");
                    }

                    if (guessesRemaining == 0) {
                        printLoseGameMessage();
                        break;
                    }

                    if (isSecretWordGuessed()) {
                        printWinGameMessage();
                        break;
                    }
                }
            }
        });
        taskThread.start();
    }

    private void displayGuessedWord() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                hangmanCanvas.displayWord(guessedWord);
            }
        });
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

    private void printLoseGameMessage() {
        System.out.println("You're completely hung.");
        System.out.println(String.format("The word was: %s", secretWord));
        System.out.println("You lose.");
    }

    private void printWinGameMessage() {
        System.out.println(String.format("You guessed the word: %s", secretWord));
        System.out.println("You win.");
    }
}
