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
        var state = Status.userState;
        // todo validate, добавить границу, если стоит на выходе, вызывать финиш игры у game стейта, поменять стейт на меню
        switch (move) {
            case UP ->
                state.updatePosition(new Position(state.getPosition().getX(), state.getPosition().getY() + 1));
            case DOWN ->
                state.updatePosition(new Position(state.getPosition().getX(), state.getPosition().getY() - 1));
            case LEFT ->
                state.updatePosition(new Position(state.getPosition().getX() - 1, state.getPosition().getY()));
            case RIGHT ->
                state.updatePosition(new Position(state.getPosition().getX() + 1, state.getPosition().getY()));
        }
    }
}
