package frontend.buttons;

import backend.model.Figure;
import backend.model.Line;
import backend.model.Point;
import backend.model.movables.MovablePoint;
import frontend.DrawableMovable.DrawableMovableLine;

public class LineButton extends FigureButton{
    public LineButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(MovablePoint startPoint, MovablePoint endPoint) {
        return new DrawableMovableLine(startPoint, endPoint);
    }
}
