
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
 * @author PC
 */
public class Rectangle extends Shape {

    private final Point topLeft;
    private final int width, height;

    public Rectangle(Point topLeft, int width, int height, Color color, boolean isFilled) {
        super(color, isFilled);
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (isFilled) {
            g.fillRect(topLeft.x, topLeft.y, width, height);
        } else {
            g.drawRect(topLeft.x, topLeft.y, width, height);
        }
    }
}
