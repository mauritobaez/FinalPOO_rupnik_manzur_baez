package frontend.buttons;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import backend.model.movables.MovablePoint;
import frontend.DrawableMovable.DrawableMovableFigure;
import frontend.DrawableMovable.DrawableMovableRectangle;

public class RectangleButton extends FigureButton{
    public RectangleButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(MovablePoint startPoint, MovablePoint endPoint) { //DrawableMovableFigure!
        return new DrawableMovableRectangle(startPoint, endPoint); //DrawableMovableRectangle!
    }
}
