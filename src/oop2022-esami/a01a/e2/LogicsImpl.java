package a01a.e2;

import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics {

    int size;
    List<Pair<Integer, Integer>> lista;

    LogicsImpl(int size){
        this.size = size;
        lista = new ArrayList<>();
    }

    @Override
    public boolean hit(Pair<Integer, Integer> position) {
        if (!lista.contains(position)){
            lista.add(position);
            return true;
        }else{
            lista.remove(position);
            return false;
        }
    }

    @Override
    public boolean check() {
        if (lista.size() >= 3){
            List<Pair<Integer, Integer>> list = lista.subList(lista.size() - 3, lista.size());
            int x = list.get(0).getX();
            int y = list.get(0).getY();
            long cX = list.stream().filter(i -> i.getX() == x).count();
            long cY = list.stream().filter(i -> i.getY() == y).count();
            if (cX == 3 && cY == 3){
                return true;
            }
            return false;
        }
        return false;
    }

    

    

}
