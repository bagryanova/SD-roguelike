package ru.hse.sd.roguegue.UI;

import asciiPanel.AsciiPanel;
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
    int dy = Constants.MAP_START_Y;
    int dx = Constants.MAP_START_X;

    public GameObjectUI(){}

    /**
     * @param position
     * Display object view in the position
     */
    public void displayPosition(Position position) {
        Status.terminal.write(view, position.getX() + dx, position.getY() + dy, color);
    }

}
