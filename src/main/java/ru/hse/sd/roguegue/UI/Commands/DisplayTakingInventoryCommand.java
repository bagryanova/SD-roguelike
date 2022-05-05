package ru.hse.sd.roguegue.UI.Commands;

import ru.hse.sd.roguegue.status.InventoryItem;
import ru.hse.sd.roguegue.status.Status;

public class DisplayTakingInventoryCommand implements UICommand {
    private InventoryItem item;

    private DisplayTakingInventoryCommand() {}

    public DisplayTakingInventoryCommand(InventoryItem item) {
        this.item = item;
    }

    @Override
    public void execute() {
        Status.inventoryUI.displayTakingInventory(item);
    }
}
