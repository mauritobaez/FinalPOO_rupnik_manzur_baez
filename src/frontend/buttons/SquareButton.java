package frontend.buttons;

import backend.model.Figure;
import backend.model.movables.MovablePoint;
import frontend.drawablemovable.DrawableMovableSquare;

public class SquareButton extends FigureButton{
    public SquareButton(String name) {
        super(name,false);
    }

    @Override
    public Figure createFigure(MovablePoint startPoint, MovablePoint endPoint) {
        return new DrawableMovableSquare(startPoint, endPoint);
    }
}
