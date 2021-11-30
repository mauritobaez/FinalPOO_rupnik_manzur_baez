package frontend.buttons;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Square;

public class SquareButton extends FigureButton{
    public SquareButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(Point startPoint, Point endPoint) {
        return new Square(startPoint, endPoint);
    }
}
