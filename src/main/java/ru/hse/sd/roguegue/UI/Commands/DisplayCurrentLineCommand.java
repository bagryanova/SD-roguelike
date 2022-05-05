package ru.hse.sd.roguegue.UI.Commands;

import ru.hse.sd.roguegue.status.Status;

public class DisplayCurrentLineCommand implements UICommand {
    private int prev, cur;

    private DisplayCurrentLineCommand() {}

    public DisplayCurrentLineCommand(int prev, int cur) {
        this.prev = prev;
        this.cur = cur;
    }

    @Override
    public void execute() {
        Status.inventoryUI.displayCurrentLine(prev, cur);
    }
}
