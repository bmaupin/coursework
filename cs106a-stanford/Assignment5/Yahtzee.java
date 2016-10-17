
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.IODialog;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
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
    }

    private void handleFirstDiceRoll() {
        display.printMessage(
                String.format("%s's turn! Click \"Roll Dice\" button to roll the dice.", currentPlayerName));
        display.waitForPlayerToClickRoll(currentPlayerNumber);
        diceValues = new int[N_DICE];
        for (int i = 0; i < N_DICE; i++) {
            diceValues[i] = rgen.nextInt(1, 6);
        }
        display.displayDice(diceValues);
    }

    private void handleSubsequentDiceRoll() {
        display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
        display.waitForPlayerToSelectDice();
        for (int i = 0; i < N_DICE; i++) {
            if (display.isDieSelected(i)) {
                diceValues[i] = rgen.nextInt(1, 6);
            }
        }
        display.displayDice(diceValues);
    }

    private void handleCategorySelection() {
        display.printMessage("Select a category for this roll.");
        int category;
        while (true) {
            category = display.waitForPlayerToSelectCategory();
            if (YahtzeeMagicStub.checkCategory(diceValues, category)) {
                display.printMessage("Test");
                break;
            } else {
                display.printMessage("Invalid category selected. Please select another.");
            }
        }
        // display.updateScorecard(category, currentPlayerNumber, score);
    }
}
