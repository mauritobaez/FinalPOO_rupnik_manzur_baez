package backend.model.movables;

import backend.model.Line;

import java.util.ArrayList;
import java.util.Collection;

public class MovableLine extends Line implements MovableFigure
{

    public MovableLine(MovablePoint startingPoint, MovablePoint endingPoint)
    {
        super(startingPoint, endingPoint);
    }

    @Override
    public Collection<MovablePoint> getPoints()
    {
        Collection<MovablePoint> out= new ArrayList<>();
        out.add((MovablePoint) getStartingPoint());
        out.add((MovablePoint) getEndingPoint());
        return out;
    }
}
