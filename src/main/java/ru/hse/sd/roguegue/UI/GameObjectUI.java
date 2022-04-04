package ru.hse.sd.roguegue.UI;

import ru.hse.sd.roguegue.Constants;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.Status;

public abstract class GameObjectUI {
    private Character view = '?';
    int dy = Constants.MAP_START_Y;
    int dx = Constants.MAP_START_X;

    public void displayPosition(Position position) {
        Status.terminal.write(view, position.getX() + dx, position.getY() + dy);
    }
}
