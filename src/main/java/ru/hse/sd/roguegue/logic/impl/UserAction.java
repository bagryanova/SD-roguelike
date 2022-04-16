package ru.hse.sd.roguegue.logic.impl;

import ru.hse.sd.roguegue.logic.GameObjectAction;
import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.map.CellType;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.state.impl.UserState;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.InventoryItem;
import ru.hse.sd.roguegue.status.Status;

public class UserAction implements GameObjectAction {
    /**
     * Updates user state according to the move made by user.
     * If user can't make this step - nothing changes.
     * If user reached EXIT cell - calls methods to finish the game.
     *
     * @param move current user's move
     */
    @Override
    public void updateState(Move move) {
        UserState state = Status.userState;
        Position position = state.getPosition();
        if (!validateStep(move)) {
            state.updatePosition(position);
            return;
        }
        switch (move) {
            case UP -> state.updatePosition(new Position(position.getX(), position.getY() - 1));
            case DOWN -> state.updatePosition(new Position(position.getX(), position.getY() + 1));
            case LEFT -> state.updatePosition(new Position(position.getX() - 1, position.getY()));
            case RIGHT -> state.updatePosition(new Position(position.getX() + 1, position.getY()));
            case INVENTORY -> {
                Status.gameStatus = GameStatus.INVENTORY;
                Status.gameState.changeScreen();
            }
            case TAKE -> {
                for (InventoryItem item : Status.inventoryMapItems) {
                    if (item.position.equals(position)) {
                        Status.gameState.getInventoryState().addInventoryItem(item);
                    }
                }
            }
//            case CONFUSE -> ; // todo
        }
        if (Status.mapState.getMap().cellArray()[Status.userState.getPosition().getY()][Status.userState.getPosition().getX()] == CellType.EXIT) {
            Status.gameStatus = GameStatus.MENU;
            Status.gameState.changeScreen();
        }
        for (MobState mob : Status.gameState.getMobStates()) {
            if (mob.getPosition() == state.getPosition()) {
                fight(mob);
            }
        }
    }

    private void fight(MobState mob) {
        // todo finish game if user died
        if (mob.getStrength() > Status.userState.getStrength()) {
            Status.userState.updateHealth(mob.getStrength() / 2);
            if (Status.userState.getLives() <= 0) {
                Status.gameStatus = GameStatus.LOSE;
                // выставила юзеру изначальные значения
                Status.userState.setInitialValues();
                Status.gameState.changeScreen();
            }
        } else {
            mob.updateLives(mob.getLives() - 1);
            Status.userState.defeatMob();
        }
    }

    private boolean validateStep(Move move) {
        var state = Status.userState;
        Position position = state.getPosition();
        CellType[][] cells = Status.mapState.getMap().cellArray();
        switch (move) {
            case UP -> {
                if (cells[position.getY() - 1][position.getX()] == CellType.OBSTACLE) {
                    return false;
                }
            }
            case DOWN -> {
                if (cells[position.getY() + 1][position.getX()] == CellType.OBSTACLE) {
                    return false;
                }
            }
            case LEFT -> {
                if (cells[position.getY()][position.getX() - 1] == CellType.OBSTACLE) {
                    return false;
                }
            }
            case RIGHT -> {
                if (cells[position.getY()][position.getX() + 1] == CellType.OBSTACLE) {
                    return false;
                }
            }
            case PUT_ON -> {
                if (!(cells[position.getY()][position.getX()] == CellType.INVENTORY && Status.gameStatus.equals(GameStatus.INVENTORY))) {
                    return false;
                }
            }
        }
        return true;
    }
}
