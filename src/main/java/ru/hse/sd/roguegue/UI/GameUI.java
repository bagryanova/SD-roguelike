package ru.hse.sd.roguegue.UI;

import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.status.Status;

/**
 * Class for displaying the information about the game
 */
public class GameUI {
    public void displayScore() {
        int score = Status.gameState.getScore();
        Status.terminal.writeCenter("SCORE: " + score, Constants.SCORE_Y);
    }
}
