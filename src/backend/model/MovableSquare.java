package backend.model;

import java.util.ArrayList;
import java.util.Collection;

public class MovableSquare extends Square implements MovableFigure{
    public MovableSquare(MovablePoint topLeft, MovablePoint bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public Collection<MovablePoint> getPoints() {
        Collection<MovablePoint> out=new ArrayList<>();
        out.add((MovablePoint) getTopLeft());
        out.add((MovablePoint) getBottomRight());
        return out;
    }
}
