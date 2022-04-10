package ru.hse.sd.roguegue.UI;

import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.Status;

/**
 * Abstract class for displaying the information about GameObject. It has the char view of the object
 */
public abstract class GameObjectUI {
    private Character view = '?';
    int dy = Constants.MAP_START_Y;
    int dx = Constants.MAP_START_X;

    /**
     * @param position
     * Display object view in the position
     */
    public void displayPosition(Position position) {
        Status.terminal.write(view, position.getX() + dx, position.getY() + dy);
    }
}
