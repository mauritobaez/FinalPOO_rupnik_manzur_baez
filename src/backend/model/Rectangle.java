package backend.model;

public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public boolean pointInFigure(Point point) {
        return point.getX() > getTopLeft().getX() && point.getX() < getBottomRight().getX() && point.getY() > getTopLeft().getY() && point.getY() < getBottomRight().getY();
    }
}
