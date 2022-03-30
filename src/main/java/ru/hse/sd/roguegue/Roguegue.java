package ru.hse.sd.roguegue;

import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

public class Roguegue {
    public static void main(String[] args) {
        Status.init();
        while (Status.gameStatus != GameStatus.EXIT) {
            // do smth
        }
    }
}
