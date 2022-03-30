package ru.hse.sd.roguegue.logic;

import ru.hse.sd.roguegue.map.Map;

public interface GameObjectAction {
    void updateState(Map map);
}
