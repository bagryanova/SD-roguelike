package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.state.GameObjectState;
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
    private int attack;
    private Set<InventoryItem> inventoryStorage = new HashSet<>();
    private Set<InventoryItem> activeInventory = new HashSet<>();

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

    public void putOnInventoryItem(InventoryItem item) {
        attack += item.plusAttack;
        health += item.plusHealth;
        activeInventory.add(item);
        inventoryStorage.add(item);
    }

    public void takeOffInventoryItem(InventoryItem item) {
        activeInventory.remove(item);
        attack -= item.plusAttack;
        health -= item.plusHealth;
    }
}
