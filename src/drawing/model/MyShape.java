package drawing.model;

import java.io.Serializable;
import java.util.Stack;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;

public class MyShape implements Serializable {
    public static enum ShapeType {
        ELLIPSE,
        HEXAGON,
        LINE,
        PARALLELOGRAM,
        RECTANGLE,
        STAR,
        TRIANGLE
    }

    private Shape shape;
    private Color color;
    private Point point1; // coordinate 1
    private Point point2; // coordinate 2
    private boolean isLockRatio;

    public MyShape(ShapeType type) {
        this.color = Color.BLACK;
        this.isLockRatio = false;
        this.point1 = new  Point(0, 0); // default point
        this.point2 = new  Point(200, 100); // default point
        this.shape = createShape(type);
    }

    public MyShape(ShapeType type, Color color) {
        this.color = color;
        this.isLockRatio = false;
        this.point1 = new  Point(0, 0); // default point
        this.point2 = new  Point(200, 100); // default point
        this.shape = createShape(type);
    }

    public MyShape(ShapeType type, Color color, Point p1) {
        this.color = color;
        this.isLockRatio = false;
        this.point1 = p1;
        this.point2 = new  Point((int)p1.getX() + 200, (int)p1.getY() + 100);
        this.shape = createShape(type);
    }

    public MyShape(ShapeType type, Color color, Point p1, Point p2, boolean isLockRatio) {
        // make sure that the first point is on upper left, except for line
        if (type == ShapeType.LINE) {
            this.point1 = p1;
            this.point2 = p2;
        } else {
            double x1;
            double x2;
            double y1;
            double y2;

            x1 = (p1.getX() < p2.getX() ? p1.getX() : p2.getX());
            x2 = (p1.getX() >= p2.getX() ? p1.getX() : p2.getX());
            y1 = (p1.getY() < p2.getY() ? p1.getY() : p2.getY());
            y2 = (p1.getY() >= p2.getY() ? p1.getY() : p2.getY());

            this.point1 = new Point((int)x1, (int)y1);
            this.point2 = new Point((int)x2, (int)y2);
        }

        this.isLockRatio = isLockRatio;
        this.color = color;
        this.shape = createShape(type);
    }

    /* Private */
    private Shape createShape(ShapeType type) {
        Shape image = null;
        double size = getWidth() > getHeight() ? getWidth() : getHeight(); // if lock ratio, then use this size..  the bigger value of width and height
        int width = (int)getWidth();
        int height = (int)getHeight();
        int xStart = (int)this.point1.getX();
        int yStart = (int)this.point1.getY();
        int x[];
        int y[];

        switch (type) {
            case ELLIPSE:
                if (isLockRatio) { // ellipse + locked ration : circle
                    image = new Ellipse2D.Double(this.point1.getX(), this.point1.getY(), size, size);
                } else {
                    image = new Ellipse2D.Double(this.point1.getX(), this.point1.getY(), getWidth(), getHeight());
                }
                break;
            case HEXAGON:
                if(isLockRatio) {
                    width = (int)size;
                    height = (int)size;
                }

                // the point locations are decided arbitary to make sure the hexagon shape looks good 
                x = new int[] {xStart, xStart + (width / 4), xStart + (width * 3 / 4), xStart + width, xStart + (width * 3 / 4), xStart + (width / 4)};
                y = new int[] {yStart + (height / 2), yStart, yStart, yStart + (height / 2), yStart + height, yStart + height};
                image = new Polygon(x, y, x.length);
                break;
            case LINE: 
                image = new Line2D.Double(this.point1.getX(), this.point1.getY(), this.point2.getX(), this.point2.getY());
                break;
            case PARALLELOGRAM:
                if(isLockRatio) {
                    width = (int)size;
                    height = (int)size;
                }

                // the point locations are decided arbitary to make sure the parallelogram shape looks good 
                x = new int[] {xStart + (width / 4), xStart + width, xStart + (width * 3 / 4), xStart};
                y = new int[] {yStart, yStart, yStart + height, yStart + height};
                image = new Polygon(x, y, x.length);
                break;
            case RECTANGLE:
                if (isLockRatio) { // rectangle + locked ration : square
                    image = new Rectangle2D.Double(this.point1.getX(), this.point1.getY(), size, size);
                } else {
                    image = new Rectangle2D.Double(this.point1.getX(), this.point1.getY(), getWidth(), getHeight());
                }
                break;
            case STAR:
                if(isLockRatio) { // star + locked ration : star with equal width and height
                    width = (int)size;
                    height = (int)size;
                }

                // the point locations are decided arbitary to make sure the star shape looks good 
                x = new int[] {xStart + (width / 2), xStart + (width * 7 / 8), xStart, xStart + width, xStart + (width / 8)};
                y = new int[] {yStart, yStart + height, yStart + (height / 3), yStart + (height / 3), yStart + height};
                image = new Polygon(x, y, x.length);
                break;
            case TRIANGLE:
                if(isLockRatio) { // triangle + locked ration : star with equal height and bottom side
                    width = (int)size;
                    height = (int)size;
                }

                x = new int[] {xStart, xStart + (width /2), xStart + width};
                y = new int[] {yStart + height, yStart, yStart + height};

                image = new Polygon(x, y, x.length);
                break;              
        }
        return image;
    }

    private double getWidth() {
        return this.point2.getX() - this.point1.getX();
    }

    private double getHeight() {
        return this.point2.getY() - this.point1.getY();
    }

    /* Public */
    public Shape getShape() {
        return this.shape;
    }

    public Color getColor() {
        return this.color;
    }
}
