package ru.hse.sd.roguegue.map.impl;

import ru.hse.sd.roguegue.map.MobFactory;
import ru.hse.sd.roguegue.state.mobs.MobStrategy;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.status.MobType;

public class NatureMobFactory implements MobFactory {
    @Override
    public MobState createMob(Position position) {
        MobState mob = new MobState(position, MobType.NATURE);
        mob.updateLives(5);
        mob.updateStrength(0);
        return mob;
    }
}
