package backend.model;

public class Square extends Rectangle {
private static final Double EPSILON=0.01;
    //Nos aseguramos de que sea un cuadrado
    public Square(Point topLeft, Point bottomRight) throws PointsDoNotFormASquareException{
        super(topLeft, bottomRight);
        if(Math.abs(topLeft.getXDifferenceTo(bottomRight)-topLeft.getYDifferenceTo(bottomRight))>EPSILON)
            throw new PointsDoNotFormASquareException();
    }
    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", getTopLeft(), getBottomRight());
    }
}
