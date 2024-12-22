
import java.awt.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PC
 */
public class Line extends Shape {
    private final Point start;
    private final Point end;

    public Line(Point start, Point end, Color color) {
        super(color, false); // Line is never filled
        this.start = start;
        this.end = end;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(start.x, start.y, end.x, end.y);
    }
}

