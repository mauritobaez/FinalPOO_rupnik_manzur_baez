package backend.model;

public class Point
{

    //Son protected para que puedan ser editados por MovablePoint
    protected double x, y;

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    //Devuelve la diferencia sobre el eje horizontal entre el punto indicado y sí mismo
    public double getXDifferenceTo(Point other)
    {
        return other.x - this.x;
    }

    //Devuelve la diferencia sobre el eje vertical entre el punto indicado y sí mismo
    public double getYDifferenceTo(Point other)
    {
        return other.y - this.y;
    }

    //Devuelve la distancia entre el punto indicado y sí mismo
    public double distanceTo(Point other)
    {
        return Math.sqrt(Math.pow(this.getXDifferenceTo(other), 2) + Math.pow(this.getYDifferenceTo(other),2));
    }

    @Override
    public String toString()
    {
        return String.format("{%.2f , %.2f}", x, y);
    }

}
