package frontend.drawablemovable;

import backend.model.Point;
import backend.model.movables.MovableFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface DrawableMovableFigure extends MovableFigure {

    void drawFigure(GraphicsContext gc);
    Color getFillColor();
    void setFillColor(Color fillColor);
    Color getStrokeColor();
    void setStrokeColor(Color strokeColor);
    double getStrokeWidth();
    void setStrokeWidth(double width);

    default void drawRect(GraphicsContext gc, Point topLeft, Point bottomRight)
    {
        gc.fillRect(topLeft.getX(), topLeft.getY(), topLeft.getXDifferenceTo(bottomRight), topLeft.getYDifferenceTo(bottomRight));
        gc.strokeRect(topLeft.getX(), topLeft.getY(), topLeft.getXDifferenceTo(bottomRight), topLeft.getYDifferenceTo(bottomRight));
    }
    default void drawOval(GraphicsContext gc, Point centerPoint, double sXAxis, double sYAxis)
    {
        double XDiameter = sXAxis * 2;
        double YDiameter = sYAxis * 2;
        gc.fillOval(centerPoint.getX() - sXAxis, centerPoint.getY() - sYAxis, XDiameter, YDiameter);
        gc.strokeOval(centerPoint.getX() - sXAxis, centerPoint.getY() - sYAxis, XDiameter, YDiameter);
    }
    default void drawLine(GraphicsContext gc, Point startingPoint, Point endingPoint)
    {
        gc.strokeLine(startingPoint.getX(), startingPoint.getY(), endingPoint.getX(), endingPoint.getY());
    }
}
