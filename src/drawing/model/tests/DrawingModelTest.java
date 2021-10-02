package drawing.model.tests;

import drawing.model.*;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Before;
import org.junit.Test;
import java.awt.*;
import java.awt.geom.*;
import java.io.IOException;
import java.util.Stack;

public class DrawingModelTest {

    private IDrawingModel model;

    @BeforeClass
    public static void init() {
        System.out.println("Start DrawingModel Test ...");
    }

    @Before
    public void setup() {
        this.model = new DrawingModel();
    }
    
    @Test
    public void testChangeColor() {
        System.out.println("Check the default color and change it");

        // the default color should be black
        assertEquals(Color.BLACK, this.model.getColor());
        this.model.changeColor(Color.YELLOW);
        // check the new color
        assertEquals(Color.YELLOW, this.model.getColor());
    }

    @Test
    public void testDrawDefaultShapeAndGetImages() {
        System.out.println("Draw a shape with default mode, should be a line");

        this.model.drawShape(new Point(50, 50));
        Stack<MyShape> s = this.model.getImages();

        // the image count should be one
        assertEquals(1, this.model.getImages().size());
        // check the class of the shape
        assertEquals(Line2D.Double.class, s.pop().getShape().getClass());
    }

    @Test
    public void testGetAndChangeMode() {
        System.out.println("Get current mode and change it");
        
        // default mode
        assertEquals(MyShape.ShapeType.LINE, this.model.getMode());
        // change mode to rectangle
        this.model.modeRectangle();
        assertEquals(MyShape.ShapeType.RECTANGLE, this.model.getMode());
        // change mode star
        this.model.modeStar();
        assertEquals(MyShape.ShapeType.STAR, this.model.getMode());
        // change mode ellipse
        this.model.modeEllipse();
        assertEquals(MyShape.ShapeType.ELLIPSE, this.model.getMode());
        // change mode triangle
        this.model.modeTriangle();
        assertEquals(MyShape.ShapeType.TRIANGLE, this.model.getMode());
    }

    @Test
    public void testUndoRedo() {
        System.out.println("Draw a shape with default mode, should be a line");

        Stack<MyShape> s = this.model.getImages();

        // has no shape
        assertEquals(0, this.model.getImages().size());

        // shape 1 : LINE
        assertEquals(MyShape.ShapeType.LINE, this.model.getMode());
        this.model.drawShape(new Point(100, 100));
        assertEquals(1, this.model.getImages().size()); // 1 shapes in stack

        // shape 2: RECTANGLE
        this.model.modeRectangle();
        this.model.drawShape(new Point(100, 100));
        assertEquals(2, this.model.getImages().size()); // 2 shapes in stack
        assertEquals(MyShape.ShapeType.RECTANGLE, this.model.getMode());

        // shape 3: STAR
        this.model.modeStar();
        this.model.drawShape(new Point(100, 100));
        assertEquals(3, this.model.getImages().size()); // 3 shapes in stack
        assertEquals(MyShape.ShapeType.STAR, this.model.getMode());

        // undo STAR
        this.model.undo();
        assertEquals(2, this.model.getImages().size()); // 2 shapes left

        // undo RECTANGLE
        this.model.undo();
        assertEquals(1, this.model.getImages().size()); // 1 shapes left

        // redo RECTANGLE
        this.model.redo();
        assertEquals(2, this.model.getImages().size()); // back to 2 shapes left
        assertEquals(Rectangle2D.Double.class, this.model.getImages().pop().getShape().getClass()); // the last image should be RECTANGLE
        assertEquals(1, this.model.getImages().size()); // 1 shape left after pop
    }

    @Test
    public void testSaveAndLoad() {
        System.out.println("Draw a shape with default mode, should be a line");

        Stack<MyShape> s = this.model.getImages();

        // has no shape
        assertEquals(0, this.model.getImages().size());

        // shape 1 : LINE
        assertEquals(MyShape.ShapeType.LINE, this.model.getMode());
        this.model.drawShape(new Point(100, 100));
        assertEquals(1, this.model.getImages().size()); // 1 shapes in stack

        // shape 2: RECTANGLE
        this.model.modeRectangle();
        this.model.drawShape(new Point(100, 100));
        assertEquals(2, this.model.getImages().size()); // 2 shapes in stack
        assertEquals(MyShape.ShapeType.RECTANGLE, this.model.getMode());

        this.model.save("unit_test_save"); // save 2 iamges

        // delete all image
        this.model.clearCanvas();
        // there should be no image left now
        assertEquals(0, this.model.getImages().size());

        this.model.load("unit_test_save"); // load from the last saved file, success

        // there should be no image left now
        assertEquals(2, this.model.getImages().size()); // back to 2 shapes in stack
    }
}
