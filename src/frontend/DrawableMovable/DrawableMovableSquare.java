package frontend.DrawableMovable;

import backend.model.movables.MovablePoint;
import backend.model.movables.MovableSquare;

public class DrawableMovableSquare extends MovableSquare {
    public DrawableMovableSquare(MovablePoint topLeft, MovablePoint bottomRight) {
        super(topLeft, bottomRight);
    }
}
