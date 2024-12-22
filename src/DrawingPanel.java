
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PC
 */
class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {

    private final ArrayList<Shape> shapes = new ArrayList<>();
    private Shape previewShape = null;
    private Point startPoint;
    private String currentTool = "Freehand";
    private Color currentColor = Color.BLACK;
    private Point lastPoint;
    private boolean isFilled = false;
    private final int ERASER_SIZE = 20;

    public DrawingPanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void setFill(boolean isFilled) {
        this.isFilled = isFilled;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setTool(String tool) {
        this.currentTool = tool;
    }

    public void setColor(Color color) {
        this.currentColor = color;
    }

    public void clear() {
        shapes.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Shape shape : shapes) {
            shape.draw(g);
        }
        if (previewShape != null) {
            previewShape.draw(g);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        lastPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (currentTool.equals("Freehand")) {

        } else if (previewShape != null) {
            shapes.add(previewShape);
            previewShape = null;
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point endPoint = e.getPoint();

        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int width = Math.abs(endPoint.x - startPoint.x);
        int height = Math.abs(endPoint.y - startPoint.y);
        if (currentTool.equals("Eraser")) {
            shapes.add(new Rectangle(
                    new Point(endPoint.x - ERASER_SIZE / 2, endPoint.y - ERASER_SIZE / 2),
                    ERASER_SIZE, ERASER_SIZE, getBackground(), true
            ));
        }

        switch (currentTool) {
            case "Eraser":
                Graphics g = getGraphics();
                g.setColor(getBackground());
                g.fillRect(endPoint.x - ERASER_SIZE / 2, endPoint.y - ERASER_SIZE / 2, ERASER_SIZE, ERASER_SIZE);
                break;
            case "Line":
                previewShape = new Line(startPoint, endPoint, currentColor);
                break;
            case "Rectangle":
                previewShape = new Rectangle(new Point(x, y), width, height, currentColor, isFilled);
                break;
            case "Triangle":
                Point thirdPoint = new Point((startPoint.x + endPoint.x) / 2, startPoint.y);
                previewShape = new Triangle(startPoint, endPoint, thirdPoint, currentColor, isFilled);
                break;
            case "Oval":
                previewShape = new Oval(new Point(x, y), width, height, currentColor, isFilled);
                break;

            case "Freehand":
                Line segment = new Line(lastPoint, endPoint, currentColor);
                shapes.add(segment);
                lastPoint = endPoint;
                break;
        }

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
