package ru.hse.sd.roguegue.UI.Commands;

import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.status.Status;

/**
 * Class for outsourcing map's displaying
 */
public class DisplayMapCommand implements UICommand {
    @Override
    public void execute() {
        Status.mapUI.displayMap();
        Status.inventoryUI.displayAllInventoryOnTheMap();
        for (MobState mobState : Status.gameState.getMobStates()) {
            mobState.mobUI.displayPosition(mobState.getPosition());
        }
        Status.userState.userUI.displayPosition();
    }
}
