package backend.model;

public class PointsDoNotFormASquareException extends Exception
{
    public PointsDoNotFormASquareException()
    {
        super("Points must form a square!");
    }
}
