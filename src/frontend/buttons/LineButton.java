package frontend.buttons;

import backend.model.Figure;
import backend.model.Line;
import backend.model.Point;

public class LineButton extends FigureButton{
    public LineButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(Point startPoint, Point endPoint) {
        return new Line(startPoint, endPoint);
    }
}
