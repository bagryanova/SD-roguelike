package ru.hse.sd.roguegue.UI;

import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.impl.AggressiveStrategy;
import ru.hse.sd.roguegue.state.impl.AvoidingStrategy;
import ru.hse.sd.roguegue.state.impl.PassiveStrategy;

/**
 * Class for displaying mob on the map and related information
 */
public class MobUI extends GameObjectUI {

    public MobUI(MobStrategy mobStrategy) {
        super();
        updateUI(mobStrategy);
    }

    /**
     * @param mobStrategy
     * Changes view of a mob according to the mobStrategy
     */
    public void updateUI(MobStrategy mobStrategy) {
        if (AggressiveStrategy.class.equals(mobStrategy.getClass())) {
            view = 'a';
        } else if (AvoidingStrategy.class.equals(mobStrategy.getClass())) {
            view = 'c'; //coward
        } else if (PassiveStrategy.class.equals(mobStrategy.getClass())) {
            view = 'p';
        }
    }
}
