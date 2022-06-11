package ru.hse.sd.roguegue.preprocessing;

import ru.hse.sd.roguegue.logic.GameAction;
import ru.hse.sd.roguegue.map.Map;
import ru.hse.sd.roguegue.map.MapBuilderDirector;
import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Menu {
    private final GameAction gameAction = new GameAction();
    private Map map;

    /**
     * Depending on the user's action start the game or exit
     */
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
        Status.inventoryMapItems = new ArrayList<>();
        Status.gameState.getInventoryState().setInitialValues();
        Status.gameState.setMobStates(new ArrayList<>());
        MapBuilderDirector director = new MapBuilderDirector(Status.mapMode, Constants.MAP_WIDTH, Constants.MAP_HEIGHT, null);
        map = director.build();
        Status.userState.setInitialValues();
        Status.gameStatus = GameStatus.GAME;
        gameAction.updateState(map);
    }

    private void endGame() {
        Status.gameStatus = GameStatus.EXIT;
    }

    private boolean validateAction(KeyEvent event) {
        return event != null && (event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_Q);
    }
}
