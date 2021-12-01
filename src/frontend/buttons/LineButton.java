package frontend.buttons;

import backend.model.Figure;
import backend.model.movables.MovablePoint;
import frontend.drawablemovable.DrawableMovableLine;

public class LineButton extends FigureButton{
    public LineButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(MovablePoint startPoint, MovablePoint endPoint) {
        return new DrawableMovableLine(startPoint, endPoint);
    }
}
