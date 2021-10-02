package drawing.controller;

import drawing.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EllipseButton extends MyButton {
    public EllipseButton(IDrawingModel model) {
        super(model);
    }

    public EllipseButton(IDrawingModel model, String text) {
        super(model, text);
    }

    public void actionPerformed(ActionEvent e) {
        model.modeEllipse(); // change the mode in model
    }
}
