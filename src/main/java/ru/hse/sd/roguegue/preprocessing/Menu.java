package ru.hse.sd.roguegue.preprocessing;

import ru.hse.sd.roguegue.logic.GameAction;
import ru.hse.sd.roguegue.map.Map;
import ru.hse.sd.roguegue.map.MapGenerator;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.MapMode;
import ru.hse.sd.roguegue.status.Status;

import java.awt.event.KeyEvent;

public class Menu {
    private final GameAction gameAction = new GameAction();
    private Map map;

    public void handleAction(KeyEvent event) {
        if (!validateAction(event)) {
            return;
        }
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
            startGame();
        } else {
            endGame();
        }
    }

    private void startGame() {
        MapGenerator mapGenerator = new MapGenerator();
        Status.mapMode = MapMode.RANDOM;
        map = mapGenerator.getMap();
        Status.gameStatus = GameStatus.GAME;
        Status.userState.updatePosition(new Position(0, 0));
        gameAction.updateState(map);
    }

    private void endGame() {
        Status.gameStatus = GameStatus.EXIT;
    }

    private boolean validateAction(KeyEvent event) {
        return event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_Q;
    }
}
