package ru.hse.sd.roguegue.ui;

import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.status.InventoryItem;
import ru.hse.sd.roguegue.status.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for displaying inventory on the map. And also for displaying user's inventory in list.
 */
public class InventoryUI  {

    /**
     * Changes cursor's position from prevLine to currentLine
     */
    public void displayCurrentLine(int prevLine, int currentLine) {
        Status.terminal.write(' ', Constants.MAP_START_X, prevLine + Constants.MAP_START_Y + 1);
        Status.terminal.write('>', Constants.MAP_START_X, currentLine + Constants.MAP_START_Y + 1);
    }

    public void displayAllInventoryOnTheMap() {
        for (InventoryItem item : Status.inventoryMapItems) {
            Status.terminal.write(item.name.charAt(0), item.getPosition().getX() + Constants.MAP_START_X,  item.getPosition().getY() + Constants.MAP_START_Y);
        }
        List<InventoryItem> invCopy = new ArrayList<>(Status.inventoryMapItems);
        for (InventoryItem item : invCopy) {
            if (!item.present) {
                Status.inventoryMapItems.remove(item);
            }
        }
    }

    public void displayUsersInventoryList() {
        Status.terminal.setCursorY(Constants.MAP_START_Y);
        for (InventoryItem item : Status.gameState.getInventoryState().getInventoryItems()) {
            Status.terminal.write(item.name + " strength: " + item.plusAttack + " health: " + item.plusHealth,
                    Constants.MAP_START_X + 2,  Status.terminal.getCursorY() + 1);
        }
    }

    /**
     * Displays how user takes inventory from the map (inventory item disappears from the map)
     */
    public void displayTakingInventory(InventoryItem item) {
        Status.mapUI.displayCell(item.getPosition());
    }

}
