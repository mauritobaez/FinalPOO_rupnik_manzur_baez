package frontend.buttons;

import backend.model.Figure;
import backend.model.movables.MovablePoint;
import frontend.drawablemovable.DrawableMovableCircle;

public class CircleButton extends FigureButton
{
    public CircleButton(String name)
    {
        super(name, true);
    }

    @Override
    public Figure createFigure(MovablePoint startPoint, MovablePoint endPoint)
    {
        return new DrawableMovableCircle(startPoint, startPoint.distanceTo(endPoint));
    }
}
