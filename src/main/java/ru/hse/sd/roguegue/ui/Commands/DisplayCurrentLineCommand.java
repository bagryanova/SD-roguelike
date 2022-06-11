package ru.hse.sd.roguegue.ui.Commands;

import ru.hse.sd.roguegue.status.Status;

/**
 * Class for outsourcing cursor's displaying  in the inventory list
 */
public class DisplayCurrentLineCommand implements UICommand {
    private int prevLine;
    private int curLine;

    private DisplayCurrentLineCommand() {}

    public DisplayCurrentLineCommand(int prevLine, int curLine) {
        this.prevLine = prevLine;
        this.curLine = curLine;
    }

    @Override
    public void execute() {
        Status.inventoryUI.displayCurrentLine(prevLine, curLine);
    }
}
