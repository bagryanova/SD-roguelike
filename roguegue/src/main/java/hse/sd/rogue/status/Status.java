package hse.sd.rogue.status;

import hse.sd.rogue.status.GameStatus;

public class Status {
    public static GameStatus gameStatus;
    public static void init(){
        gameStatus = GameStatus.MENU;
    }
}
