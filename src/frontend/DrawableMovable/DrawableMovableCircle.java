package frontend.DrawableMovable;

import backend.model.movables.MovableCircle;
import backend.model.movables.MovablePoint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableMovableCircle extends MovableCircle implements DrawableMovableFigure {

    private Color fillColor;
    private Color strokeColor;
    private double strokeWidth;

    public DrawableMovableCircle(MovablePoint centerPoint, double radius) {
        super(centerPoint, radius);
    }

    @Override
    public void drawFigure(GraphicsContext gc) {
        drawOval(gc, getCenterPoint(), getRadius(), getRadius());
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
}
