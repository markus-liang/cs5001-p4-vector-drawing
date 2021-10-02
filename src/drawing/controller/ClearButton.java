package drawing.controller;

import drawing.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearButton extends MyButton {
    public ClearButton(IDrawingModel model) {
        super(model);
    }

    public ClearButton(IDrawingModel model, String text) {
        super(model, text);
    }

    public void actionPerformed(ActionEvent e) {
        model.clearCanvas(); // call clear from model
    }
}
