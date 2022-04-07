package ru.hse.sd.roguegue.UI;

import ru.hse.sd.roguegue.Constants;
import ru.hse.sd.roguegue.map.CellType;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.Status;

import java.util.EnumMap;

public class MapUI {
    private final EnumMap<CellType, Character> graphicRepresentation;
    private final int dx = Constants.MAP_START_X;
    private final int dy = Constants.MAP_START_Y;


    public MapUI() {
        graphicRepresentation = new EnumMap<>(CellType.class);
        graphicRepresentation.put(CellType.GROUND, (char)250);
        graphicRepresentation.put(CellType.OBSTACLE, (char)177);
        graphicRepresentation.put(CellType.EXIT, '+');
    }

    public void displayCell(Position position) {
        if (Status.mapState.getMap() == null) {
            return;
        }
        CellType curCell = Status.mapState.getMap().getCell(position);
        Status.terminal.write(graphicRepresentation.get(curCell), dx + position.getX(), dy + position.getY());
    }

    public void displayMap() {
        //System.out.println("CCCC");
        //Status.terminal.clear();
        //Status.screen.display();
        CellType[][] cellArray = Status.mapState.getMap().cellArray();
        Status.terminal.setCursorY(dy);
        for (int i = 0; i < cellArray.length; ++i) {
            //System.out.println(Status.terminal.getCursorX());
            Status.terminal.setCursorX(dx);
            for (int j = 0; j < cellArray[i].length; ++j) {
//                System.out.println(cellArray[i][j]);
                Status.terminal.write(graphicRepresentation.get(cellArray[i][j]));
                //graphicRepresentation.get(cellArray[i][j]);
            }
            Status.terminal.setCursorY(Status.terminal.getCursorY() + 1);
        }

    }
}
