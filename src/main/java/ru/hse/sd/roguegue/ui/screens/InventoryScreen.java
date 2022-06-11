package ru.hse.sd.roguegue.ui.screens;

import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

/**
 * Screen for displaying user's inventory items list
 */
public class InventoryScreen implements Screen {

    /**
     * Shows content of inventory as a list with cursor
     */
    @Override
    public void display() {
        Status.terminal.clear();
        Status.terminal.writeCenter("The contents of your inventory:", Constants.SENTENCES_Y - 1);
        Status.userState.userUI.displayInformation();
        Status.inventoryUI.displayUsersInventoryList();
        Status.terminal.write('>', Constants.MAP_START_X, Constants.MAP_START_Y + 1);
    }

    /**
     * @return PlayScreen if user wants return to the game process
     * otherwise return this screen
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
