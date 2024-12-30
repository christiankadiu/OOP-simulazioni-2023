package a02a.e2;

import java.util.*;

public class LogicsImpl implements Logics {
    int size;
    Set<Pair<Integer, Integer>> enabled;
    Set<Pair<Integer, Integer>> bishops;
    LogicsImpl(int size){
        this.bishops = new HashSet<>();
        this.enabled = new HashSet<>();
        this.size = size;
    }
    @Override
    public void hit(Pair<Integer, Integer> pos) {
        bishops.add(pos);
        for (Pair<Integer,Integer> pair : checkDiagonals(pos)) {
            this.enabled.add(pair);
        }
    }

    private List<Pair<Integer, Integer>> checkDiagonals(Pair<Integer,Integer> pos) {
        int i, k;
        List<Pair<Integer, Integer>> lista = new ArrayList<>();
        for(i = pos.getX(), k = pos.getY(); i < this.size || k < this.size; i++, k++){
            Pair<Integer, Integer> p = new Pair<Integer,Integer>(i, k);
            if (i != pos.getX() && k != pos.getY() && !this.bishops.contains(p)){
                lista.add(p);
            }
        }
        for(i = pos.getX(), k = pos.getY(); i >= 0 || k >= 0; i--, k--){
            Pair<Integer, Integer> p = new Pair<Integer,Integer>(i, k);
            if (i != pos.getX() && k != pos.getY() && !this.bishops.contains(p)){
                lista.add(p);
            }
        }
        for(i = pos.getX(), k = pos.getY(); i >= 0 || k < this.size; i--, k++){
            Pair<Integer, Integer> p = new Pair<Integer,Integer>(i, k);
            if (i != pos.getX() && k != pos.getY() && !this.bishops.contains(p)){
                lista.add(p);
            }
        }
        for(i = pos.getX(), k = pos.getY(); i < this.size || k >= 0; i++, k--){
            Pair<Integer, Integer> p = new Pair<Integer,Integer>(i, k);
            if (i != pos.getX() && k != pos.getY() && !this.bishops.contains(p)){
                lista.add(p);
            }
        }
        return lista;
    }
    @Override
    public boolean isEnabled(Pair<Integer, Integer> pos) {
        if (this.enabled.contains(pos)) {
            return true;
        }
        return false;
    }
    @Override
    public boolean toQuit() {
        return this.bishops.size() + this.enabled.size() == this.size * this.size;

    }

    
}
