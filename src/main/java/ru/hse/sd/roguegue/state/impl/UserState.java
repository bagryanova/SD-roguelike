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
    private int level = 0;

    public void setInitialValues() {
        health = 10;
        exp = 0;
        strength = 5;
        lives = 5;
        levels.put(0, List.of(20, 12, 50)); // level num; req exp, health, strength
        levels.put(1, List.of(60, 15, 60));
        levels.put(2, List.of(100, 17, 75));
        levels.put(3, List.of(140, 20, 90));
        levels.put(4, List.of(200, 25, 100));
    }

    /**
     * @param lostHealth health that was lost in a fight
     *
     * Update health according to the newHealth and display changes on the screen
     */
    public void loseHealth(int lostHealth) {
        if (health > lostHealth) {
            updateHealth(health - lostHealth);
        } else {
            int cur = lostHealth - health;
            int cnt = 0;
            while (cur > 0) {
                cur -= levels.get(level).get(1);
                cnt++;
            }
            cur += levels.get(level).get(1);
            System.out.println("lives " + lives + ' ' + cnt);
            updateLives(lives - cnt);
            updateHealth(levels.get(level).get(1) - cur);
        }
    }

    public void updateHealth(int newHealth) {
        this.health = newHealth;
        Status.userState.userUI.displayInformation();
    }

    public int getHealth() {
        return health;
    }

    public void defeatMob() {
        System.out.println(exp);
        updateExp(exp + 20);
        for (int i = 4; i >= 0; i--) {
            if (exp > levels.get(i).get(0)) {
                updateHealth(levels.get(i).get(1));
                updateStrength(levels.get(i).get(2));
            }
        }
    }

    /**
     * @param newExp update experience according to the newExp and display changes on the screen
     */
    public void updateExp(int newExp) {
        exp = newExp;
        Status.userState.userUI.displayInformation();
    }

    public int getExp() {
        return exp;
    }

    /**
     * @param newStrength update attack according to the newAttack
     */
    public void updateStrength(int newStrength) {
        strength = newStrength;
        Status.userState.userUI.displayInformation();
    }

    public int getStrength() {
        return strength;
    }

    /**
     * @param newLives update attack according to the newAttack
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
