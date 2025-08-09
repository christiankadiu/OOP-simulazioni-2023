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
        int c = 0;
        if (lista.size() >= 3){
            for (int i = 1; i <= 2; i++){
                if (Math.abs(lista.get(lista.size() - i).getX()-lista.get(lista.size() - i - 1).getX()) == 1 && 
                        Math.abs(lista.get(lista.size() - i).getY()-lista.get(lista.size() - i - 1).getY()) == 1){
                            c++;
                }
            }
        }
        return c == 3;
    }

    

    

}
