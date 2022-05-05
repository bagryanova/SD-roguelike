package ru.hse.sd.roguegue.UI.Commands;

import ru.hse.sd.roguegue.status.Status;

public class DisplayScreenCommand implements UICommand {
    @Override
    public void execute() {
        Status.terminal.clear();
        Status.screen.display();
    }
}
