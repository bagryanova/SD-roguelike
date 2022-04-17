package ru.hse.sd.roguegue.UI;

import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.map.CellType;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.Status;

import java.util.EnumMap;

/**
 * Class for displaying the map. It should have graphic representations for all cell types.
 * Can display a full map or a one cell.
 */
public class MapUI {
    private final EnumMap<CellType, Character> graphicRepresentation;
    private final int dx = Constants.MAP_START_X;
    private final int dy = Constants.MAP_START_Y;


    /**
     * In the constructor we have to define all graphic representations
     */
    public MapUI() {
        graphicRepresentation = new EnumMap<>(CellType.class);
        graphicRepresentation.put(CellType.GROUND, (char)250);
        graphicRepresentation.put(CellType.OBSTACLE, (char)177);
        graphicRepresentation.put(CellType.EXIT, '+');
    }

    /**
     * @param position
     * Display only one cell from the map according to the position
     */
    public void displayCell(Position position) {
        if (Status.mapState.getMap() == null) {
            return;
        }
        CellType curCell = Status.mapState.getMap().getCell(position);
        Status.terminal.write(graphicRepresentation.get(curCell), dx + position.getX(), dy + position.getY());
    }

    /**
     * Display a full map
     */
    public void displayMap() {
        if (Status.mapState.getMap() == null) {
            return;
        }
        CellType[][] cellArray = Status.mapState.getMap().cellArray();
        Status.terminal.setCursorY(dy);
        for (int i = 0; i < cellArray.length; ++i) {
            Status.terminal.setCursorX(dx);
            for (int j = 0; j < cellArray[i].length; ++j) {
                Status.terminal.write(graphicRepresentation.get(cellArray[i][j]));
            }
            Status.terminal.setCursorY(Status.terminal.getCursorY() + 1);
        }

    }
}
