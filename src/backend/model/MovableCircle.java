package backend.model;

import java.util.ArrayList;
import java.util.Collection;

public class MovableCircle extends Circle implements MovableFigure{
    public MovableCircle(MovablePoint centerPoint, double radius) {
        super(centerPoint, radius);
    }

    @Override
    public Collection<MovablePoint> getPoints() {
        Collection<MovablePoint> out=new ArrayList<>();
        out.add((MovablePoint) getCenterPoint());
        return out;
    }

}
