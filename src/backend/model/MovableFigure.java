package backend.model;

import java.util.Collection;

public interface MovableFigure extends Movable{
    Collection<MovablePoint> getPoints();

    @Override
    default void moveX(double diff){
        for(MovablePoint point : getPoints()) {
        point.moveX(diff);
        }
    }

    @Override
    default void moveY(double diff){
        for(MovablePoint point : getPoints()) {
            point.moveY(diff);
        }
    }
}
