package drawing.controller;

import drawing.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RectangleButton extends MyButton {

    public RectangleButton(IDrawingModel model) {
        super(model);
    }

    public RectangleButton(IDrawingModel model, String text) {
        super(model, text);
    }

    public void actionPerformed(ActionEvent e) {
        model.modeRectangle();
    }
}
