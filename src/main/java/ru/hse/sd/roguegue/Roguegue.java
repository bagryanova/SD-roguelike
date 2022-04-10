package ru.hse.sd.roguegue;

import ru.hse.sd.roguegue.preprocessing.UserInteraction;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

public class Roguegue {
    public static void main(String[] args) {
        Status.init();
        UserInteraction userInteraction = new UserInteraction();
        while (Status.gameStatus != GameStatus.EXIT) {
            userInteraction.getUserAction();
        }
    }
}
