package frontend.drawablemovable;

import backend.model.Point;
import backend.model.movables.MovableFigure;
import backend.model.movables.MovablePoint;
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
    boolean isContained(Point topLeft,Point bottomRight);
    default boolean isContainedRect(Point topLeft,Point bottomRight,Point topLeftOfFigure,Point bottomRightOfFigure){
        return (Double.compare(topLeftOfFigure.getX(),topLeft.getX())>=0 && Double.compare(topLeftOfFigure.getY(),topLeft.getY())>=0)
                &&(Double.compare(bottomRightOfFigure.getX(),bottomRight.getX())<=0&&Double.compare(bottomRightOfFigure.getY(),bottomRight.getY())<=0);
    }
    default boolean isContainedOval(Point topLeft,Point bottomRight,Point centerPoint,double sXAxis,double sYAxis){
        return (Double.compare(centerPoint.getX()-sXAxis,topLeft.getX())>=0&&Double.compare(centerPoint.getY()-sYAxis,topLeft.getY())>=0)&&
                (Double.compare(centerPoint.getX()+sXAxis,bottomRight.getX())<=0 && Double.compare(centerPoint.getY()+sYAxis,bottomRight.getY())<=0);
    }
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
