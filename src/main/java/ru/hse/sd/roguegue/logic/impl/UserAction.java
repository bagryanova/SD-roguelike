package ru.hse.sd.roguegue.logic.impl;

import ru.hse.sd.roguegue.logic.GameObjectAction;
import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.map.CellType;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.impl.UserState;
import ru.hse.sd.roguegue.status.GameStatus;
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
        if (Status.mapState.getMap().cellArray()[position.getX()][position.getY() + 1] == CellType.EXIT) {
            Status.gameStatus = GameStatus.MENU;
            Status.gameState.finishLevel();
        }
        if (!validateStep(move)) {
            state.updatePosition(position);
        }
        switch (move) {
            case UP -> state.updatePosition(new Position(position.getX(), position.getY() + 1));
            case DOWN -> state.updatePosition(new Position(position.getX(), position.getY() - 1));
            case LEFT -> state.updatePosition(new Position(position.getX() - 1, position.getY()));
            case RIGHT -> state.updatePosition(new Position(position.getX() + 1, position.getY()));
        }
    }

    private boolean validateStep(Move move) {
        var state = Status.userState;
        Position position = state.getPosition();
        CellType[][] cells = Status.mapState.getMap().cellArray();
        switch (move) {
            case UP -> {
                if (cells[position.getX()][position.getY() + 1] == CellType.OBSTACLE) {
                    return false;
                }
            }
            case DOWN -> {
                if (cells[position.getX()][position.getY() - 1] == CellType.OBSTACLE) {
                    return false;
                }
            }
            case LEFT -> {
                if (cells[position.getX() - 1][position.getY()] == CellType.OBSTACLE) {
                    return false;
                }
            }
            case RIGHT -> {
                if (cells[position.getX() + 1][position.getY()] == CellType.OBSTACLE) {
                    return false;
                }
            }
        }
        return true;
    }
}
