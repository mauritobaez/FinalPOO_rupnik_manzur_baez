package frontend.buttons;

import backend.model.Ellipse;
import backend.model.Figure;
import backend.model.Point;
import backend.model.movables.MovablePoint;
import frontend.DrawableMovable.DrawableMovableEllipse;
import frontend.DrawableMovable.DrawableMovableFigure;

public class EllipseButton extends FigureButton{
    public EllipseButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(MovablePoint startPoint, MovablePoint endPoint) {
        double sXAxis = startPoint.getXDifferenceTo(endPoint)/2;
        double sYAxis = startPoint.getYDifferenceTo(endPoint)/2;
        return new DrawableMovableEllipse(new MovablePoint(startPoint.getX() + sXAxis, startPoint.getY() + sYAxis), sXAxis, sYAxis);
    }
}
