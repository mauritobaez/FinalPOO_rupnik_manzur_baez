package frontend.DrawableMovable;

import backend.model.movables.MovableCircle;
import backend.model.movables.MovablePoint;
import javafx.scene.canvas.GraphicsContext;

public class DrawableMovableCircle extends MovableCircle implements DrawableMovableFigure {
    public DrawableMovableCircle(MovablePoint centerPoint, double radius) {
        super(centerPoint, radius);
    }

    @Override
    public void drawFigure(GraphicsContext gc) {
        drawOval(gc, getCenterPoint(), getRadius(), getRadius());
    }
}
