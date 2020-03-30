package drawingapp;

import java.awt.geom.Rectangle2D;

public class Rectangle extends Rectangle2D.Double {
    public Rectangle(double x0, double y0, double radius) {
        super(x0 - radius / 2, y0 - radius / 2, radius, radius);
    }
}
