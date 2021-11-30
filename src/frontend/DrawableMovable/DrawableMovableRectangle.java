package frontend.DrawableMovable;

import backend.model.movables.MovablePoint;
import backend.model.movables.MovableRectangle;

public class DrawableMovableRectangle extends MovableRectangle {
    public DrawableMovableRectangle(MovablePoint topLeft, MovablePoint bottomRight) {
        super(topLeft, bottomRight);
    }
}
