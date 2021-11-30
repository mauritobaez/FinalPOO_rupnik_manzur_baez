package backend.model;

public class MovablePoint extends Point implements Movable{
    public MovablePoint(double x,double y){
        super(x,y);
    }
    @Override
    public void moveX(double diff) {
        x+=diff;
    }

    @Override
    public void moveY(double diff) {
        y+=diff;
    }
}
