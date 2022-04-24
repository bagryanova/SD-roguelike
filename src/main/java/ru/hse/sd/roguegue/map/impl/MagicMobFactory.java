package ru.hse.sd.roguegue.map.impl;

import ru.hse.sd.roguegue.map.MobFactory;
import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.impl.MobState;

public class MagicMobFactory implements MobFactory {
    @Override
    public MobState createMob(MobStrategy strategy, Position position) {
        MobState mob = new MobState(strategy, position);
        mob.updateLives(3);
        mob.updateStrength(7);
        return null;
    }
}
