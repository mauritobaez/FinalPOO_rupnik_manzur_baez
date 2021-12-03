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

    public void moveFigureToFirst(Figure figure){
        remove(figure);
        list.addFirst(figure);
    }

    public void moveFigureToLast(Figure figure){
        remove(figure);
        list.addLast(figure);
    }

    public void remove(Figure figure){
        list.remove(figure);
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

/*
public class CanvasState extends LinkedList<Figure> {


    public void addFigure(Figure figure) {
        addLast(figure);
    }

    public void moveFigureToFirst(Figure figure){
        remove(figure);
        addFirst(figure);
    }

    public void moveFigureToLast(Figure figure){
        remove(figure);
        addLast(figure);
    }

    public Iterable<Figure> figures() {
        Deque<Figure> myDeque = new LinkedList<>();
        for(Figure fig : this){
            myDeque.addLast(fig);
        }
        return myDeque;
    }

    // Para iterar "al revés"
    public Iterable<Figure> figuresReverse(){
        Deque<Figure> myDeque = new LinkedList<>();
        for(Figure fig : this){
            myDeque.addFirst(fig);
        }
        return myDeque;
    }

}
 */
