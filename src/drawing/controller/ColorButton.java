package drawing.controller;

import drawing.model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ColorButton extends MyButton {
    public ColorButton(IDrawingModel model) {
        super(model);
    }

    public ColorButton(IDrawingModel model, String text) {
        super(model, text);
    }

    public void actionPerformed(ActionEvent e) {
        Color color = JColorChooser.showDialog(this, "Choose Color", Color.BLACK);
        model.changeColor(color); // change the default color in model
    }
}
