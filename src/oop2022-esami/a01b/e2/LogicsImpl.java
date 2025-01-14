package a01b.e2;

import java.util.*;


public class LogicsImpl implements Logics {

    int size;
    List<Pair<Integer, Integer>> lista;
    Set<Pair<Integer, Integer>> set; 

    LogicsImpl(int size){
        this.size = size;
        lista = new ArrayList<>();
        set = new HashSet<>();
    }

    private void print(){
        for (Pair<Integer,Integer> pair : set) {
            System.out.print(pair+"\t");
        }
    }

    @Override
    public void hit(Pair<Integer, Integer> pos) {
        List<Pair<Integer, Integer>> lista = new ArrayList<>();
        lista.add(new Pair<Integer,Integer>(pos.getX() - 1, pos.getY() - 1));
        lista.add(new Pair<Integer,Integer>(pos.getX() - 1, pos.getY() + 1));
        lista.add(new Pair<Integer,Integer>(pos.getX() + 1, pos.getY() - 1));
        lista.add(new Pair<Integer,Integer>(pos.getX() + 1, pos.getY() + 1));
        compose(pos, lista);
    }

    private void compose(Pair<Integer, Integer> pos, List<Pair<Integer, Integer>> lista){
        for (Pair<Integer,Integer> pair : lista) {
            if (set.contains(pair)){
                set.remove(pair);
            }else{
                set.add(pair);
            }
        }
        print();
    }

    @Override
    public boolean get(Pair<Integer, Integer> value) {
        return set.contains(value);
    }

}
