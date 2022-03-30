package hse.sd.rogue;

import hse.sd.rogue.preprocessing.UserInteraction;
import hse.sd.rogue.status.GameStatus;
import hse.sd.rogue.status.Status;

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
