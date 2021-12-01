package backend.model;

public class Ellipse extends Figure {

    private final Point centerPoint;
    private final double sXAxis;
    private final double sYAxis;

    public Ellipse(Point centerPoint, double sXAxis, double sYAxis) {
        this.centerPoint = centerPoint;
        this.sXAxis = sXAxis;
        this.sYAxis = sYAxis;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getsXAxis() {
        return sXAxis;
    }

    public double getsYAxis(){
        return sYAxis;
    }

    @Override
    public String toString() {
        return String.format("Elipse [Centro: %s, SemiejeHoriz: %.2f, SemiejeVert: %.2f]", centerPoint, sXAxis, sYAxis);
    }

    @Override
    public boolean pointInFigure(Point point) {
        double p = (Math.pow((point.getX() - centerPoint.getX()), 2) / Math.pow(sXAxis, 2))
                + (Math.pow((point.getY() - centerPoint.getY()), 2) / Math.pow(sYAxis, 2));

        return Double.compare(p,1.0) <1;
    }
}
