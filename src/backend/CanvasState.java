package backend;

import backend.model.Figure;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;


public class CanvasState {

    private final Deque<Figure> list = new LinkedList<>();

    public void addFigure(Figure figure) {
        list.addLast(figure);
    }

    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }

    // Para iterar "al revés"
    public Iterable<Figure> figuresReverse(){
        Deque<Figure> myDeque = new LinkedList<>();
        for(Figure fig : list){
            myDeque.addFirst(fig);
        }
        return myDeque;
    }

}
