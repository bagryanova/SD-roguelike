package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.status.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for information about game process (such as score)
 */
public class GameState {
    private int score;

    private ArrayList<MobState> mobStates = new ArrayList<>();

    public InventoryState getInventoryState() {
        return inventoryState;
    }

    public void upCurrentInventoryItem() {
        this.inventoryState.nextCurrentItem();
    }

    public void downCurrentInventoryItem() {
        this.inventoryState.prevCurrentItem();
    }

    public void putOnCurrentInventoryItem() {
        this.inventoryState.prevCurrentItem();
    }

    public void takeOffCurrentInventoryItem() {
        this.inventoryState.prevCurrentItem();
    }

    private InventoryState inventoryState = new InventoryState();

    public ArrayList<MobState> getMobStates() {
        return mobStates;
    }

    public void setMobStates(ArrayList<MobState> mobStates) {
        this.mobStates = mobStates;
    }

    /**
     * @param newScore
     * update score according to the newScore and display changes on the screen
     */
    // todo как у нас вообще счет обновляется..?
    public void updateScore(int newScore) {
        score = newScore;
        Status.gameUI.displayScore();
    }

    public int getScore() {
        return score;
    }

    /**
     * Do everything to start the level in terms of UI (change screens, clear terminal and display new information)
     */
    public void startLevel() {
        Status.screen = Status.screen.change();
        Status.terminal.clear();
        Status.screen.display();
    }

    /**
     * Do everything to finish the level in terms of UI (change screens, clear terminal and display new information)
     */
    public void finishLevel() {
        Status.screen = Status.screen.change();
        Status.terminal.clear();
        Status.screen.display();
    }

    public void inventoryMenu() {
    }
}
