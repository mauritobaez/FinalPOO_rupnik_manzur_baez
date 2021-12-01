package frontend.buttons;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Square;
import backend.model.movables.MovablePoint;
import frontend.DrawableMovable.DrawableMovableFigure;
import frontend.DrawableMovable.DrawableMovableSquare;

public class SquareButton extends FigureButton{
    public SquareButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(MovablePoint startPoint, MovablePoint endPoint) {
        return new DrawableMovableSquare(startPoint, endPoint);
    }
}
