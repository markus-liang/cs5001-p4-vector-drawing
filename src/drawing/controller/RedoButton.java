package drawing.controller;

import drawing.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedoButton extends MyButton {
    public RedoButton(IDrawingModel model) {
        super(model);
    }

    public RedoButton(IDrawingModel model, String text) {
        super(model, text);
    }

    public void actionPerformed(ActionEvent e) {
        model.redo(); // call redo from model
    }
}
