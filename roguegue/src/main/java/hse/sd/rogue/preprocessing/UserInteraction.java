package hse.sd.rogue.preprocessing;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;

import hse.sd.rogue.preprocessing.Handler;
import hse.sd.rogue.preprocessing.Menu;
import hse.sd.rogue.status.GameStatus;
import hse.sd.rogue.status.Status;

public class UserInteraction extends JFrame implements KeyListener {
    private final Queue<KeyEvent> inputQueue = new LinkedList<>();
    private Menu menu = new Menu();
    private Handler handler = new Handler();
    //private AsciiPanel terminal = new AsciiPanel(500, 500);

    public UserInteraction() {
        //super.add(terminal);
        super.addKeyListener(this);
        //super.setSize(600, 600);
        super.setVisible(true);
        //super.repaint();
    }

    public synchronized void getUserAction() {
        KeyEvent event = inputQueue.poll();
        if (event == null) {
            return;
        }
        redirectUserAction(event);
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
