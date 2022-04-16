package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.UI.InventoryUI;
import ru.hse.sd.roguegue.state.GameObjectState;
import ru.hse.sd.roguegue.status.InventoryItem;
import ru.hse.sd.roguegue.status.Status;

import java.util.ArrayList;

public class InventoryState {
    private ArrayList<InventoryItem> inventoryItems = new ArrayList<>();
    private ArrayList<InventoryItem> activeInventoryItems = new ArrayList<>();
    private final InventoryUI inventoryUI = new InventoryUI();
    private int currentItemIndex = 0;

    public void setInitialValues() {
        inventoryItems = new ArrayList<>();
        activeInventoryItems = new ArrayList<>();
    }

    void nextCurrentItem() {
        if (currentItemIndex + 1 < inventoryItems.size()) {
            currentItemIndex += 1;
            inventoryUI.displayCurrentLine(currentItemIndex - 1, currentItemIndex);
        }
    }

    void prevCurrentItem() {
        if (currentItemIndex - 1 >= 0) {
            currentItemIndex -= 1;
            inventoryUI.displayCurrentLine(currentItemIndex + 1, currentItemIndex);

        }
    }

    public void addInventoryItem(InventoryItem item) {
        inventoryItems.add(item);
    }

    public ArrayList<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }

    void putOnCurrentItem() {
        if (activeInventoryItems.contains(inventoryItems.get(currentItemIndex))) {
            return;
        }
        activeInventoryItems.add(inventoryItems.get(currentItemIndex));
        Status.userState.putOnInventoryItem(inventoryItems.get(currentItemIndex));
    }

    void takeOffCurrentItem() {
        if (!activeInventoryItems.contains(inventoryItems.get(currentItemIndex))) {
            return;
        }
        activeInventoryItems.remove(inventoryItems.get(currentItemIndex));
        Status.userState.takeOffInventoryItem(inventoryItems.get(currentItemIndex));
    }
}
