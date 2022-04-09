package ru.hse.sd.roguegue.UI.screens;

/**
 * Interface for screen
 */
public interface Screen {
    /**
     * Display something
     */
    void display();

    /**
     * @return this screen or new one if it's time to change
     */
    Screen change();
}
