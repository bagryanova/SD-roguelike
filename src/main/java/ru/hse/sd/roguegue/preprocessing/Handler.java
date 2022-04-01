package ru.hse.sd.roguegue.preprocessing;

import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.logic.impl.UserAction;

import java.awt.event.KeyEvent;

public class Handler {
    private final UserAction userAction = new UserAction();

    private boolean validateAction(KeyEvent event) {
        return event.getKeyCode() == KeyEvent.VK_LEFT || event.getKeyCode() == KeyEvent.VK_RIGHT ||
                event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN;
    }

    public void setUserAction(KeyEvent event) {
        if (!validateAction(event)) {
            return;
        }
        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT ->
                userAction.updateState(Move.LEFT);
            case KeyEvent.VK_RIGHT ->
                userAction.updateState(Move.RIGHT);
            case KeyEvent.VK_DOWN ->
                userAction.updateState(Move.DOWN);
            case KeyEvent.VK_UP ->
                userAction.updateState(Move.UP);
        }
    }
}
