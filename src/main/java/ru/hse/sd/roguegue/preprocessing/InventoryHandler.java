package ru.hse.sd.roguegue.preprocessing;

import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.logic.impl.InventoryAction;
import ru.hse.sd.roguegue.logic.impl.UserAction;

import java.awt.event.KeyEvent;

public class InventoryHandler {
    private final InventoryAction inventoryAction = new InventoryAction();

    private boolean validateAction(KeyEvent event) {
        return event.getKeyCode() == KeyEvent.VK_Q || event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN || event.getKeyCode() == KeyEvent.VK_BACK_SPACE;
    }

    /**
     * Depending on the user's action update inventory state
     */
    public void setInventoryAction(KeyEvent event) {
        if (!validateAction(event)) {
            return;
        }
        switch (event.getKeyCode()) {
            case KeyEvent.VK_UP -> inventoryAction.updateState(Move.UP);
            case KeyEvent.VK_DOWN -> inventoryAction.updateState(Move.DOWN);
            case KeyEvent.VK_ENTER -> inventoryAction.updateState(Move.PUT_ON);
            case KeyEvent.VK_BACK_SPACE -> inventoryAction.updateState(Move.TAKE_OFF);
            case KeyEvent.VK_Q -> inventoryAction.updateState(Move.ENTER);
        }
    }
}
