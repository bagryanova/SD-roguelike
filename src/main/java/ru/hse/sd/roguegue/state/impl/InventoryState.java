package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.ui.Commands.DisplayCurrentLineCommand;
import ru.hse.sd.roguegue.status.InventoryItem;
import ru.hse.sd.roguegue.status.Status;

import java.util.ArrayList;

public class InventoryState {
    private ArrayList<InventoryItem> inventoryItems = new ArrayList<>();
    private ArrayList<InventoryItem> activeInventoryItems = new ArrayList<>();
    private int currentItemIndex = 0;

    /**
     * Initialize inventory.
     */
    public void setInitialValues() {
        inventoryItems = new ArrayList<>();
        activeInventoryItems = new ArrayList<>();
    }

    /**
     * Move current item
     */
    public void nextCurrentItem() {
        if (currentItemIndex + 1 < inventoryItems.size()) {
            currentItemIndex += 1;
            new DisplayCurrentLineCommand(currentItemIndex - 1, currentItemIndex).execute();
        }
    }

    /**
     * Move current item
     */
    public void prevCurrentItem() {
        if (currentItemIndex - 1 >= 0) {
            currentItemIndex -= 1;
            new DisplayCurrentLineCommand(currentItemIndex + 1, currentItemIndex).execute();

        }
    }

    /**
     * Add new inventory item
     */
    public void addInventoryItem(InventoryItem item) {
        inventoryItems.add(item);
    }

    /**
     * Get all inventory item
     */
    public ArrayList<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }

    /**
     * Put on current inventory item
     */
    public void putOnCurrentItem() {
        if (activeInventoryItems.contains(inventoryItems.get(currentItemIndex))) {
            return;
        }
        activeInventoryItems.add(inventoryItems.get(currentItemIndex));
        Status.userState.putOnInventoryItem(inventoryItems.get(currentItemIndex));
    }

    /**
     * Take off current inventory item
     */
    public void takeOffCurrentItem() {
        if (!activeInventoryItems.contains(inventoryItems.get(currentItemIndex))) {
            return;
        }
        activeInventoryItems.remove(inventoryItems.get(currentItemIndex));
        Status.userState.takeOffInventoryItem(inventoryItems.get(currentItemIndex));
    }
}
