package a01a.e2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class LogicsImpl implements Logics{

    //private Set<Pair<Integer, Integer>> set;
    private Map<Pair<Integer, Integer>, Integer> mappa;
    private int size;
    int count = 0;

    LogicsImpl(int size){
        this.size = size;
        //set = new  HashSet<>();
        mappa = new HashMap<>();
    }

    @Override
    public void hit(Pair<Integer, Integer> pos) {
        if (innesco(pos)){
            innescazione();
        }else{
            if (!mappa.keySet().contains(pos)){
                mappa.put(pos, count++);
            }
        }
    }

    private boolean innesco(Pair<Integer, Integer> pos){
        for (Pair<Integer,Integer> pair : mappa.keySet()) {
            if (adjacent(pair).contains(pos)){
                return true;
            }
        }
        return false;
    }

    Set<Pair<Integer,Integer>> adjacent(Pair<Integer,Integer> pos){
        Set<Pair<Integer,Integer>> setto = new HashSet<>();
        for (int  i = pos.getX() - 1; i <= pos.getX() + 1; i++){
            if (i >= 0 && i < size && (pos.getY() - 1) >= 0 && (pos.getY() + 1) < size){
                Pair<Integer,Integer> pair = new Pair<Integer,Integer>(i, pos.getY() - 1);
                if (!setto.contains(pair)){
                    setto.add(pair);
                }
            }
        }
        for (int  i = pos.getX() - 1; i <= pos.getX() + 1; i++){
            if (i >= 0 && i < size && (pos.getX() + 1) >= 0 && (pos.getX() + 1) < size){
                Pair<Integer,Integer> pair = new Pair<Integer,Integer>(i, pos.getY() + 1);
                if (!setto.contains(pair)){
                    setto.add(pair);
                }
            }
        }
        for (int  i = pos.getY() - 1; i <= pos.getY() + 1; i++){
            if (i >= 0 && i < size && (pos.getX() - 1) >= 0 && (pos.getX() - 1) < size){
                Pair<Integer,Integer> pair = new Pair<Integer,Integer>(pos.getX() - 1, i);
                if (!setto.contains(pair)){
                    setto.add(pair);
                }
            }
        }
        for (int  i = pos.getY() - 1; i <= pos.getY() + 1; i++){
            if (i >= 0 && i < size && (pos.getX() + 1) >= 0 && (pos.getX() + 1) < size){
                Pair<Integer,Integer> pair = new Pair<Integer,Integer>(pos.getX() + 1, i);
                if (!setto.contains(pair)){
                    setto.add(pair);
                }
            }
        }
        return setto;
    }

    void innescazione(){
        Map<Pair<Integer, Integer>, Integer> app = new HashMap<>();
        for (Map.Entry<Pair<Integer, Integer>, Integer> entry : mappa.entrySet()) {
            Pair<Integer, Integer> p = new Pair<Integer,Integer>(entry.getKey().getX() + 1, entry.getKey().getY() + 1);
            app.put(p, entry.getValue());
        } 
        mappa = new HashMap<>();
    }

    @Override
    public Optional<Integer> get(Pair<Integer, Integer> value) {
        return Optional.ofNullable(mappa.get(value)); 
    }

}
