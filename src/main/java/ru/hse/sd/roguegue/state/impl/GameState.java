package ru.hse.sd.roguegue.state.impl;

public class GameState {
    private int score;

    public void updateScore(int newScore) {
        score = newScore;
    }

    public int getScore() {
        return score;
    }
}
