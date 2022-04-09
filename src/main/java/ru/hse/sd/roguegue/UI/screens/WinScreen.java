package ru.hse.sd.roguegue.UI.screens;

import ru.hse.sd.roguegue.Constants;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

/**
 * Screen for displaying information about victory in the game and offer to continue.
 */
public class WinScreen implements Screen {

    /**
     * Display information about victory and offer to continue.
     */
    @Override
    public void display() {
        Status.terminal.clear();
        Status.terminal.writeCenter("You won the level", Constants.SENTENCES_Y);
        Status.terminal.writeCenter("Press [enter] to start the next one", Constants.SENTENCES_Y + 1);
    }

    /**
     * @return this screen if the player haven't pressed enter yet (to start next level)
     * otherwise return new PlayScreen (to play level)
     */
    @Override
    public Screen change() {
        if (Status.gameStatus == GameStatus.GAME) {
            return new PlayScreen();
        } else {
            return this;
        }
    }
}
