package frontend.drawablemovable;

import backend.model.Point;
import backend.model.movables.MovablePoint;
import backend.model.movables.MovableRectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableMovableRectangle extends MovableRectangle implements DrawableMovableFigure
{

    private Color fillColor;
    private Color strokeColor;
    private double strokeWidth;

    public DrawableMovableRectangle(MovablePoint topLeft, MovablePoint bottomRight)
    {
        super(topLeft, bottomRight);
    }

    @Override
    public void drawFigure(GraphicsContext gc)
    {
        drawRect(gc, getTopLeft(), getBottomRight());
    }

    @Override
    public Color getFillColor()
    {
        return fillColor;
    }

    @Override
    public void setFillColor(Color fillColor)
    {
        this.fillColor = fillColor;
    }

    @Override
    public Color getStrokeColor()
    {
        return strokeColor;
    }

    @Override
    public void setStrokeColor(Color strokeColor)
    {
        this.strokeColor = strokeColor;
    }

    @Override
    public double getStrokeWidth()
    {
        return strokeWidth;
    }

    @Override
    public void setStrokeWidth(double width)
    {
        this.strokeWidth = width;
    }

    @Override
    public boolean isContained(Point topLeft, Point bottomRight)
    {
       return isContainedRect(topLeft,bottomRight,getTopLeft(),getBottomRight());
    }
}
