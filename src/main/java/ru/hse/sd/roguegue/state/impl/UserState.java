package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.state.GameObjectState;
import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.status.InventoryItem;
import ru.hse.sd.roguegue.status.Status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class for the specific information about user
 */
public class UserState extends GameObjectState {
    private int health;
    private int exp;
    private int strength;
    private int lives;
    private Set<InventoryItem> inventoryStorage = new HashSet<>();
    private Set<InventoryItem> activeInventory = new HashSet<>();

    public void setInitialValues() {
        health = 100;
        exp = 0;
        strength = 50;
        lives = 5;
        inventoryStorage = new HashSet<>();
        activeInventory = new HashSet<>();
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
            // todo хз надо ли
            exp -= 10;
            health = Constants.MAX_USER_HEALTH - cur;
        }
        Status.userUI.displayHealth();
        // todo display lives
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
     * @param newStrength
     * update attack according to the newAttack
     */
    public void updateStrength(int newStrength) {
        strength = newStrength;
//        Status.userUI.displayAttack();
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
        // todo
//        Status.userUI.displayLives();
    }

    public int getLives() {
        return lives;
    }

    public void putOnInventoryItem(InventoryItem item) {
        strength += item.plusAttack;
        health += item.plusHealth;
        activeInventory.add(item);
        inventoryStorage.add(item);
    }

    public void takeOffInventoryItem(InventoryItem item) {
        activeInventory.remove(item);
        strength -= item.plusAttack;
        health -= item.plusHealth;
    }
}
