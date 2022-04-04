package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.status.Status;

public class GameState {
    private int score;

    // todo как у нас вообще счет обновляется..?
    public void updateScore(int newScore) {
        System.out.print("Change score from " + score);
        score = newScore;
        System.out.println("to " + score);
        Status.gameUI.displayScore();
    }

    public int getScore() {
        return score;
    }

    // todo соба
    public void startLevel() {
        Status.screen = Status.screen.change();
        Status.terminal.clear();
        Status.screen.display();
    }

    public void finishLevel() {
        Status.screen = Status.screen.change();
        Status.terminal.clear();
        Status.screen.display();
    }
}
