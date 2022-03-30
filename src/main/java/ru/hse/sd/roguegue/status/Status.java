package ru.hse.sd.roguegue.status;

public class Status {
    public static GameStatus gameStatus;

    public static void init(){
        gameStatus = GameStatus.MENU;
    }
}
