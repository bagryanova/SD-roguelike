package ru.hse.sd.roguegue.preprocessing;

import ru.hse.sd.roguegue.logic.impl.GameAction;
import ru.hse.sd.roguegue.map.Map;
import ru.hse.sd.roguegue.map.MapGenerator;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

public class Menu {

    private final GameAction gameAction = new GameAction();
    private Map map;

    public void handleAction() {
        startGame();
    }

    private void startGame() {
        MapGenerator mapGenerator = new MapGenerator();
        map = mapGenerator.getMap();
        Status.gameStatus = GameStatus.GAME;
        gameAction.updateState(map);
    }
}
