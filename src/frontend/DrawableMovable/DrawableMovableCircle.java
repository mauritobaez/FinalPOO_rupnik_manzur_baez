package frontend.DrawableMovable;

import backend.model.movables.MovableCircle;
import backend.model.movables.MovablePoint;

public class DrawableMovableCircle extends MovableCircle {
    public DrawableMovableCircle(MovablePoint centerPoint, double radius) {
        super(centerPoint, radius);
    }
}
