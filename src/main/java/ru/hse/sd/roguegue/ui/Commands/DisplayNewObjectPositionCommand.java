package ru.hse.sd.roguegue.ui.Commands;

import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.status.Status;

/**
 * Class for outsourcing viewing of object which has changed its position
 */
public class DisplayNewObjectPositionCommand implements UICommand {
    private Position prev;

    private DisplayNewObjectPositionCommand() {}

    public DisplayNewObjectPositionCommand(Position prev) {
        this.prev = prev;
    }
    @Override
    public void execute() {
        Status.mapUI.displayCell(prev);
        Status.inventoryUI.displayAllInventoryOnTheMap();
        for (MobState mobState : Status.gameState.getMobStates()) {
            mobState.mobUI.displayPosition(mobState.getPosition());
        }
        Status.userState.userUI.displayPosition();
    }
}
