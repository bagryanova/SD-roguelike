package ru.hse.sd.roguegue.state;

import ru.hse.sd.roguegue.status.Status;

/**
 * Abstract class for the information about GameObject
 */
public abstract class GameObjectState {
    private Position position = new Position(0, 0);

    /**
     * @param newPosition
     * update position according to the newPosition and display changes on the screen
     */
    public void updatePosition(Position newPosition) {
        //System.out.print("Change position from (" + position.getX() + ", " + position.getY() + ")");
        Status.mapUI.displayCell(position);
        position = newPosition;
        //System.out.println(" to (" + position.getX() + ", " + position.getY() + ")");
        Status.userUI.displayPosition();
    }

    public Position getPosition() {
        return position;
    }
}
