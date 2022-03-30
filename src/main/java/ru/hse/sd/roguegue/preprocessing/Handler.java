package ru.hse.sd.roguegue.preprocessing;

import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.logic.impl.UserAction;

public class Handler {
    private final UserAction userAction = new UserAction();

    public void setUserAction() {
        userAction.updateState(Move.LEFT);
    }
}
