package ru.hse.sd.roguegue.UI.screens;

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
        Status.terminal.writeCenter("-- press [enter] to start --", 5);
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
