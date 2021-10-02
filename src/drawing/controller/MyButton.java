package drawing.controller;

import drawing.model.*;
import javax.swing.*;
import java.awt.event.ActionListener;

// this is  the parent class for all other button class in this application
public abstract class MyButton extends JButton implements ActionListener {
    public IDrawingModel model;

    public MyButton(IDrawingModel model) {
        super();
        this.addActionListener(this);
        this.model = model;
        this.setFocusable(false); // to make sure everytime the button is clicked, then the focus is still on the frame where the key listener works
    }

    public MyButton(IDrawingModel model, String text) {
        super(text);
        this.addActionListener(this);
        this.model = model;
        this.setFocusable(false);
    }
}
