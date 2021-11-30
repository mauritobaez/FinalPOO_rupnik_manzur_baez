package frontend.DrawableMovable;

import backend.model.movables.MovablePoint;
import backend.model.movables.MovableSquare;
import javafx.scene.canvas.GraphicsContext;

public class DrawableMovableSquare extends MovableSquare implements DrawableMovableFigure{
    public DrawableMovableSquare(MovablePoint topLeft, MovablePoint bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public void drawFigure(GraphicsContext gc) {
        drawRect(gc, getTopLeft(), getBottomRight());
    }
}
