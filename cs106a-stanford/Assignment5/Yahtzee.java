
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import acm.io.IODialog;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
    private int category;
    private String currentPlayerName;
    private int currentPlayerNumber;

    private int[] diceValues;
    private YahtzeeDisplay display;
    private int nPlayers;
    private String[] playerNames;
    private RandomGenerator rgen = new RandomGenerator();

    public static void main(String[] args) {
        new Yahtzee().start(args);
    }

    @Override
    public void run() {
        IODialog dialog = getDialog();
        nPlayers = dialog.readInt("Enter number of players");
        playerNames = new String[nPlayers];
        for (int i = 1; i <= nPlayers; i++) {
            playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
        }
        display = new YahtzeeDisplay(getGCanvas(), playerNames);
        playGame();
    }

    private void playGame() {
        playTurn();
    }

    private void playTurn() {
        // TODO: change currentPlayerNumber
        currentPlayerNumber = 1;
        currentPlayerName = playerNames[currentPlayerNumber - 1];
        handleFirstDiceRoll();
        handleSubsequentDiceRoll();
        handleSubsequentDiceRoll();
        handleCategorySelection();
        updateScore();
    }

    private void handleFirstDiceRoll() {
        display.printMessage(
                String.format("%s's turn! Click \"Roll Dice\" button to roll the dice.", currentPlayerName));
        display.waitForPlayerToClickRoll(currentPlayerNumber);
        diceValues = new int[N_DICE];
        for (int i = 0; i < diceValues.length; i++) {
            diceValues[i] = rgen.nextInt(1, 6);
        }
        display.displayDice(diceValues);
    }

    private void handleSubsequentDiceRoll() {
        display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
        display.waitForPlayerToSelectDice();
        for (int i = 0; i < diceValues.length; i++) {
            if (display.isDieSelected(i)) {
                diceValues[i] = rgen.nextInt(1, 6);
            }
        }
        display.displayDice(diceValues);
    }

    private void handleCategorySelection() {
        display.printMessage("Select a category for this roll.");
        while (true) {
            category = display.waitForPlayerToSelectCategory();
            if (YahtzeeMagicStub.checkCategory(diceValues, category)) {
                break;
            } else {
                display.printMessage("Invalid category selected. Please select another.");
            }
        }
    }

    private int calculateScore() {
        int score = 0;

        switch (category) {
        case ONES:
            score = Arrays.stream(diceValues)
                    .filter(v -> v == 1)
                    .sum();
            break;

        case TWOS:
            score = Arrays.stream(diceValues)
                    .filter(v -> v == 2)
                    .sum();
            break;

        case THREES:
            score = Arrays.stream(diceValues)
                    .filter(v -> v == 3)
                    .sum();
            break;

        case FOURS:
            score = Arrays.stream(diceValues)
                    .filter(v -> v == 4)
                    .sum();
            break;

        case FIVES:
            score = Arrays.stream(diceValues)
                    .filter(v -> v == 5)
                    .sum();
            break;

        case SIXES:
            score = Arrays.stream(diceValues)
                    .filter(v -> v == 6)
                    .sum();
            break;

        case THREE_OF_A_KIND:
            // TODO
            Map<Object, Long> diceValueCounts = Arrays.stream(diceValues)
                    .boxed()
                    .collect(Collectors.groupingBy(v -> v, Collectors.counting()));
            display.printMessage("Score: " + diceValueCounts);
            break;

        case FOUR_OF_A_KIND:
            // TODO
            break;

        case FULL_HOUSE:
            score = 35;
            break;

        case SMALL_STRAIGHT:
            score = 30;
            break;

        case LARGE_STRAIGHT:
            score = 40;
            break;

        case YAHTZEE:
            score = 50;
            break;

        case CHANCE:
            score = Arrays.stream(diceValues)
                    .sum();
            break;

        default:
        }

        return score;
    }

    private void updateScore() {
        int score = calculateScore();
        // TODO
        // display.printMessage("Score: " + score);
        // display.updateScorecard(category, currentPlayerNumber, score);
    }
}
