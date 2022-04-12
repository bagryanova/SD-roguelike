package ru.hse.sd.roguegue.status;

import ru.hse.sd.roguegue.state.Position;

public class InventoryItem {
    public String name;
    public Position position;
    public InventoryItem(String name, Position position) {
        this.name = name;
        this.position = position;
    }
}
