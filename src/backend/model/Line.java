package backend.model;

public class Line extends Figure
{

    private final Point startingPoint, endingPoint;

    public Line(Point startingPoint, Point endingPoint)
    {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }

    public Point getStartingPoint()
    {
        return startingPoint;
    }

    public Point getEndingPoint()
    {
        return endingPoint;
    }

    @Override
    public String toString()
    {
        return String.format("Línea [ %s , %s ]", startingPoint, endingPoint);
    }

    //Consideramos que las líneas no tienen interior
    @Override
    public boolean pointInFigure(Point point)
    {
        return false;
    }
}
