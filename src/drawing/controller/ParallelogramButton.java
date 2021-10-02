package drawing.controller;

import drawing.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParallelogramButton extends MyButton {
    public ParallelogramButton(IDrawingModel model) {
        super(model);
    }

    public ParallelogramButton(IDrawingModel model, String text) {
        super(model, text);
    }

    public void actionPerformed(ActionEvent e) {
        model.modeParallelogram(); // change the mode in model
    }
}
