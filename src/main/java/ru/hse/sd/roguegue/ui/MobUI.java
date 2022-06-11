package ru.hse.sd.roguegue.ui;

import asciiPanel.AsciiPanel;
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
     * Changes view of a mob according to the mobType
     */
    private void updateUI(MobType mobType) {
        view = '\u001E';
        if (mobType == MobType.NATURE) {
            color = AsciiPanel.brightGreen;
        } else if (mobType == MobType.TECH) {
            color = AsciiPanel.brightCyan;
        } else if (mobType == MobType.MAGIC) {
            color = AsciiPanel.brightMagenta;
        }
    }
}
