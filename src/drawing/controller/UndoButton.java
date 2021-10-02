package drawing.controller;

import drawing.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoButton extends MyButton {
    public UndoButton(IDrawingModel model) {
        super(model);
    }

    public UndoButton(IDrawingModel model, String text) {
        super(model, text);
    }

    public void actionPerformed(ActionEvent e) {
        model.undo(); // call undo from model
    }
}
