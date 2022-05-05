package ru.hse.sd.roguegue.UI.Commands;

import ru.hse.sd.roguegue.status.Status;

public class ChangeScreenCommand implements UICommand {
    @Override
    public void execute() {
        Status.screen = Status.screen.change();
        Status.terminal.clear();
        Status.screen.display();
    }
}
