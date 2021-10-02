package drawing.model;

import java.util.Stack;
import javax.swing.*;
import java.awt.*;

public interface IDrawingModel {
    public abstract void changeColor(Color newColor);
    public abstract void clearCanvas();
    public abstract void drawShape(Point p1);
    public abstract void drawShape(Point p1, Point p2);
    public abstract Color getColor();
    public abstract Stack<MyShape> getImages();
    public abstract MyShape.ShapeType getMode();
    public abstract void load(String path);
    public abstract void modeEllipse();
    public abstract void modeHexagon();
    public abstract void modeLine();
    public abstract void modeParallelogram();
    public abstract void modeRectangle();
    public abstract void modeStar();
    public abstract void modeTriangle();
    public abstract void redo();
    public abstract void lockRatio(boolean value);
    public abstract void undo();
    public abstract void save(String path);
}
