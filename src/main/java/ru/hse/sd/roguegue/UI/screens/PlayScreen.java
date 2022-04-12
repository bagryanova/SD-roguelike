package ru.hse.sd.roguegue.UI.screens;

import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

/**
 * Screen for game process
 */
public class PlayScreen implements Screen {
    /**
     * In the beginning of the level display all pieces of information – map, score, user etc.
     */
    @Override
    public void display() {
        Status.terminal.clear();
        Status.terminal.setCursorPosition(0, 0);
        Status.gameUI.displayScore();
        Status.mapUI.displayMap();
        Status.userUI.displayPosition();
        Status.userUI.displayHealth();
    }

    /**
     * @return this screen if it's the middle of the game process
     * otherwise return winScreen
     */
    @Override
    public Screen change() {
        if (Status.gameStatus == GameStatus.MENU) {
            return new WinScreen();
        } else {
            return this;
        }
    }
}
