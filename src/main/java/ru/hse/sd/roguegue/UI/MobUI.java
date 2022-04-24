package ru.hse.sd.roguegue.UI;

import ru.hse.sd.roguegue.status.MobType;

/**
 * Class for displaying mob on the map and related information
 */
public class MobUI extends GameObjectUI {

    public MobUI(MobType mobType) {
        super();
        updateUI(mobType);
    }

    /**
     * @param mobType
     * Changes view of a mob according to the mobType
     */
    private void updateUI(MobType mobType) {
        if (mobType == MobType.NATURE) {
            view = 'n';
        } else if (mobType == MobType.TECH) {
            view = 't';
        } else if (mobType == MobType.MAGIC) {
            view = 'm';
        }
    }
}
