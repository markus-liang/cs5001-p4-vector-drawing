package drawing.controller;

import drawing.model.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class MyMenuItem extends JMenuItem implements ActionListener {
    public IDrawingModel model;

    public MyMenuItem(IDrawingModel model) {
        super();
        this.addActionListener(this);
        this.model = model;
    }

    public MyMenuItem(IDrawingModel model, String text) {
        super(text);
        this.addActionListener(this);
        this.model = model;
    }
}
