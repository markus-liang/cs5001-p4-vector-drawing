package drawing.controller;

import drawing.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

// the save menu item handling save event
public class SaveMenuItem extends MyMenuItem {
    public SaveMenuItem(IDrawingModel model) {
        super(model);
    }

    public SaveMenuItem(IDrawingModel model, String text) {
        super(model, text);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Save Menu Item");

        try {
            File f = new File(new File(".").getCanonicalPath());
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(f); // set the application folder as default, just for user friendliness
            int returnVal = fc.showSaveDialog(fc); // let the user specify the file location

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                this.model.save(fc.getSelectedFile().getName());
            } else {
                System.out.println("User cancel saving.");
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
}
