package ru.hse.sd.roguegue.UI;

import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.status.InventoryItem;
import ru.hse.sd.roguegue.status.Status;

public class InventoryUI  {

    public void displayCurrentLine(int currentLine) {
        Status.terminal.write('>', Constants.MAP_START_X, currentLine + Constants.SENTENCES_Y + 1);
    }

    public void displayAllInventoryOnTheMap() {
        for (InventoryItem item : Status.inventoryMapItems) {
            Status.terminal.write(item.name.charAt(0), item.getPosition().getX() + Constants.MAP_START_X,  item.getPosition().getY() + Constants.MAP_START_Y);
        }
    }

    public void displayUsersInventoryList() {
        Status.terminal.setCursorY(Constants.MAP_START_Y);
        for (InventoryItem item : Status.gameState.getInventoryState().getInventoryItems()) {
            Status.terminal.write(item.name, Constants.MAP_START_X + 2,  Status.terminal.getCursorY() + 1);
        }
    }

    public void displayTakingInventory(InventoryItem item) {
        Status.mapUI.displayCell(item.getPosition());
    }

}
