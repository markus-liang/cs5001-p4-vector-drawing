package drawing.model;

import drawing.model.MyShape;
import java.io.Serializable;
import java.util.Observable;
import java.util.Stack;
import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class DrawingModel extends Observable implements IDrawingModel, Serializable {
    private Stack<MyShape> imageStack; // current stack of shapes
    private Stack<MyShape> bin; // for temporary redo action
    private Color color;
    private MyShape.ShapeType mode;
    private boolean isLockRatio;

    public DrawingModel() {
        this.imageStack = new Stack<MyShape>();
        this.mode = MyShape.ShapeType.LINE;
        this.isLockRatio = false; // for drawing circle or square;
        changeColor(Color.BLACK);
        clearState();
    }

    /* Private Section */
    private void clearState() {
        this.bin = new Stack<MyShape>();
        this.isLockRatio = false;
    }

    private void update() {
        this.setChanged();
        this.notifyObservers();
    }

    /* Public Section */
    public void changeColor(Color newColor) {
        System.out.println("Change color : " + newColor.toString());

        this.color = newColor;
        update();
    }

    public void clearCanvas() {
        this.imageStack = new Stack<MyShape>();
        clearState();
        update();
    }

    // draw the shape with only one input point (mouse click)
    public void drawShape(Point p1) {
        System.out.println("Draw Shape at : " + p1.toString());

        this.imageStack.push(new MyShape(this.mode, this.color, p1));
        clearState();
        update();
    }

    // draw the shape with two input points (mouse drag)
    public void drawShape(Point p1, Point p2) {
        System.out.println("Draw Shape at : " + p1.toString() + " to " + p2.toString());

        this.imageStack.push(new MyShape(this.mode, this.color, p1, p2, isLockRatio));
        clearState();
        update();
    }

    public Color getColor() {
        return this.color;
    }

    public Stack<MyShape> getImages() {
        return this.imageStack;
    }

    public MyShape.ShapeType getMode() {
        return this.mode;
    }

    public void load(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Stack<MyShape> loadedStack = (Stack<MyShape>) ois.readObject();
            ois.close();
            this.imageStack = loadedStack; // replace existing image stack with the new load stack
            clearState();
            update();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } catch (ClassNotFoundException cne) {
            System.err.println(cne.getMessage());
        }
    }

    public void modeEllipse() {
        this.mode = MyShape.ShapeType.ELLIPSE;
        update();
    }

    public void modeHexagon() {
        this.mode = MyShape.ShapeType.HEXAGON;
        update();
    }

    public void modeLine() {
        this.mode = MyShape.ShapeType.LINE;
        update();
    }

    public void modeParallelogram() {
        this.mode = MyShape.ShapeType.PARALLELOGRAM;
        update();
    }

    public void modeRectangle() {
        this.mode = MyShape.ShapeType.RECTANGLE;
        update();
    }

    public void modeStar() {
        this.mode = MyShape.ShapeType.STAR;
        update();
    }

    public void modeTriangle() {
        this.mode = MyShape.ShapeType.TRIANGLE;
        update();
    }

    public void redo() {
        Iterator it = imageStack.iterator();
        if (this.bin.size() > 0) { // if only there is minimum one image
            this.imageStack.push(this.bin.pop());
        }
        update();
    }

    public void lockRatio(boolean value){
        this.isLockRatio = value;
    }

    public void undo() {
        Iterator it = imageStack.iterator();
        if (this.imageStack.size() > 0) { // if only there is minimum one image
            this.bin.push(this.imageStack.pop());
        }
        update();
    }

    public void save(String path) {        
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.imageStack);
            oos.close();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
}
