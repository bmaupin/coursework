
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import acm.io.IODialog;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
    private int category;
    private int currentPlayerIndex;
    private String currentPlayerName;
    private int currentPlayerNumber;
    private int currentTurnNumber;

    private int[] diceValues;
    private YahtzeeDisplay display;
    private int nPlayers;
    private String[] playerNames;
    private int[][] playerScores;
    private RandomGenerator rgen = new RandomGenerator();

    public static void main(String[] args) {
        new Yahtzee().start(args);
    }

    @Override
    public void run() {
        setUpGame();
        playGame();
    }

    private void setUpGame() {
        IODialog dialog = getDialog();
        nPlayers = dialog.readInt("Enter number of players");
        playerNames = new String[nPlayers];
        playerScores = new int[nPlayers][N_CATEGORIES];
        for (int i = 1; i <= nPlayers; i++) {
            playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
        }
        display = new YahtzeeDisplay(getGCanvas(), playerNames);
    }

    private void playGame() {
        setFirstPlayer();

        for (currentTurnNumber = 1; currentTurnNumber <= N_SCORING_CATEGORIES; currentTurnNumber++) {
            playTurn();
        }
    }

    private void setFirstPlayer() {
        currentPlayerIndex = 0;
        setCurrentPlayer();
    }

    private void setCurrentPlayer() {
        currentPlayerNumber = currentPlayerIndex + 1;
        currentPlayerName = playerNames[currentPlayerIndex];
    }

    private void playTurn() {
        handleFirstDiceRoll();
        handleSubsequentDiceRoll();
        handleSubsequentDiceRoll();
        handleCategorySelection();
        updateScore();
        setNextPlayer();
    }

    private void setNextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex >= nPlayers) {
            currentPlayerIndex = 0;
        }
        setCurrentPlayer();
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
        // TODO: don't allow the same category to be selected more than once
        // TODO: allow selection of invalid category with a score of 0
        while (true) {
            category = display.waitForPlayerToSelectCategory();
            // TODO: implement YahtzeeMagicStub.checkCategory
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
            score = getSpecificDiceValueScore(1);
            break;

        case TWOS:
            score = getSpecificDiceValueScore(2);
            break;

        case THREES:
            score = getSpecificDiceValueScore(3);
            break;

        case FOURS:
            score = getSpecificDiceValueScore(4);
            break;

        case FIVES:
            score = getSpecificDiceValueScore(5);
            break;

        case SIXES:
            score = getSpecificDiceValueScore(6);
            break;

        case THREE_OF_A_KIND:
            score = getTotalDiceValueScore();
            break;

        case FOUR_OF_A_KIND:
            score = getTotalDiceValueScore();
            break;

        case FULL_HOUSE:
            score = 25;
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
            score = getTotalDiceValueScore();
            break;

        default:
        }

        return score;
    }

    private int getSpecificDiceValueScore(int value) {
        return Arrays.stream(diceValues)
                .filter(v -> v == value)
                .sum();
    }

    private int getTotalDiceValueScore() {
        return Arrays.stream(diceValues)
                .sum();
    }

    private Map<Integer, Integer> getDiceValueCounts() {
        Map<Integer, Integer> diceValueCounts = new HashMap<Integer, Integer>();
        for (int diceValue : diceValues) {
            int diceValueCount = diceValueCounts.getOrDefault(diceValue, 0) + 1;
            diceValueCounts.put(diceValue, diceValueCount);
        }

        return diceValueCounts;
    }

    private void updateScore() {
        int score = calculateScore();
        playerScores[currentPlayerIndex][category] = score;
        display.updateScorecard(category, currentPlayerNumber, score);
    }
}
