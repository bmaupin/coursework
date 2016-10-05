
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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        setUpGame(primaryStage);
        playGame();
    }

    private void setUpGame(Stage primaryStage) {
        populateLexicon();
        chooseSecretWord();
        initializeGuessedWord();

        hangmanCanvas = new HangmanCanvas(primaryStage);
        hangmanCanvas.reset();
        scanner = new Scanner(System.in);
        guessesRemaining = STARTING_GUESSES;

        displayWelcomeMessage();
    }

    private void populateLexicon() {
        lexicon = new HangmanLexicon();
    }

    private void chooseSecretWord() {
        secretWord = lexicon.getWord(new Random().nextInt(lexicon.getWordCount()));
    }

    private void initializeGuessedWord() {
        guessedWord = "";

        for (int i = 0; i < secretWord.length(); i++) {
            guessedWord += "-";
        }
    }

    private void displayWelcomeMessage() {
        System.out.println("Welcome to Hangman!");
    }

    private void playGame() {
        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    displayGameProgress();
                    getNextGuess();

                    if (isGuessLegal()) {
                        convertGuessToUpper();

                        if (isGuessCorrect()) {
                            handleCorrectGuess();
                        } else {
                            handleIncorrectGuess();
                        }

                    } else {
                        handleIllegalGuess();
                        continue;
                    }

                    if (isSecretWordGuessed()) {
                        winGame();
                        break;
                    }

                    if (noGuessesRemaining()) {
                        loseGame();
                        break;
                    }
                }
            }
        });
        taskThread.start();
    }

    private void displayGameProgress() {
        System.out.println(String.format("The word now looks like this: %s", guessedWord));
        System.out.println(String.format("You have %d guesses left.", guessesRemaining));
        updateGuessedWordGraphics();
    }

    private void updateGuessedWordGraphics() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                hangmanCanvas.displayWord(guessedWord);
            }
        });
    }

    private void getNextGuess() {
        System.out.print("Your guess: ");
        currentGuessedLetter = scanner.nextLine();
    }

    private boolean isGuessLegal() {
        if (currentGuessedLetter.length() < 1) {
            return false;
        }

        char ch = currentGuessedLetter.charAt(0);
        return currentGuessedLetter.length() == 1 && ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'));
    }

    private void convertGuessToUpper() {
        currentGuessedLetter = currentGuessedLetter.toUpperCase();
    }

    private boolean isGuessCorrect() {
        return secretWord.contains(currentGuessedLetter);
    }

    private void handleCorrectGuess() {
        System.out.println("That guess is correct.");
        updateGuessedWord(currentGuessedLetter);
        updateGuessedWordGraphics();
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

    private void handleIncorrectGuess() {
        System.out.println(String.format("There are no %s's in the word", currentGuessedLetter));
        updateIncorrectGuessGraphics();
        guessesRemaining--;
    }

    private void updateIncorrectGuessGraphics() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                hangmanCanvas.noteIncorrectGuess(currentGuessedLetter.charAt(0));
            }
        });
    }

    private void handleIllegalGuess() {
        System.out.println("Illegal guess. Please guess again.");
    }

    private boolean isSecretWordGuessed() {
        return !guessedWord.contains("-");
    }

    private void winGame() {
        updateGuessedWordGraphics();
        printWinGameMessage();
    }

    private void printWinGameMessage() {
        System.out.println(String.format("You guessed the word: %s", secretWord));
        System.out.println("You win.");
    }

    private boolean noGuessesRemaining() {
        return guessesRemaining == 0;
    }

    private void loseGame() {
        printLoseGameMessage();
    }

    private void printLoseGameMessage() {
        System.out.println("You're completely hung.");
        System.out.println(String.format("The word was: %s", secretWord));
        System.out.println("You lose.");
    }
}
