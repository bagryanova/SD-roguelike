package ru.hse.sd.roguegue.UI;

import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.status.Status;

/**
 * Class for displaying user on the map and related information
 */
public class UserUI extends GameObjectUI {

    public UserUI() {
        super();
        view = '!';
    }

    /**
     * Display user view on the map
     */
    public void displayPosition() {
        Status.terminal.write(view, Status.userState.getPosition().getX() + dx, Status.userState.getPosition().getY() + dy);
    }

    /**
     * Display user's health above the map
     */
    public void displayInformation() {
        Status.terminal.writeCenter("                                       ", Constants.HEALTH_Y);
        Status.terminal.writeCenter("HEALTH: " + Status.userState.getHealth(), Constants.HEALTH_Y);
        Status.terminal.writeCenter("                                       ", Constants.HEALTH_Y + 1);
        Status.terminal.writeCenter("LIVES: " + Status.userState.getLives(), Constants.HEALTH_Y + 1);
        Status.terminal.writeCenter("                                       ", Constants.HEALTH_Y + 2);
        Status.terminal.writeCenter("EXP: " + Status.userState.getExp(), Constants.HEALTH_Y + 2);
        Status.terminal.writeCenter("                                       ", Constants.HEALTH_Y + 3);
        Status.terminal.writeCenter("STRENGTH: " + Status.userState.getStrength(), Constants.HEALTH_Y + 3);
    }
}
