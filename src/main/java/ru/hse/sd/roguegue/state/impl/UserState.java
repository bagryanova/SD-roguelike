package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.state.GameObjectState;
import ru.hse.sd.roguegue.status.Status;

public class UserState extends GameObjectState {
    private int health;

    public void updateHealth(int newHealth) {
        System.out.print("Change health from " + health);
        health = newHealth;
        System.out.println(" to " + health);
        Status.userUI.displayHealth();
    }

    public int getHealth() {
        return health;
    }
}
