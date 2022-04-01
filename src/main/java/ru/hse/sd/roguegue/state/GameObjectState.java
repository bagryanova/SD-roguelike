package ru.hse.sd.roguegue.state;

public abstract class GameObjectState {
    private Position position = new Position(0, 0); //!

    public void updatePosition(Position newPosition) {
        System.out.print("Change position from (" + position.getX() + ", " + position.getY() + ")");
        position = newPosition;
        System.out.println("to (" + position.getX() + ", " + position.getY() + ")");
    }

    public Position getPosition() {
        return position;
    }
}
