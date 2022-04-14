package ru.hse.sd.roguegue.preprocessing;

import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;

public class UserInteraction extends JFrame implements KeyListener {
    private final Queue<KeyEvent> inputQueue = new LinkedList<>();
    private final Menu menu = new Menu();
    private final Handler handler = new Handler();

    private final InventoryHandler inventoryHandler = new InventoryHandler();

    public UserInteraction() {
        super();
        add(Status.terminal);
        pack();
        addKeyListener(this);
        setVisible(true);
        Status.terminal.clear();
        Status.screen.display();
        super.repaint();
    }

    /**
     * Get user's action and redirect it on the next layer
     */
    public synchronized void getUserAction() {
        KeyEvent event = inputQueue.poll();
        redirectUserAction(event);
        super.repaint();
    }

    private void redirectUserAction(KeyEvent event) {
        if (Status.gameStatus == GameStatus.MENU) {
            menu.handleAction(event);
        } else if (Status.gameStatus == GameStatus.GAME) {
            handler.setUserAction(event);
        } else if (Status.gameStatus == GameStatus.INVENTORY) {
            inventoryHandler.setInventoryAction(event);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Put all user's action un queue
     */
    @Override
    synchronized public void keyPressed(KeyEvent e) {
        inputQueue.add(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}