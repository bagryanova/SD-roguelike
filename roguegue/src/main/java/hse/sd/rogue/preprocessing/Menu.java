package hse.sd.rogue.preprocessing;

import hse.sd.rogue.logic.GameAction;
import hse.sd.rogue.status.GameStatus;
import hse.sd.rogue.map.Map;
import hse.sd.rogue.status.Status;
import hse.sd.rogue.map.MapGenerator;

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
        map = mapGenerator.getMap();
        Status.gameStatus = GameStatus.GAME;
        gameAction.updateState(map);
    }

    private void endGame() {
        Status.gameStatus = GameStatus.EXIT;
    }

    private boolean validateAction(KeyEvent event) {
        return event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_Q;
    }
}
