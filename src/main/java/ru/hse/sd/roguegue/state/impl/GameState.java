package ru.hse.sd.roguegue.state.impl;

public class GameState {
    private int score;

    // todo как у нас вообще счет обновляется..?
    public void updateScore(int newScore) {
        System.out.print("Change score from " + score);
        score = newScore;
        System.out.println("to " + score);
    }

    public int getScore() {
        return score;
    }
}
