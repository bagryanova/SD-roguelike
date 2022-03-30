package ru.hse.sd.roguegue.state;

public abstract class GameObjectState {
    private Position position;

    public void updatePosition(Position newPosition) {
        position = newPosition;
    }

    public Position getPosition() {
        return position;
    }
}
