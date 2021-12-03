package backend.model.movables;

import java.util.Collection;

public interface MovableFigure extends Movable{

    //Se consiguen los puntos de la figura que deben moverse
    Collection<MovablePoint> getPoints();

    //Mueve todos los puntos (=> la figura) en el eje horizontal
    @Override
    default void moveX(double diff){
        for(MovablePoint point : getPoints()) {
        point.moveX(diff);
        }
    }

    //Mueve todos los puntos (=> la figura) en el eje vertical
    @Override
    default void moveY(double diff){
        for(MovablePoint point : getPoints()) {
            point.moveY(diff);
        }
    }
}
