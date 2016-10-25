
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import acm.io.IODialog;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
    private int currentCategory;
    private int currentPlayerIndex;
    private int currentTurnNumber;

    private int[] diceValues;
    private YahtzeeDisplay display;
    private int nPlayers;
    private String[] playerNames;
    private int[][] playerScores;
    private RandomGenerator rgen = new RandomGenerator();
    private Map<Integer, ArrayList<Integer>> usedCategories = new HashMap<Integer, ArrayList<Integer>>();

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
        // For whatever reason, the categories are numbered starting with 1
        playerScores = new int[nPlayers][N_CATEGORIES + 1];
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

        endGame();
    }

    private void setFirstPlayer() {
        currentPlayerIndex = 0;
    }

    private void playTurn() {
        handleFirstDiceRoll();
        handleSubsequentDiceRoll();
        handleSubsequentDiceRoll();
        handleCategorySelection();
        updateCurrentPlayerScore();
        setNextPlayer();
    }

    private void setNextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex >= nPlayers) {
            currentPlayerIndex = 0;
        }
    }

    private void handleFirstDiceRoll() {
        display.printMessage(
                String.format("%s's turn! Click \"Roll Dice\" button to roll the dice.",
                        getPlayerName(currentPlayerIndex)));
        display.waitForPlayerToClickRoll(getPlayerNumber(currentPlayerIndex));
        diceValues = new int[N_DICE];
        for (int i = 0; i < diceValues.length; i++) {
            diceValues[i] = rgen.nextInt(1, 6);
        }
        display.displayDice(diceValues);
    }

    private String getPlayerName(int playerIndex) {
        return playerNames[playerIndex];
    }

    private int getPlayerNumber(int playerIndex) {
        return playerIndex + 1;
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
            currentCategory = display.waitForPlayerToSelectCategory();

            if (!usedCategories.containsKey(currentPlayerIndex)) {
                usedCategories.put(currentPlayerIndex, new ArrayList<Integer>());
            }

            if (usedCategories.get(currentPlayerIndex).contains(currentCategory)) {
                display.printMessage("Category already used. Please select another.");
            } else {
                usedCategories.get(currentPlayerIndex).add(currentCategory);
                break;
            }
        }
    }

    private void updateCurrentPlayerScore() {
        int score = calculateCurrentCategoryScore();
        updateCurrentCategoryScore(score);
        incrementTotalScore(score);
    }

    private int calculateCurrentCategoryScore() {
        int score = 0;

        // TODO: implement YahtzeeMagicStub.checkCategory
        if (!YahtzeeMagicStub.checkCategory(diceValues, currentCategory)) {
            return score;
        }

        switch (currentCategory) {
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

    private void updateCurrentCategoryScore(int score) {
        updateAndDisplayScore(currentPlayerIndex, currentCategory, score);
    }

    private void updateAndDisplayScore(int playerIndex, int category, int score) {
        playerScores[playerIndex][category] = score;
        display.updateScorecard(category, getPlayerNumber(playerIndex), score);
    }

    private void incrementTotalScore(int score) {
        int totalScore = playerScores[currentPlayerIndex][TOTAL] + score;
        updateAndDisplayScore(currentPlayerIndex, TOTAL, totalScore);
    }

    private void endGame() {
        displayFinalScores();
        determineWinner();
    }

    private void displayFinalScores() {
        for (int playerIndex = 0; playerIndex < nPlayers; playerIndex++) {
            updateAndDisplayScore(playerIndex, UPPER_SCORE, calculateUpperScore(playerIndex));
            updateAndDisplayScore(playerIndex, UPPER_BONUS, calculateUpperBonus(playerIndex));
            updateAndDisplayScore(playerIndex, LOWER_SCORE, calculateLowerScore(playerIndex));
            updateAndDisplayScore(playerIndex, TOTAL, calculateTotalScore(playerIndex));
        }
    }

    private int calculateUpperScore(int playerIndex) {
        return playerScores[playerIndex][ONES] +
                playerScores[playerIndex][TWOS] +
                playerScores[playerIndex][THREES] +
                playerScores[playerIndex][FOURS] +
                playerScores[playerIndex][FIVES] +
                playerScores[playerIndex][SIXES];
    }

    private int calculateUpperBonus(int playerIndex) {
        if (playerScores[playerIndex][UPPER_SCORE] >= 63) {
            return 35;
        } else {
            return 0;
        }
    }

    private int calculateLowerScore(int playerIndex) {
        return playerScores[playerIndex][THREE_OF_A_KIND] +
                playerScores[playerIndex][FOUR_OF_A_KIND] +
                playerScores[playerIndex][FULL_HOUSE] +
                playerScores[playerIndex][SMALL_STRAIGHT] +
                playerScores[playerIndex][LARGE_STRAIGHT] +
                playerScores[playerIndex][YAHTZEE] +
                playerScores[playerIndex][CHANCE];
    }

    private int calculateTotalScore(int playerIndex) {
        return playerScores[playerIndex][UPPER_SCORE] +
                playerScores[playerIndex][UPPER_BONUS] +
                playerScores[playerIndex][LOWER_SCORE];
    }

    private void determineWinner() {
        int highScore = -1;
        int winnerIndex = -1;

        for (int playerIndex = 0; playerIndex < nPlayers; playerIndex++) {
            if (playerScores[playerIndex][TOTAL] > highScore) {
                highScore = playerScores[playerIndex][TOTAL];
                winnerIndex = playerIndex;
            }
        }

        display.printMessage(
                String.format(
                        "Congratulations, %s, you're the winner with a total score of %d!",
                        getPlayerName(winnerIndex),
                        highScore));
    }
}
