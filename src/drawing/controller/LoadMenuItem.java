package drawing.controller;

import drawing.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

public class LoadMenuItem extends MyMenuItem {
    public LoadMenuItem(IDrawingModel model) {
        super(model);
    }

    public LoadMenuItem(IDrawingModel model, String text) {
        super(model, text);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Load Menu Item");
        try {
            File f = new File(new File(".").getCanonicalPath());
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(f); // set the application folder as default, just for user friendliness
            int returnVal = fc.showOpenDialog(fc); // let the user specify the file location
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                this.model.load(fc.getSelectedFile().getName());
            } else {
                System.out.println("User cancel loading.");
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
}
