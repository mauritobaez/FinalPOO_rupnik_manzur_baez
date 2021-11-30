package frontend.DrawableMovable;

import backend.model.Line;
import backend.model.Point;
import backend.model.movables.MovableLine;
import backend.model.movables.MovablePoint;

// MovableLine
public class DrawableMovableLine extends MovableLine {
    public DrawableMovableLine(MovablePoint startingPoint, MovablePoint endingPoint) {
        super(startingPoint, endingPoint);
    }
}
