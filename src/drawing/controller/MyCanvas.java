package drawing.controller;

import drawing.model.*;
import java.util.Iterator;
import java.util.Stack;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

// MyCanvas is JPanel with mouse listener
public class MyCanvas extends JPanel implements MouseListener {
    private IDrawingModel model;
    private Stack<MyShape> images;
    private String mode;
    private Point startPoint;

    public MyCanvas(IDrawingModel model) {
        this.model = model;
        this.images = new Stack<MyShape>();
        setupCanvas();
    }

    public MyCanvas(IDrawingModel model, Stack<MyShape> images) {
        this.model = model;
        this.images = images;
        setupCanvas();
    }

    private void setupCanvas() {
        this.mode = "line"; // default mode;
        setBackground(Color.WHITE);
        addMouseListener(this);
        setVisible(true);
    }

    // draw all image stack to the canvas
    public void paint (Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        Iterator it = images.iterator();
        while (it.hasNext()) {
            MyShape shape = (MyShape)it.next();
            if (shape.getShape().getClass() == Ellipse2D.Double.class) {
                Ellipse2D temp = (Ellipse2D) shape.getShape();
                g2D.setColor(shape.getColor());
                g2D.draw(temp);
            } else if (shape.getShape().getClass() == Line2D.Double.class) {
                Line2D temp = (Line2D) shape.getShape();
                g2D.setColor(shape.getColor());
                g2D.draw(temp);
            } else if (shape.getShape().getClass() == Rectangle2D.Double.class) {
                Rectangle2D temp = (Rectangle2D) shape.getShape();
                g2D.setColor(shape.getColor());
                g2D.draw(temp);
            } else if (shape.getShape().getClass() == Polygon.class) {
                Polygon temp = (Polygon) shape.getShape();
                g2D.setColor(shape.getColor());
                g2D.draw(temp);
            }           
        }
    }

    // on mouse click, draw image immediately with default size defined by the model
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked 1 " + e.getX() + " " + e.getY());
        model.drawShape(e.getPoint());
    }

    // on mouse pressed, keep the start point and wait until it has released then draw the image
    public void mousePressed(MouseEvent e) {
        this.startPoint = e.getPoint();
        System.out.println("Mouse pressed " + e.getX() + " " + e.getY());
    }

    // on mouse released, draw the image with position between start point (get on mouse pressed event) to released point
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse released " + e.getX() + " " + e.getY());
        model.drawShape(this.startPoint, e.getPoint());
    }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
}