package ru.hse.sd.roguegue.logic;

import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.Status;

public class UserAction {
    /**
     * Updates user state according to the move made by user
     *
     * @param move current user's move
     */
    public void updateState(Move move) {
        Position position = Status.userState.getPosition();
        switch (move) {
            case UP:
                position.updatePosition(position.getX(), position.getY() + 1);
            case DOWN:
                position.updatePosition(position.getX(), position.getY() - 1);
            case LEFT:
                position.updatePosition(position.getX() - 1, position.getY());
            case RIGHT:
                position.updatePosition(position.getX() + 1, position.getY());
        }
    }
}
