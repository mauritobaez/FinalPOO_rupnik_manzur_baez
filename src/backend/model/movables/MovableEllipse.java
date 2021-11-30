package backend.model.movables;

import backend.model.Ellipse;

import java.util.ArrayList;
import java.util.Collection;

public class MovableEllipse extends Ellipse implements MovableFigure{

    public MovableEllipse(MovablePoint centerPoint, double sMayorAxis, double sMinorAxis) {
        super(centerPoint, sMayorAxis, sMinorAxis);
    }

    @Override
    public Collection<MovablePoint> getPoints() {
        Collection<MovablePoint> out= new ArrayList<>();
        out.add((MovablePoint) getCenterPoint());
        return out;
    }
}
