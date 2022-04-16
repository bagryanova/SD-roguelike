package ru.hse.sd.roguegue.UI.screens;

import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

public class LoseScreen implements Screen {
    @Override
    public void display() {
        Status.terminal.clear();
        Status.terminal.writeCenter("You lost", Constants.SENTENCES_Y);
        Status.terminal.writeCenter("Press [enter] to return to the menu", Constants.SENTENCES_Y + 1);
    }

    @Override
    public Screen change() {
        if (Status.gameStatus == GameStatus.MENU) {
            return new MenuScreen();
        } else {
            return this;
        }
    }
}
