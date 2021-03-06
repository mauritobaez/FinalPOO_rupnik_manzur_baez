package frontend.drawablemovable;

import backend.model.Point;
import backend.model.movables.MovableFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//Esta interfaz básicamente actúa como simuladora de herencia múltiple pues las clases que la implementan
//ya están extendiendo a sus contrapartes Movable
public interface DrawableMovableFigure extends MovableFigure
{
    //Recibe los puntos que forman un rectángulo imaginario, y devuelve si la figura está incluída en el interior del mismo
    boolean isContained(Point topLeft,Point bottomRight);
    //Dibuja la figura en el graphicsContext
    void drawFigure(GraphicsContext gc);

    //Al ser una interfaz no podemos pedirle que las clases hijas tengan estas propiedades,
    //pero sí podemos simular esto pidiendoles que tengan implementado un getter y un setter para las mismas.
    Color getFillColor();
    void setFillColor(Color fillColor);
    Color getStrokeColor();
    void setStrokeColor(Color strokeColor);
    double getStrokeWidth();
    void setStrokeWidth(double width);


    //Métodos para no repetir código en las clases que implementan a esta interfaz
    default boolean isContainedRect(Point topLeft, Point bottomRight, Point topLeftOfFigure, Point bottomRightOfFigure)
    {
        return (topLeft.getXDifferenceTo(topLeftOfFigure) > 0 && topLeft.getYDifferenceTo(topLeftOfFigure) > 0 && bottomRight.getXDifferenceTo(bottomRightOfFigure) < 0 && bottomRight.getYDifferenceTo(bottomRightOfFigure) < 0);
    }
    default boolean isContainedOval(Point topLeft, Point bottomRight, Point centerPoint, double sXAxis, double sYAxis)
    {
        return (topLeft.getXDifferenceTo(centerPoint) > sXAxis && topLeft.getYDifferenceTo(centerPoint) > sYAxis && centerPoint.getXDifferenceTo(bottomRight) > sXAxis && centerPoint.getYDifferenceTo(bottomRight) > sYAxis );
    }
    default boolean isContainedLine(Point topLeft, Point bottomRight, Point startingPoint, Point endingPoint)
    {
        return (topLeft.getXDifferenceTo(startingPoint) > 0 && startingPoint.getXDifferenceTo(bottomRight) > 0 && topLeft.getYDifferenceTo(startingPoint) > 0 && startingPoint.getYDifferenceTo(bottomRight) > 0 && topLeft.getXDifferenceTo(endingPoint) > 0 && endingPoint.getXDifferenceTo(bottomRight) > 0 && topLeft.getYDifferenceTo(endingPoint) > 0 && endingPoint.getYDifferenceTo(bottomRight) > 0);
    }


    //Métodos para no repetir código en las clases que implementan a esta interfaz
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
