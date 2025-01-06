package a01b.e2;

import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics {

    private List<Pair<Integer, Integer>> lista;
    private Pair<Integer, Integer> prev;
    int fine = 0;

    LogicsImpl(int size){
        lista = new ArrayList<>();
        prev = new Pair<Integer,Integer>(null, null);
    }

    @Override
    public boolean hit(Pair<Integer, Integer> pos) {
        if (lista.isEmpty()){
            lista.add(pos);
            this.prev = pos;
            this.fine++;
            return true;
        }
        if (compute(pos)){
            lista.add(pos);
            this.fine++;
            return true;
        }
        return false;
    }

    @Override
    public boolean draw() {
        return this.fine == 3;
    }

    private boolean compute(Pair<Integer, Integer> pos){
        if (pos.getX() == prev.getX() || pos.getY() == prev.getY()){
            add(pos);
            prev = pos;
            return true;
        }
        return false;
    }

    @Override
    public boolean isPresent(Pair<Integer, Integer> value) {
        return lista.contains(value);
    }

    private void add(Pair<Integer, Integer> pos){
        int minRow = Math.min(pos.getX(), prev.getX());
        int maxRow = Math.max(pos.getX(), prev.getX());
        int minCol = Math.min(pos.getY(), prev.getY());
        int maxCol = Math.max(pos.getY(), prev.getY());
        for (int i = minRow; i <= maxRow; i++){
            for (int k = minCol; k <= maxCol; k++){
                Pair<Integer, Integer> p = new Pair<Integer,Integer>(i, k);
                if (!lista.contains(p)){
                    lista.add(p);
                }
            }
        }
    }
}
