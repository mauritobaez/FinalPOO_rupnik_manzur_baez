package frontend.buttons;

import backend.model.Figure;
import backend.model.movables.MovablePoint;
import frontend.drawablemovable.DrawableMovableRectangle;

public class RectangleButton extends FigureButton{
    public RectangleButton(String name) {
        super(name,false);
    }

    @Override
    public Figure createFigure(MovablePoint startPoint, MovablePoint endPoint) { //DrawableMovableFigure!
        return new DrawableMovableRectangle(startPoint, endPoint); //DrawableMovableRectangle!
    }
}
