package backend.model.movables;

import backend.model.Rectangle;

import java.util.ArrayList;
import java.util.Collection;

public class MovableRectangle extends Rectangle implements MovableFigure
{

    public MovableRectangle(MovablePoint topLeft, MovablePoint bottomRight)
    {
        super(topLeft, bottomRight);
    }

    @Override
    public Collection<MovablePoint> getPoints()
    {
        Collection<MovablePoint> out = new ArrayList<>();
        // Casteos seguros pues el constructor pide MovablePoint, y no hay setters como para que deje de ser MovablePoint
        out.add((MovablePoint)getTopLeft());
        out.add((MovablePoint)getBottomRight());
        return out;
    }
}
