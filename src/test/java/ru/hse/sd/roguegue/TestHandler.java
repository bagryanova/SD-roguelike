package ru.hse.sd.roguegue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hse.sd.roguegue.logic.GameAction;
import ru.hse.sd.roguegue.map.CellType;
import ru.hse.sd.roguegue.map.Map;
import ru.hse.sd.roguegue.preprocessing.Handler;
import ru.hse.sd.roguegue.preprocessing.Menu;
import ru.hse.sd.roguegue.preprocessing.UserInteraction;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHandler {
    @BeforeEach
    public void init() {
        Status.init();
        Status.gameStatus = GameStatus.GAME;
        CellType[][] cellTypes = new CellType[3][3];
        for (int i = 0; i < cellTypes.length; i++) {
            for (int j = 0; j < cellTypes[i].length; j++) {
                cellTypes[i][j] = CellType.GROUND;
            }
        }
        cellTypes[1][1] = CellType.PLAYER;
        Status.userState.updatePosition(new Position(1, 1));
        Map map = new Map(cellTypes);
        GameAction gameAction = new GameAction();
        gameAction.updateState(map);
    }

    @Test
    public void testHandlerUp() {
        assertEquals(Status.userState.getPosition(), new Position(1, 1));
        UserInteraction userInteraction = new UserInteraction();
        Handler handler = new Handler();
        handler.setUserAction(new KeyEvent(userInteraction, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_UP, 'w'));
        assertEquals(Status.userState.getPosition(), new Position(1, 2));
    }

    @Test
    public void testHandlerDown() {
        assertEquals(Status.userState.getPosition(), new Position(1, 1));
        UserInteraction userInteraction = new UserInteraction();
        Handler handler = new Handler();
        handler.setUserAction(new KeyEvent(userInteraction, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_DOWN, 's'));
        assertEquals(Status.userState.getPosition(), new Position(1, 0));
    }

    @Test
    public void testHandlerLeft() {
        assertEquals(Status.userState.getPosition(), new Position(1, 1));
        UserInteraction userInteraction = new UserInteraction();
        Handler handler = new Handler();
        handler.setUserAction(new KeyEvent(userInteraction, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_LEFT, 'a'));
        assertEquals(Status.userState.getPosition(), new Position(0, 1));
    }

    @Test
    public void testHandlerRight() {
        assertEquals(Status.userState.getPosition(), new Position(1, 1));
        UserInteraction userInteraction = new UserInteraction();
        Handler handler = new Handler();
        handler.setUserAction(new KeyEvent(userInteraction, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_RIGHT, 'd'));
        assertEquals(Status.userState.getPosition(), new Position(2, 1));
    }
}
