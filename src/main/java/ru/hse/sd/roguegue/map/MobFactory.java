package ru.hse.sd.roguegue.map;

import ru.hse.sd.roguegue.state.mobs.MobStrategy;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.impl.MobState;

public interface MobFactory {
    MobState createMob(Position position);
}
