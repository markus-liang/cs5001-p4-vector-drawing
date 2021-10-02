package drawing.controller;

import drawing.model.*;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JFrame;

// MyFrame is a JFrame with keyboard handler
public class MyFrame extends JFrame implements KeyListener {
    private static int DEFAULT_FRAME_WIDTH = 800;
    private static int DEFAULT_FRAME_HEIGHT = 600;

    public IDrawingModel model;

    public MyFrame(IDrawingModel model) {
        super();
        addKeyListener(this);
        this.model = model;
        setFrame();
    }

    public MyFrame(IDrawingModel model, String text) {
        super(text);
        addKeyListener(this);
        this.model = model;
        setFrame();
    }

    private void setFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT); // set frame size
        setVisible(true); // display frame
    }

    // Keyboard event handlers
    public void keyTyped(KeyEvent e) { }

    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed : " + e.getKeyCode());
        if(e.getKeyCode() == 17) { // control key
            this.model.lockRatio(true);
        }
    }

    public void keyReleased(KeyEvent e) {
        System.out.println("Key Release : " + e.getKeyCode());
        if(e.getKeyCode() == 17) { // control key
            this.model.lockRatio(false);
        }
    }   
}