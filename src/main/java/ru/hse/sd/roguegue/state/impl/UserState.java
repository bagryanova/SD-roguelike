package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.UI.Commands.DisplayInformationCommand;
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
    public UserUI userUI = new UserUI();
    private int level = 0;
    // Key - level number; Value - required experience, health, strength
    private final Map<Integer, List<Integer>> levels = Map.of(0, List.of(20, 12, 50),
            1, List.of(60, 15, 60),
            2, List.of(100, 17, 75),
            3, List.of(140, 20, 90),
            4, List.of(200, 25, 100));

    /**
     * Set initial default values in case of user's death or start of the new game
     */
    public void setInitialValues() {
        health = 10;
        exp = 0;
        strength = 1;
        lives = 5;
    }

    /**
     * @param lostHealth health that was lost in a fight
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

    /**
     * @param newHealth new health to be set
     *                  Updates health according to the new health
     */
    public void updateHealth(int newHealth) {
        this.health = newHealth;
        new DisplayInformationCommand().execute();
    }

    /**
     * @return current health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Update experience after mob's death and set a new level if user gained enough experience
     */
    public void defeatMob() {
        updateExp(exp + 20);
        for (int i = 4; i >= 0; i--) {
            if (exp > levels.get(i).get(0)) {
                level = i;
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
        new DisplayInformationCommand().execute();
    }

    public int getExp() {
        return exp;
    }

    /**
     * @param newStrength update attack according to the newAttack
     */
    public void updateStrength(int newStrength) {
        strength = newStrength;
        new DisplayInformationCommand().execute();
    }

    /**
     * @return current strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @param newLives update attack according to the newAttack
     */
    public void updateLives(int newLives) {
        lives = newLives;
        new DisplayInformationCommand().execute();
    }

    /**
     * @return current number of lives
     */
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
