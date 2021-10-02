package drawing.model.tests;

import drawing.model.*;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Before;
import org.junit.Test;
import java.awt.geom.*;
import java.awt.*;

public class MyShapeTest {

    private MyShape shape;
    
    @Test
    public void testConstructorOneParameterAndGetShape() {
        System.out.println("testing constructor 1 parameter and get shape");

        this.shape = new MyShape(MyShape.ShapeType.ELLIPSE);
        assertEquals(Ellipse2D.Double.class, this.shape.getShape().getClass());
    }

    @Test
    public void testConstructorOneParameterAndGetColor() {
        System.out.println("testing constructor 1 parameter and get color");

        this.shape = new MyShape(MyShape.ShapeType.RECTANGLE);
        assertEquals(Color.BLACK, this.shape.getColor());
    }

    @Test
    public void testConstructorTwoParametersAndGetColor() {
        System.out.println("testing constructor 2 parameters and get color");

        this.shape = new MyShape(MyShape.ShapeType.HEXAGON, Color.RED);
        assertEquals(Color.RED, this.shape.getColor());
    }

    @Test
    public void testConstructorThreeParametersAndGetShape() {
        System.out.println("testing constructor 3 parameters and get shape");

        this.shape = new MyShape(MyShape.ShapeType.TRIANGLE, Color.BLUE, new Point(100, 100));
        assertEquals(Polygon.class, this.shape.getShape().getClass());
    }

    @Test
    public void testConstructorFiveParametersAndGetShape() {
        System.out.println("testing constructor 5 parameters and get shape");

        this.shape = new MyShape(MyShape.ShapeType.STAR, Color.BLUE, new Point(100, 100), new Point(200, 200), true);
        assertEquals(Polygon.class, this.shape.getShape().getClass());
    }
}