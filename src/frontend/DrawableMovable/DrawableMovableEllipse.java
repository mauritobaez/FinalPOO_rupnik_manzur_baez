package frontend.DrawableMovable;

import backend.model.movables.MovableEllipse;
import backend.model.movables.MovablePoint;
import javafx.scene.canvas.GraphicsContext;

public class DrawableMovableEllipse extends MovableEllipse implements DrawableMovableFigure{
    public DrawableMovableEllipse(MovablePoint centerPoint, double sMayorAxis, double sMinorAxis) {
        super(centerPoint, sMayorAxis, sMinorAxis);
    }

    @Override
    public void drawFigure(GraphicsContext gc) {
        drawOval(gc, getCenterPoint(), getsXAxis(), getsYAxis());
    }
}
