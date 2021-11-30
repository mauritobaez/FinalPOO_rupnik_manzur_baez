package frontend.buttons;

import backend.model.Ellipse;
import backend.model.Figure;
import backend.model.Point;

public class EllipseButton extends FigureButton{
    public EllipseButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(Point startPoint, Point endPoint) {
        double sXAxis = startPoint.getXDifferenceTo(endPoint)/2;
        double sYAxis = startPoint.getYDifferenceTo(endPoint)/2;
        return new Ellipse(new Point(startPoint.getX() + sXAxis, startPoint.getY() + sYAxis), sXAxis, sYAxis);
    }
}
