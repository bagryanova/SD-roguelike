package ru.hse.sd.roguegue.UI.Commands;

import ru.hse.sd.roguegue.status.Status;

public class DisplayInformationCommand implements UICommand {
    @Override
    public void execute() {
        Status.userState.userUI.displayInformation();
    }
}
