package ru.hse.sd.roguegue.UI.screens;

import ru.hse.sd.roguegue.Constants;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

public class WinScreen implements Screen {

    @Override
    public void display() {
        Status.terminal.clear();
        Status.terminal.writeCenter("You won the level", Constants.SENTENCES_Y);
        Status.terminal.writeCenter("Press [enter] to start the next one", Constants.SENTENCES_Y + 1);
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
