package frontend.buttons;

import backend.model.Figure;
import backend.model.movables.MovablePoint;
import javafx.scene.control.ToggleButton;

public abstract class FigureButton extends ToggleButton {

    //para informar si se puede arrastrar en cualquier dirección el mouse y hacer la figura
    private final boolean freeDirectionForCreation;

    public FigureButton(String name, boolean freeDirectionForCreation) {
        super(name);
        this.freeDirectionForCreation = freeDirectionForCreation;
    }

    // Los botones son los que crean las figuras, que en verdad son DrawableMovableFigure-s, pero retornan
    // un Figure para poder usarlo en la LinkedList de CanvasState el cual es del back-end
    public abstract Figure createFigure(MovablePoint startPoint, MovablePoint endPoint);

    public boolean isFreeDirectionForCreation(){
        return freeDirectionForCreation;
    }

    /* Esta era otra opción para no enviarle por constructor un booleano, aquellas clases
    en las cuales se puede arrastrar el mouse en cualquier dirección harían un @Override
    de este método para que retorne true.
    La versión que tenemos ahora nos pareció más clara.
    public boolean isFreeDirectionForCreation(){
        return false;
    }
     */
}

