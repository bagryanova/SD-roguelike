package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.state.GameObjectState;

public class UserState extends GameObjectState {
    private int health;

    public void updateHealth(int newHealth) {
        System.out.print("Change health from " + health);
        health = newHealth;
        System.out.println("to " + health);
    }

    public int getHealth() {
        return health;
    }
}
