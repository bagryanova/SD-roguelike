package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.state.GameObjectState;
import ru.hse.sd.roguegue.status.Status;

/**
 * Class for the specific information about user
 */
public class UserState extends GameObjectState {
    private int health;
    private int exp;
    private int attack;

    /**
     * @param newHealth
     * update health according to the newHealth and display changes on the screen
     */
    public void updateHealth(int newHealth) {
        health = newHealth;
        Status.userUI.displayHealth();
    }

    public int getHealth() {
        return health;
    }

    /**
     * @param newExp
     * update experience according to the newExp and display changes on the screen
     */
    public void updateExp(int newExp) {
        exp = newExp;
        // todo soba
//        Status.userUI.displayExp();
    }

    public int getExp() {
        return exp;
    }

    /**
     * @param newAttack
     * update attack according to the newAttack
     */
    public void updateAttack(int newAttack) {
        attack = newAttack;
//        Status.userUI.displayAttack();
    }

    public int getAttack() {
        return attack;
    }
}
