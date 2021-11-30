package backend.model;

import java.util.ArrayList;
import java.util.Collection;

public class MovableRectangle extends Rectangle implements MovableFigure{

    public MovableRectangle(MovablePoint topLeft, MovablePoint bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public Collection<MovablePoint> getPoints() {
        Collection<MovablePoint> out=new ArrayList<>();
        out.add((MovablePoint)getTopLeft()); //casteo Seguro pues el constructor pide MovablePoint
        out.add((MovablePoint)getBottomRight());
        return out;
    }
}
