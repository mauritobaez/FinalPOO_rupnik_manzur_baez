package frontend.DrawableMovable;

import backend.model.movables.MovablePoint;
import backend.model.movables.MovableRectangle;
import javafx.scene.canvas.GraphicsContext;

public class DrawableMovableRectangle extends MovableRectangle implements DrawableMovableFigure{
    public DrawableMovableRectangle(MovablePoint topLeft, MovablePoint bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public void drawFigure(GraphicsContext gc) {
        drawRect(gc, getTopLeft(), getBottomRight());
    }
}
