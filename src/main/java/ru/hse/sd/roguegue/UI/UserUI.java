package ru.hse.sd.roguegue.UI;

import ru.hse.sd.roguegue.Constants;
import ru.hse.sd.roguegue.status.Status;

public class UserUI extends GameObjectUI{
    private Character view = '!';

    public void displayPosition() {
        Status.terminal.write(view, Status.userState.getPosition().getX() + dx, Status.userState.getPosition().getY() + dy);
    }

    public void displayHealth() {
        Status.terminal.writeCenter("HEALTH: " + Status.userState.getHealth(), Constants.HEALTH_Y);
    }
}
