package ru.hse.sd.roguegue.ui;

import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.Status;

import java.awt.*;

/**
 * Abstract class for displaying the information about GameObject. It has the char view of the object
 */
public class GameObjectUI {
    protected Character view = '?';
    protected Color color;
    protected int dy = Constants.MAP_START_Y;
    protected int dx = Constants.MAP_START_X;

    public GameObjectUI() {}

    /**
     * Display object view in the position on the map
     */
    public void displayPosition(Position position) {
        Status.terminal.write(view, position.getX() + dx, position.getY() + dy, color);
    }

}
