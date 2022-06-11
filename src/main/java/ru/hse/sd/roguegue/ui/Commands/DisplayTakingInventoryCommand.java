package ru.hse.sd.roguegue.ui.Commands;

import ru.hse.sd.roguegue.status.InventoryItem;
import ru.hse.sd.roguegue.status.Status;

/**
 * Class for outsourcing inventory item's taking
 */
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
