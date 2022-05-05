package ru.hse.sd.roguegue.UI.Commands;

/**
 * Interface for UI commands.
 * They will be called in business logic components and outsource action in UI.
 */
public interface UICommand {

    /**
     * The main method for commands.
     * Should execute command
     */
    public void execute();
}
