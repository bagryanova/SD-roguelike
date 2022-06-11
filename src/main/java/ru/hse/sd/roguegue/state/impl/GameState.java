package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.ui.Commands.ChangeScreenCommand;

import java.util.ArrayList;

/**
 * Class for information about game process (such as score)
 */
public class GameState {
    private ArrayList<MobState> mobStates = new ArrayList<>();
    private final InventoryState inventoryState = new InventoryState();

    public InventoryState getInventoryState() {
        return inventoryState;
    }

    public void upCurrentInventoryItem() {
        this.inventoryState.prevCurrentItem();
    }

    public void downCurrentInventoryItem() {
        this.inventoryState.nextCurrentItem();
    }

    public void putOnCurrentInventoryItem() {
        this.inventoryState.putOnCurrentItem();
    }

    public void takeOffCurrentInventoryItem() {
        this.inventoryState.takeOffCurrentItem();
    }

    public ArrayList<MobState> getMobStates() {
        return mobStates;
    }

    public void setMobStates(ArrayList<MobState> mobStates) {
        this.mobStates = mobStates;
    }

    public void changeScreen() {
        new ChangeScreenCommand().execute();
    }

}
