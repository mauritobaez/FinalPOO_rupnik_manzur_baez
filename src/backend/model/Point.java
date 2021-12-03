package backend.model;

public class Point {
    ///Cambio a protected de public, mala practica
    protected double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getXDifferenceTo(Point other)
    {
        return other.x - this.x;
    }

    public double getYDifferenceTo(Point other)
    {
        return other.y - this.y;
    }

    public double distanceTo(Point other)
    {
        return Math.sqrt(Math.pow(this.getXDifferenceTo(other), 2) + Math.pow(this.getYDifferenceTo(other),2));
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

}
