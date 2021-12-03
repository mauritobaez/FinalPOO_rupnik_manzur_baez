package frontend.buttons;

import backend.model.Figure;
import backend.model.movables.MovablePoint;
import frontend.drawablemovable.DrawableMovableRectangle;
import frontend.drawablemovable.DrawableMovableSquare;

public class SquareButton extends FigureButton{
    public SquareButton(String name)
    {
        super(name,false);
    }

    @Override
    public Figure createFigure(MovablePoint startPoint, MovablePoint endPoint)
    {
        //Al crear un DrawableMovableSquare se deben pasar como parámetro dos puntos que formen un cuadrado, se maneja la excepción que puede enviar el constructo de Square
        try
        {
            return new DrawableMovableSquare(startPoint, new MovablePoint(endPoint.getX(), startPoint.getY() + startPoint.getXDifferenceTo(endPoint)));
        } catch (Exception exception)
        {
            System.out.println(exception.getMessage());
            return new DrawableMovableRectangle(startPoint, endPoint);
        }
    }

}
