package ru.hse.sd.roguegue.map.impl;

import ru.hse.sd.roguegue.map.MobFactory;
import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.status.MobType;

public class NatureMobFactory implements MobFactory {
    @Override
    public MobState createMob(MobStrategy strategy, Position position) {
        MobState mob = new MobState(strategy, position, MobType.NATURE);
        mob.updateLives(3);
        mob.updateStrength(3);
        return mob;
    }
}
