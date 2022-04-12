package ru.hse.sd.roguegue.status;

import asciiPanel.AsciiPanel;
import ru.hse.sd.roguegue.UI.GameUI;
import ru.hse.sd.roguegue.UI.MapUI;
import ru.hse.sd.roguegue.UI.UserUI;
import ru.hse.sd.roguegue.UI.screens.MenuScreen;
import ru.hse.sd.roguegue.UI.screens.Screen;
import ru.hse.sd.roguegue.state.impl.GameState;
import ru.hse.sd.roguegue.state.impl.MapState;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.state.impl.UserState;

import java.util.ArrayList;
import java.util.List;

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
     * Terminal for UI
     */
    public static AsciiPanel terminal = new AsciiPanel(Constants.TERMINAL_SIZE_X, Constants.TERMINAL_SIZE_Y);

    /**
     * Current version of screen (menu, game or win)
     */
    public static Screen screen = new MenuScreen();

    /**
     * UI for Map. Can display it
     */
    public static MapUI mapUI = new MapUI();

    /**
     * UI for game information. Can display score
     */
    public static GameUI gameUI = new GameUI();

    /**
     * UI for user. Can display user
     */
    public static UserUI userUI = new UserUI();

    // todo где-нибудь инициализировать в начале игры и задать этому всему характеристики
    public static List<String> inventoryObjects = List.of("Helmet", "Sword", "Knife", "Coat");

    public static List<InventoryItem> inventory = new ArrayList<>(List.of());

    /**
     * Initializes game status by setting default values todo
     */
    public static void init() {
        gameStatus = GameStatus.MENU;
        mapMode = MapMode.RANDOM;
    }

    public static void init(MapMode newMapMode) {
        gameStatus = GameStatus.MENU;
        mapMode = newMapMode;
    }

}
