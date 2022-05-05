package ru.hse.sd.roguegue.state;

import ru.hse.sd.roguegue.UI.Commands.DisplayNewObjectPositionCommand;

/**
 * Abstract class for the information about GameObject
 */
public abstract class GameObjectState {
    public Position position = new Position(0, 0);

    /**
     * @param newPosition update position according to the newPosition and display changes on the screen
     */
    public void updatePosition(Position newPosition) {
        Position prev = position;
        position = newPosition;
        new DisplayNewObjectPositionCommand(prev).execute();
    }

    public Position getPosition() {
        return position;
    }
}
