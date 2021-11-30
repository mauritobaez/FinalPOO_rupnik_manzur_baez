package frontend.DrawableMovable;

import backend.model.Line;
import backend.model.Point;
import backend.model.movables.MovableLine;
import backend.model.movables.MovablePoint;
import javafx.scene.canvas.GraphicsContext;

// MovableLine
public class DrawableMovableLine extends MovableLine implements DrawableMovableFigure {
    public DrawableMovableLine(MovablePoint startingPoint, MovablePoint endingPoint) {
        super(startingPoint, endingPoint);
    }

    @Override
    public void drawFigure(GraphicsContext gc) {
        drawLine(gc, getStartingPoint(), getEndingPoint());
    }
}
