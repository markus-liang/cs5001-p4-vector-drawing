package drawing.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;
import javax.swing.*;
import java.awt.*;
import drawing.controller.*;
import drawing.model.*;

public class DrawingView implements Observer {
    private IDrawingModel model;

    private MyFrame mainFrame;
    private JMenuBar menuBar;
    private JMenu file;
    private MyButton clearButton;
    private MyMenuItem load;
    private MyMenuItem save;
    private JToolBar toolbar;
    // All these MyButton classes will be instantiate as specific buttons (polymorph)
    private MyButton lineButton;
    private MyButton ellipseButton;
    private MyButton hexagonButton;
    private MyButton parallelogramButton;
    private MyButton rectangleButton;
    private MyButton starButton;
    private MyButton triangleButton;
    private MyButton undoButton;
    private MyButton redoButton;
    private MyButton colorButton;
    private MyCanvas canvasPanel;
    private Hashtable <MyShape.ShapeType, MyButton> modeButtons;
    private MyButton lastMode;

    public DrawingView(IDrawingModel model) {
        this.model = model;

        // create frame for view of sheep and control buttons
        mainFrame = new MyFrame(model, "My Drawing Canvas");

        // create menu bar
        menuBar = new JMenuBar();
        addMenubarElements();
        mainFrame.setJMenuBar(menuBar);

        // create toolbar
        toolbar = new JToolBar();
        toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        addToolbarElements();
        mainFrame.add(toolbar, BorderLayout.NORTH);

        // drawing area
        canvasPanel = new MyCanvas(this.model); 
        mainFrame.getContentPane().add(canvasPanel, BorderLayout.CENTER);

        // set display and show the frame
        setActiveMode(lineButton);

        // Add the GUI as a listener for the GUI buttons and observer of model updates
        ((Observable) model).addObserver(this);
    }

    private void addMenubarElements() {
        file = new JMenu ("File");
        load = new LoadMenuItem (model, "Load");
        save = new SaveMenuItem (model, "Save");
        file.add (load);
        file.add (save);
        menuBar.add (file);
    }

    private void addToolbarElements() {
        clearButton = new ClearButton(model, "Clear");
        ellipseButton = new EllipseButton(model, "Ellipse");
        hexagonButton = new HexagonButton(model, "Hexagon");
        lineButton = new LineButton(model, "Line");
        parallelogramButton = new ParallelogramButton(model, "Parallelogram");
        rectangleButton = new RectangleButton(model, "Rectangle");
        starButton = new StarButton(model, "Star");
        triangleButton = new TriangleButton(model, "Triangle");
        undoButton = new UndoButton(model, "Undo");
        redoButton = new RedoButton(model, "Redo");
        colorButton = new ColorButton(model, "Color");
        colorButton.setFont(new Font(colorButton.getFont().getName(), Font.BOLD, colorButton.getFont().getSize()));

        toolbar.add(clearButton);
        toolbar.add(undoButton);
        toolbar.add(redoButton);
        toolbar.add(new JSeparator(SwingConstants.VERTICAL)); // grouping the buttons on the toolbar
        toolbar.add(colorButton);
        toolbar.add(new JSeparator(SwingConstants.VERTICAL)); // grouping the buttons on the toolbar
        toolbar.add(ellipseButton);
        toolbar.add(hexagonButton);
        toolbar.add(lineButton);
        toolbar.add(parallelogramButton);
        toolbar.add(rectangleButton);
        toolbar.add(starButton);
        toolbar.add(triangleButton);

        // to make it easier when changing button style on mode change
        modeButtons = new Hashtable<MyShape.ShapeType, MyButton>();
        modeButtons.put(MyShape.ShapeType.ELLIPSE, ellipseButton);
        modeButtons.put(MyShape.ShapeType.HEXAGON, hexagonButton);
        modeButtons.put(MyShape.ShapeType.LINE, lineButton);
        modeButtons.put(MyShape.ShapeType.PARALLELOGRAM, parallelogramButton);
        modeButtons.put(MyShape.ShapeType.RECTANGLE, rectangleButton);
        modeButtons.put(MyShape.ShapeType.STAR, starButton);
        modeButtons.put(MyShape.ShapeType.TRIANGLE, triangleButton);
    }

    // to bold the text in shape button, indicating the active mode
    private void setActiveMode(MyButton button) {
        if(lastMode != null) {
            lastMode.setFont(new Font(lastMode.getFont().getName(), Font.PLAIN, lastMode.getFont().getSize()));
        }
        button.setFont(new Font(button.getFont().getName(), Font.BOLD, button.getFont().getSize()));
        lastMode = button;
    }

    public void update(Observable arg0, Object arg1) {
        SwingUtilities.invokeLater(
            new Runnable() {
                public void run() {
                    // update current color
                    Color color = model.getColor();
                    colorButton.setForeground(color);

                    // bold the shape button
                    MyShape.ShapeType mode = model.getMode();
                    setActiveMode(modeButtons.get(mode));
                    System.out.println("Mode = " + mode);

                    // update canvas state
                    Stack<MyShape> images = model.getImages();
                    mainFrame.remove(canvasPanel);
                    canvasPanel = new MyCanvas(model, images);
                    mainFrame.add(canvasPanel);
                    mainFrame.revalidate();
                }
            });
    }
}
