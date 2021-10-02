package drawing.controller;

import drawing.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineButton extends MyButton {
    public LineButton(IDrawingModel model) {
        super(model);
    }

    public LineButton(IDrawingModel model, String text) {
        super(model, text);
    }

    public void actionPerformed(ActionEvent e) {
        model.modeLine(); // change the mode in model
    }
}
