package a01a.e2;

import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics{
    int size;
    List<Pair<Integer, Integer>> g1;
    List<Pair<Integer, Integer>> g2;
    boolean t = true;

    LogicsImpl(int size){
        this.size = size;
        g1 = new ArrayList<>();
        g2 = new ArrayList<>();
    }


    @Override
    public boolean hit(Pair<Integer, Integer> position) {
        if (t && !g1.contains(position) && isValid(position)){
            g1.add(position);
            t = false;
            return true;
        }
        if (!t && !g2.contains(position) && isValid(position)){
            g2.add(position);
            t = true;
            return true;
        }
        return false;
    }

    private boolean isValid(Pair<Integer, Integer> pos){
        Pair<Integer, Integer> p = new Pair<Integer, Integer>(pos.getX(), pos.getY() + 1);
        return !g1.contains(p) && !g2.contains(p);
    }


    @Override
    public boolean toQuit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toQuit'");
    }


    
    
}
