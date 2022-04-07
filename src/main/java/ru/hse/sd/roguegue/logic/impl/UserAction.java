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
        if (!validateStep(move)) {
            System.out.println("not valid");
            state.updatePosition(position);
            return;
        }
        switch (move) {
            case UP -> state.updatePosition(new Position(position.getX(), position.getY() - 1));
            case DOWN -> state.updatePosition(new Position(position.getX(), position.getY() + 1));
            case LEFT -> state.updatePosition(new Position(position.getX() - 1, position.getY()));
            case RIGHT -> state.updatePosition(new Position(position.getX() + 1, position.getY()));
        }
        if (Status.mapState.getMap().cellArray()[Status.userState.getPosition().getY()][Status.userState.getPosition().getX()] == CellType.EXIT) {
            Status.gameStatus = GameStatus.MENU;
            Status.gameState.finishLevel();
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
        }
        return true;
    }
}
