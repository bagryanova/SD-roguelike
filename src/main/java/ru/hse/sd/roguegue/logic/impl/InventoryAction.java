package ru.hse.sd.roguegue.logic.impl;

import ru.hse.sd.roguegue.logic.GameObjectAction;
import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.state.impl.GameState;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

public class InventoryAction implements GameObjectAction {
    /**
     * Updates inventory state according to the action made by user.
     * User can change current item, put on or take off them.
     *
     * @param move current user's move
     */
    @Override
    public void updateState(Move move) {
        GameState gameState = Status.gameState;
        switch (move) {
            case UP -> gameState.upCurrentInventoryItem();
            case DOWN -> gameState.downCurrentInventoryItem();
            case PUT_ON -> gameState.putOnCurrentInventoryItem();
            case TAKE_OFF -> gameState.takeOffCurrentInventoryItem();
            case ENTER -> {
                Status.gameStatus = GameStatus.GAME;
                gameState.changeScreen();
            }
        }



    }
}
