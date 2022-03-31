package ru.hse.sd.roguegue.status;

import ru.hse.sd.roguegue.state.impl.GameState;
import ru.hse.sd.roguegue.state.impl.MapState;
import ru.hse.sd.roguegue.state.impl.UserState;

public class Status {
    /**
     * Current game status
     */
    public static GameStatus gameStatus;

    /**
     * Mode in which map will be loaded (random generation or from file)
     */
    public static MapMode mapMode;

    /**
     * Instance of user state
     */
    public static UserState userState = new UserState();

    /**
     * Instance of game state
     */
    public static GameState gameState = new GameState();

    /**
     * Instance of map state
     */
    public static MapState mapState = new MapState();

    /**
     * Initializes game status by setting default values todo
     */
    public static void init() {
        gameStatus = GameStatus.MENU;
    }

}
