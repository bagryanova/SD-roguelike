package ru.hse.sd.roguegue.ui.Commands;

import ru.hse.sd.roguegue.status.Status;

/**
 * Class for outsourcing displaying information about user
 */
public class DisplayInformationCommand implements UICommand {
    @Override
    public void execute() {
        Status.userState.userUI.displayInformation();
    }
}
