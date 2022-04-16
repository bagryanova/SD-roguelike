package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.UI.UserUI;
import ru.hse.sd.roguegue.state.GameObjectState;
import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.status.InventoryItem;
import ru.hse.sd.roguegue.status.Status;

import java.util.*;

/**
 * Class for the specific information about user
 */
public class UserState extends GameObjectState {
    private int health;
    private int exp;
    private int strength;
    private int lives;
    private final HashMap<Integer, List<Integer>> levels = new HashMap<>();
    public UserUI userUI = new UserUI();

    {
        setUI(userUI);
    }

    public void setInitialValues() {
        health = 100;
        exp = 0;
        strength = 50;
        lives = 5;
        // initial levels
        levels.put(0, List.of(20, 100, 50)); // level num; req exp, health, strength
        levels.put(1, List.of(60, 125, 60));
        levels.put(2, List.of(100, 140, 75));
        levels.put(3, List.of(140, 175, 90));
        levels.put(4, List.of(200, 205, 100));
    }

    /**
     * @param lostHealth
     * update health according to the newHealth and display changes on the screen
     */
    public void updateHealth(int lostHealth) {
        if (health > lostHealth) {
            health -= lostHealth;
        }
        else {
            int cur = lostHealth - health;
            int cnt = 0;
            while (cur > 0) {
                cur -= Constants.MAX_USER_HEALTH;
                cnt++;
            }
            lives -= cnt;
            health = Constants.MAX_USER_HEALTH - cur;
        }
        Status.userState.userUI.displayInformation();
    }

    public int getHealth() {
        return health;
    }

    public void defeatMob() {
        exp += 20;
        if (exp > levels.get(4).get(0)) {
            health = levels.get(4).get(1);
            strength = levels.get(4).get(2);
        } else if (exp > levels.get(3).get(0)) {
            health = levels.get(3).get(1);
            strength = levels.get(3).get(2);
        } // todo in cycle
    }

    /**
     * @param newExp
     * update experience according to the newExp and display changes on the screen
     */
    public void updateExp(int newExp) {
        exp = newExp;
        Status.userState.userUI.displayInformation();
    }

    public int getExp() {
        return exp;
    }

    /**
     * @param newStrength
     * update attack according to the newAttack
     */
    public void updateStrength(int newStrength) {
        strength = newStrength;
        Status.userState.userUI.displayInformation();
    }

    public int getStrength() {
        return strength;
    }

    /**
     * @param newLives
     * update attack according to the newAttack
     */
    public void updateLives(int newLives) {
        lives = newLives;
        Status.userState.userUI.displayInformation();
    }

    public int getLives() {
        return lives;
    }

    public void putOnInventoryItem(InventoryItem item) {
        updateStrength(strength + item.plusAttack);
        updateHealth(health + item.plusHealth);

    }

    public void takeOffInventoryItem(InventoryItem item) {
        updateStrength(strength - item.plusAttack);
        updateHealth(health - item.plusHealth);
    }
}
