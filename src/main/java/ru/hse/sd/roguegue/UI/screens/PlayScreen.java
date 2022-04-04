package ru.hse.sd.roguegue.UI.screens;

import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

public class PlayScreen implements Screen {
    @Override
    public void display() {
        Status.terminal.clear();
        Status.terminal.setCursorPosition(0, 0);
        Status.gameUI.displayScore();
        Status.mapUI.displayMap();
        Status.userUI.displayPosition();
        Status.userUI.displayHealth();
    }

    @Override
    public Screen change() {
        if (Status.gameStatus == GameStatus.MENU) {
            return new WinScreen();
        } else {
            return this;
        }
    }
}
