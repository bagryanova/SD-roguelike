package ru.hse.sd.roguegue.UI;

import ru.hse.sd.roguegue.Constants;
import ru.hse.sd.roguegue.status.Status;

/**
 * Class for displaying user on the map and related information. It has the char view of the user.
 */
public class UserUI extends GameObjectUI{
    private Character view = '!';

    /**
     * Display user view on the map
     */
    public void displayPosition() {
        Status.terminal.write(view, Status.userState.getPosition().getX() + dx, Status.userState.getPosition().getY() + dy);
    }

    /**
     * Display user's health above the map
     */
    public void displayHealth() {
        Status.terminal.writeCenter("HEALTH: " + Status.userState.getHealth(), Constants.HEALTH_Y);
    }
}
