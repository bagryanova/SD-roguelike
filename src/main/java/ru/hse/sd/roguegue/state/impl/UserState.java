package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.state.GameObjectState;
import ru.hse.sd.roguegue.status.Status;

/**
 * Class for the specific information about user
 */
public class UserState extends GameObjectState {
    private int health;

    /**
     * @param newHealth
     * update health according to the newHealth and display changes on the screen
     */
    public void updateHealth(int newHealth) {
        //System.out.print("Change health from " + health);
        health = newHealth;
        //System.out.println(" to " + health);
        Status.userUI.displayHealth();
    }

    public int getHealth() {
        return health;
    }
}
