package ru.hse.sd.roguegue.ui.screens;

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
