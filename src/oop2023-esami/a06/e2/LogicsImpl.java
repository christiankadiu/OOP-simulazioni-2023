package a06.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics{
    int size;
    Optional<Pair<Integer, Integer>> current;
    Optional<Pair<Integer, Integer>> other;
    List<List<Integer>> matrice;
    Set<Pair<Integer, Integer>> set;
    Set<Pair<Integer, Integer>> disabled;
    boolean d = false;
    boolean s = false;

    LogicsImpl(int size){
        Random r = new Random();
        this.size = size;
        matrice = new ArrayList<>();
        for (int i = 0; i < size; i++){
            List<Integer> raw = new ArrayList<>();
            matrice.add(raw);
            for (int k = 0; k < size; k++){
                raw.add(r.nextInt(10));
            }
        }
        this.set = new HashSet<>();
        this.disabled = new HashSet<>();
    }

    @Override
    public boolean hit(Pair<Integer, Integer> pos) {
        if (current.isEmpty()){
            current = Optional.ofNullable(pos);
            return false;
        }else{
            other = Optional.ofNullable(pos);
            if (current.get().equals(pos)){
                disabled.add(current.get());
                disabled.add(pos);
                
            }
            return true;
        }
    }

    @Override
    public boolean get(Pair<Integer, Integer> value) {
        return lista.contains(value);
    }

    @Override
    public int getNumber(Pair<Integer, Integer> value) {
        if (set.contains(value)){
            return 
        }
    }
}
