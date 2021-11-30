package frontend.buttons;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;

public class CircleButton extends FigureButton{


    public CircleButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(Point startPoint, Point endPoint) {
        return new Circle(startPoint, startPoint.distanceTo(endPoint));
    }
}
