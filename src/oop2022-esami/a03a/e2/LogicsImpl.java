package a03a.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicsImpl implements Logics{

    Set<Pair<Integer, Integer>> set;
    int size;

    LogicsImpl(int size){
        this.size = size;
        set = new HashSet<>();
    }

    @Override
    public boolean hit(Pair<Integer, Integer> position) {
        if (!set.contains(position)){
            set.add(new Pair<Integer,Integer>(position.getX(), position.getY()));
            return true;
        }
        return false;
    }
}
