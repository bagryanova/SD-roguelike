package ru.hse.sd.roguegue.ui.Commands;

import ru.hse.sd.roguegue.status.Status;

/**
 * Class for outsourcing screen's changing
 */
public class ChangeScreenCommand implements UICommand {
    @Override
    public void execute() {
        Status.screen = Status.screen.change();
        Status.terminal.clear();
        Status.screen.display();
    }
}
