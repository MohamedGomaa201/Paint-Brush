
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eng Mohamed
 */
public class Triangle extends Shape {
    private final Point point1;
    private final Point point2;
    private final Point point3;

    public Triangle(Point point1, Point point2, Point point3, Color color, boolean isFilled) {
        super(color, isFilled);
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        int[] xPoints = {point1.x, point2.x, point3.x};
        int[] yPoints = {point1.y, point2.y, point3.y};
        if (isFilled) {
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            g.drawPolygon(xPoints, yPoints, 3);
        }
    }
}
