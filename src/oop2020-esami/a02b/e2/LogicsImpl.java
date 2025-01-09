package a02b.e2;

import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics {
    int size;
    List<List<Boolean>> matrice;

    LogicsImpl(int size){
        this.size = size;
        matrice = new ArrayList<>();
        for (int i = 0; i < size; i++){
            List<Boolean> raw = new ArrayList<>();
            matrice.add(raw);
            for (int k = 0; k < size; k++){
                raw.add(false);
            }
        }
    }

    @Override
    public boolean hit(Pair<Integer, Integer> pos) {
        if (!matrice.get(pos.getX()).get(pos.getY())){
            matrice.get(pos.getX()).set(pos.getY(), true);
            return true;
        }
        return false;
    }

    @Override
    public void setSouth() {
    }
}
