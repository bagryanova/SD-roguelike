package ru.hse.sd.roguegue.ui.Commands;

import ru.hse.sd.roguegue.status.Status;

/**
 * Class for outsourcing screen's displaying
 */
public class DisplayScreenCommand implements UICommand {
    @Override
    public void execute() {
        Status.terminal.clear();
        Status.screen.display();
    }
}
