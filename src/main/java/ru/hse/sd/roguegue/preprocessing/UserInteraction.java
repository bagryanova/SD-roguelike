package ru.hse.sd.roguegue.preprocessing;

import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;

public class UserInteraction extends JFrame implements KeyListener {
    private final Queue<KeyEvent> inputQueue = new LinkedList<>();
    private final Menu menu = new Menu();
    private final Handler handler = new Handler();
    //private AsciiPanel terminal = new AsciiPanel(500, 500);

    public UserInteraction() {
        //super.add(terminal);
        super.addKeyListener(this);
        //super.setSize(600, 600);
        super.setVisible(true);
        //super.repaint();
    }

    /**
     * Get user's action and redirect it on the next layer
     */
    public synchronized void getUserAction() {
        KeyEvent event = inputQueue.poll();
        if (event == null) {
            return;
        }
        redirectUserAction(event);
    }

    private void redirectUserAction(KeyEvent event) {
        if (Status.gameStatus == GameStatus.MENU) {
            menu.handleAction(event);
        } else if (Status.gameStatus == GameStatus.GAME) {
            handler.setUserAction(event);
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