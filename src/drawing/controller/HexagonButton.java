package drawing.controller;

import drawing.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HexagonButton extends MyButton {
    public HexagonButton(IDrawingModel model) {
        super(model);
    }

    public HexagonButton(IDrawingModel model, String text) {
        super(model, text);
    }

    public void actionPerformed(ActionEvent e) {
        model.modeHexagon(); // change the mode in model
    }
}
