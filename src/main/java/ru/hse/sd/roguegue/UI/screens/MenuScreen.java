package ru.hse.sd.roguegue.UI.screens;

import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

/**
 * The first screen which user sees
 */
public class MenuScreen implements Screen {
    /**
     * Display hello sentence and offer to start the game
     */
    @Override
    public void display() {
        Status.terminal.clear();
        Status.terminal.writeCenter("-- press [enter] to start --", Constants.SENTENCES_Y);
    }

    /**
     * @return this screen if user hasn't pressed start
     * otherwise return PlayScreen (to start the level)
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
