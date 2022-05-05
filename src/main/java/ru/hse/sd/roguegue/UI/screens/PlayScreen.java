package ru.hse.sd.roguegue.UI.screens;

import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

/**
 * Screen for game process
 */
public class PlayScreen implements Screen {
    /**
     * In the beginning of the level display all pieces of information – map, score, user etc.
     */
    @Override
    public void display() {
        Status.terminal.clear();
        Status.terminal.setCursorPosition(0, 0);
        Status.mapUI.displayMap();
        Status.userState.userUI.displayPosition();
        Status.userState.userUI.displayInformation();
        Status.inventoryUI.displayAllInventoryOnTheMap();

        for (MobState mobState : Status.gameState.getMobStates()) {
            mobState.mobUI.displayPosition(mobState.getPosition());
        }
    }

    /**
     * @return this screen if it's the middle of the game process
     * otherwise return winScreen
     */
    @Override
    public Screen change() {
        if (Status.gameStatus == GameStatus.MENU) {
            return new WinScreen();
        } else if (Status.gameStatus == GameStatus.LOSE) {
            return new LoseScreen();
        } else if (Status.gameStatus == GameStatus.INVENTORY) {
            return new InventoryScreen();
        } else {
            return this;
        }
    }
}
