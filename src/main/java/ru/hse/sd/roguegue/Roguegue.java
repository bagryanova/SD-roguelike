package ru.hse.sd.roguegue;

import ru.hse.sd.roguegue.preprocessing.UserInteraction;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.MapMode;
import ru.hse.sd.roguegue.status.Status;

import java.util.Objects;

public class Roguegue {
    /**
     * Initialize status and start game
     */
    public static void main(String[] args) {
        if (args.length == 1 && Objects.equals(args[0], "-f")) {
            Status.init(MapMode.FILE);
        } else {
            Status.init();
        }
        UserInteraction userInteraction = new UserInteraction();
        userInteraction.startMob();
        while (Status.gameStatus != GameStatus.EXIT) {
            userInteraction.getUserAction();
        }
    }
}
