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
            case PUT_ON -> {
                if (Status.gameStatus.equals(GameStatus.GAME)) {
                    for (InventoryItem item : Status.inventory) {
                        if (item.position == position) {
                            state.putOnInventoryItem(item);
                            break;
                        }
                    }
                } // todo если из меню с инвентарем
            }
//            case TAKE_OFF -> ; // todo после того как добавится меню и я пойму, что вообще приходит
//            case CONFUSE -> // todo;
        }
        if (Status.mapState.getMap().cellArray()[Status.userState.getPosition().getY()][Status.userState.getPosition().getX()] == CellType.EXIT) {
            Status.gameStatus = GameStatus.MENU;
            Status.gameState.finishLevel();
        }
        for (MobState mob : Status.gameState.getMobStates()) {
            if (mob.getPosition() == state.getPosition()) {
                fight(mob);
            }
        }
    }

    private void fight(MobState mob) {
        // todo, если сдохли, то тут финишировать игру
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
                // todo gameStatus наверное не меню а инвентарь
                if (!(cells[position.getY()][position.getX()] == CellType.INVENTORY && Status.gameStatus.equals(GameStatus.MENU))) {
                    return false;
                }
            }
        }
        return true;
    }
}
