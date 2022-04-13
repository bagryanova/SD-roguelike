package ru.hse.sd.roguegue.status;

import ru.hse.sd.roguegue.state.Position;

public class InventoryItem {
    public String name;
    public Position position;
    public int plusAttack;
    public int plusHealth;

    public InventoryItem(String name, Position position, int plusAttack, int plusHealth) {
        this.name = name;
        this.position = position;
        this.plusAttack = plusAttack;
        this.plusHealth = plusHealth;
    }
}
