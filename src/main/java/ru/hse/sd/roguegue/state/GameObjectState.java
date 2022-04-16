package ru.hse.sd.roguegue.state;

import ru.hse.sd.roguegue.UI.GameObjectUI;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.status.Status;

/**
 * Abstract class for the information about GameObject
 */
public abstract class GameObjectState {
    public Position position = new Position(0, 0);
    private GameObjectUI gameObjectUI = new GameObjectUI();

    /**
     * @param newPosition update position according to the newPosition and display changes on the screen
     */
    public void updatePosition(Position newPosition) {
        Status.mapUI.displayCell(position);
        position = newPosition;
        Status.inventoryUI.displayAllInventoryOnTheMap();
        for (MobState mobState : Status.gameState.getMobStates()) {
            mobState.mobUI.displayPosition(mobState.getPosition());
        }
        gameObjectUI.displayPosition(position);
    }

    public void setUI(GameObjectUI UI) {
        gameObjectUI = UI;
    }

    public Position getPosition() {
        return position;
    }
}
