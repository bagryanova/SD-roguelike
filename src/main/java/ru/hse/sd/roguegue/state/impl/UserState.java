package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.state.GameObjectState;

public class UserState extends GameObjectState {
    private int health;

    public void updateHealth(int newHealth) {
        health = newHealth;
    }

    public int getHealth() {
        return health;
    }
}
