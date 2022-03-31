package ru.hse.sd.roguegue;

import ru.hse.sd.roguegue.preprocessing.UserInteraction;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

public class Roguegue {
    public static void main(String[] args) {
        Status.init();
        UserInteraction userInteraction = new UserInteraction();
        while (Status.gameStatus != GameStatus.EXIT) {
//            if(Status.gameStatus == GameStatus.MENU){
//                System.out.println("Press ENTER to start\nPress Q to quit");
//            }
            userInteraction.getUserAction();
        }
    }
}
