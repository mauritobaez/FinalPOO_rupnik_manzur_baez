package frontend.buttons;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import backend.model.movables.MovablePoint;
import frontend.DrawableMovable.DrawableMovableCircle;
import frontend.DrawableMovable.DrawableMovableFigure;

public class CircleButton extends FigureButton{


    public CircleButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(MovablePoint startPoint, MovablePoint endPoint) {
        return new DrawableMovableCircle(startPoint, startPoint.distanceTo(endPoint));
    }
}
