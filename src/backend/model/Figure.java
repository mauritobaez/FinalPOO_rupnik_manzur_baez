package backend.model;

public abstract class Figure {
    // para que el front-end pueda ver si el un punto determinado está dentro de la figura
    public abstract boolean pointInFigure(Point point);
}
