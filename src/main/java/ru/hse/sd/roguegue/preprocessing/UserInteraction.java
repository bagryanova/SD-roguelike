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

    public UserInteraction() {
        super();
        add(Status.terminal);
        //super.addKeyListener(this);
        //super.setSize(600, 600);
        //Status.terminal.setDefaultForegroundColor(Color.green);
        pack();
        addKeyListener(this);
        setVisible(true);
        Status.terminal.clear();
        Status.screen.display();
        super.repaint();
    }

    public synchronized void getUserAction() {
        KeyEvent event = inputQueue.poll();
        if (event == null) {
            return;
        }
        redirectUserAction(event);
        super.repaint();
    }

    void redirectUserAction(KeyEvent event) {
        if (Status.gameStatus == GameStatus.MENU) {
            menu.handleAction(event);
        } else if (Status.gameStatus == GameStatus.GAME) {
            handler.setUserAction(event);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    synchronized public void keyPressed(KeyEvent e) {
        inputQueue.add(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}