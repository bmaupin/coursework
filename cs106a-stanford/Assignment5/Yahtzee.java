
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.IODialog;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
    private int nPlayers;
    private String[] playerNames;
    private YahtzeeDisplay display;
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
        int currentPlayerNumber = 1;
        String currentPlayerName = playerNames[currentPlayerNumber - 1];
        display.printMessage(
                String.format("%s's turn! Click \"Roll Dice\" button to roll the dice.", currentPlayerName));
        display.waitForPlayerToClickRoll(currentPlayerNumber);
        rollDice();
        display.waitForPlayerToSelectDice();
    }

    private void rollDice() {
        int[] diceValues = new int[N_DICE];
        for (int i = 0; i < N_DICE; i++) {
            diceValues[i] = rgen.nextInt(1, 6);
        }
        display.displayDice(diceValues);
    }
}
