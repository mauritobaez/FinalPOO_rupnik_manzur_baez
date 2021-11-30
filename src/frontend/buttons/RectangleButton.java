package frontend.buttons;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;

public class RectangleButton extends FigureButton{
    public RectangleButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(Point startPoint, Point endPoint) { //DrawableMovableFigure!
        return new Rectangle(startPoint, endPoint); //DrawableMovableRectangle!
    }
}
