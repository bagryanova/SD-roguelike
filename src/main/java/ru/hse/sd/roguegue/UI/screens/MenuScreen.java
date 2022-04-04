package ru.hse.sd.roguegue.UI.screens;

import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

public class MenuScreen implements Screen {
    @Override
    public void display() {
        Status.terminal.writeCenter("-- press [enter] to start --", 5);
    }

    @Override
    public Screen change() {
        if (Status.gameStatus == GameStatus.GAME) {
            return new PlayScreen();
        } else {
            return this;
        }
    }
}
