package backend.model;

public class Square extends Rectangle {

    public Square(Point topLeft, Point bottomRight) throws PointsDoNotFormASquareException{
        super(topLeft, bottomRight);
        if(Double.compare(topLeft.getXDifferenceTo(bottomRight), topLeft.getYDifferenceTo(bottomRight))!=0)
            throw new PointsDoNotFormASquareException();
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", getTopLeft(), getBottomRight());
    }
}
