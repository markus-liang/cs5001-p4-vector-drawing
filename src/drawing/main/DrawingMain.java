package drawing.main;

import drawing.model.DrawingModel;
import drawing.model.IDrawingModel;
import drawing.view.DrawingView;

public class DrawingMain {
    public static void main(String[] args) {
        // Create Model
        IDrawingModel model = new DrawingModel();

        // Create View (GUI)
        new DrawingView(model);
    }
}
