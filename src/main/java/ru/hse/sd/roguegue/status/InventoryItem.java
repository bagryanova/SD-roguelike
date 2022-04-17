package ru.hse.sd.roguegue.status;

import ru.hse.sd.roguegue.state.GameObjectState;
import ru.hse.sd.roguegue.state.Position;

public class InventoryItem extends GameObjectState {
    public String name;
    public int plusAttack;
    public int plusHealth;
    public boolean present = true;

    public InventoryItem(String name, Position position, int plusAttack, int plusHealth) {
        this.name = name;
        this.position = position;
        this.plusAttack = plusAttack;
        this.plusHealth = plusHealth;
    }
}
