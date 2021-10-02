package drawing.controller;

import drawing.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TriangleButton extends MyButton {
    public TriangleButton(IDrawingModel model) {
        super(model);
    }

    public TriangleButton(IDrawingModel model, String text) {
        super(model, text);
    }

    public void actionPerformed(ActionEvent e) {
        model.modeTriangle(); // change the mode in model
    }
}
