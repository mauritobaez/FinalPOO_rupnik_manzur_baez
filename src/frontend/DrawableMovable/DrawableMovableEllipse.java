package frontend.DrawableMovable;

import backend.model.movables.MovableEllipse;
import backend.model.movables.MovablePoint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableMovableEllipse extends MovableEllipse implements DrawableMovableFigure{

    private Color fillColor;
    private Color strokeColor;
    private double strokeWidth;

    public DrawableMovableEllipse(MovablePoint centerPoint, double sMayorAxis, double sMinorAxis) {
        super(centerPoint, sMayorAxis, sMinorAxis);
    }

    @Override
    public void drawFigure(GraphicsContext gc) {
        drawOval(gc, getCenterPoint(), getsXAxis(), getsYAxis());
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
