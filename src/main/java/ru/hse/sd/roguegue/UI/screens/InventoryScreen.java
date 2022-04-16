package ru.hse.sd.roguegue.UI.screens;

import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.InventoryItem;
import ru.hse.sd.roguegue.status.Status;

public class InventoryScreen implements Screen {
    @Override
    public void display() {
        Status.terminal.clear();
        Status.terminal.writeCenter("The contents of your inventory:", Constants.SENTENCES_Y);
        Status.inventoryUI.displayUsersInventoryList();
        Status.terminal.write('>', Constants.MAP_START_X, Constants.MAP_START_Y + 1);
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
