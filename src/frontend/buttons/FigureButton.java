package frontend.buttons;

import backend.model.Figure;
import backend.model.movables.MovablePoint;
import javafx.scene.control.ToggleButton;

public abstract class FigureButton extends ToggleButton {

    private final boolean freeDirectionForCreation;

    public FigureButton(String name, boolean freeDirectionForCreation) {
        super(name);
        this.freeDirectionForCreation = freeDirectionForCreation;
    }

    public abstract Figure createFigure(MovablePoint startPoint, MovablePoint endPoint);

    public boolean isFreeDirectionForCreation(){
        return freeDirectionForCreation;
    }
}

