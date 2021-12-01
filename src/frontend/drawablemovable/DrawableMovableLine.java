package frontend.drawablemovable;

import backend.model.Point;
import backend.model.movables.MovableLine;
import backend.model.movables.MovablePoint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// MovableLine
public class DrawableMovableLine extends MovableLine implements DrawableMovableFigure {

    private Color fillColor;
    private Color strokeColor;
    private double strokeWidth;

    public DrawableMovableLine(MovablePoint startingPoint, MovablePoint endingPoint) {
        super(startingPoint, endingPoint);
    }

    @Override
    public void drawFigure(GraphicsContext gc) {
        drawLine(gc, getStartingPoint(), getEndingPoint());
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public Color getStrokeColor() {
        return strokeColor;
    }

    @Override
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    @Override
    public double getStrokeWidth() {
        return strokeWidth;
    }

    @Override
    public void setStrokeWidth(double width) {
        this.strokeWidth = width;
    }

    @Override
    public boolean isContained(Point topLeft, Point bottomRight) {//No se si funca
    return isContainedRect(topLeft,bottomRight,getStartingPoint(),getEndingPoint())||isContainedRect(topLeft,bottomRight,getEndingPoint(),getStartingPoint());
    }
}
