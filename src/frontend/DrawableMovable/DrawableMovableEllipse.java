package frontend.DrawableMovable;

import backend.model.movables.MovableEllipse;
import backend.model.movables.MovablePoint;

public class DrawableMovableEllipse extends MovableEllipse {
    public DrawableMovableEllipse(MovablePoint centerPoint, double sMayorAxis, double sMinorAxis) {
        super(centerPoint, sMayorAxis, sMinorAxis);
    }
}
