package backend.model;

public class Line extends Figure{
    private final Point startingPoint, endingPoint;

    public Line(Point startingPoint, Point endingPoint) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }

    public Point getstartingPoint() {
        return startingPoint;
    }

    public Point getendingPoint() {
        return endingPoint;
    }

    @Override
    public String toString() {
        return String.format("Línea [ %s , %s ]", startingPoint, endingPoint);
    }
}
