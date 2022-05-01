package ru.hse.sd.roguegue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hse.sd.roguegue.preprocessing.Menu;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TestMenu {
    @BeforeEach
    public void init() {
        Status.init();
    }

    @Test
    public void testMenuStart() {
        assert Status.gameStatus == GameStatus.MENU;
        Menu menu = new Menu();
        menu.handleAction(new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_ENTER, '\n'));
        assert Status.gameStatus == GameStatus.GAME;
    }

    @Test
    public void testMenuQuit() {
        assert Status.gameStatus == GameStatus.MENU;
        Menu menu = new Menu();
        menu.handleAction(new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_Q, 'q'));
        assert Status.gameStatus == GameStatus.EXIT;
    }

    @Test
    public void testMenuInvalidAction() {
        assert Status.gameStatus == GameStatus.MENU;
        Menu menu = new Menu();
        menu.handleAction(new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_A, 'a'));
        assert Status.gameStatus == GameStatus.MENU;
    }
}
