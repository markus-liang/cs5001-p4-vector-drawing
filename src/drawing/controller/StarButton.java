package drawing.controller;

import drawing.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StarButton extends MyButton {
    public StarButton(IDrawingModel model) {
        super(model);
    }

    public StarButton(IDrawingModel model, String text) {
        super(model, text);
    }

    public void actionPerformed(ActionEvent e) {
        model.modeStar(); // change the mode in model
    }
}
