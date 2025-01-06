package a02a.e2;

import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics {

    int size;
    List<Pair<Integer, Integer>>  lista;

    LogicsImpl(int size){
        this.size = size;
        lista = new ArrayList<>();
    }

    @Override
    public boolean hit(Pair<Integer, Integer> pos) {
        if (isValid(pos)){
            lista.add(pos);
            print();
            return true;
        }
        return false;
    }

    private boolean isValid(Pair<Integer, Integer> p){
        if (p.getX() % 2 == 0 && p.getY() % 2 == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean quit() {
        if (lista.size() >= 4){
            for (int i = 1; i < 4; i++){
                int deltaX = Math.abs(lista.get(lista.size() - i).getX() - lista.get(lista.size() - (i + 1)).getX());
                int deltaY = Math.abs(lista.get(lista.size() - i).getY() - lista.get(lista.size() - (i + 1)).getY());
                if ((deltaX != 2 && deltaX != 0) || (deltaY != 2 && deltaY != 0)){
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    private void print(){
        for (Pair<Integer,Integer> pair : lista) {
            System.out.println(pair);
        }
    }
}
