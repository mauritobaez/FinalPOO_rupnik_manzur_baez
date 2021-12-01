package backend.model;

public class Square extends Rectangle {

    public Square(Point topLeft, Point finalPoint) {
        super(topLeft, new Point(finalPoint.getX(), topLeft.getY() + topLeft.getXDifferenceTo(finalPoint)));
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", getTopLeft(), getBottomRight());
    }
}
