package frontend.buttons;

import backend.model.Figure;
import backend.model.Point;
import backend.model.movables.MovablePoint;
import javafx.scene.control.ToggleButton;

public abstract class FigureButton extends ToggleButton {
    public FigureButton(String name) {
        super(name);
    }

    public abstract Figure createFigure(MovablePoint startPoint, MovablePoint endPoint); //Debería ser un DrawableMovableFigure
}

