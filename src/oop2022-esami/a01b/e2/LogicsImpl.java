package a01b.e2;

import java.util.ArrayList;
import java.util.List;



public class LogicsImpl implements Logics {

    int size;
    List<List<Boolean>> matrice;
    Boolean quit = false;

    LogicsImpl(int size) {
        this.size = size;
        matrice = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            List<Boolean> raw = new ArrayList<>();
            matrice.add(raw);
            for (int k = 0; k < this.size; k++) {
                raw.add(false);
            }
        }
    }

    @Override
    public void hit(Pair<Integer, Integer> position) {
        int posi = 0;
        int neg = 0;
        if (!matrice.get(position.getX()).get(position.getY())) {
            for (Pair<Integer, Integer> pos : Adjacent(position)) {
                if (matrice.get(pos.getX()).get(pos.getY())){
                    neg++;
                    matrice.get(pos.getX()).set(pos.getY(), false);
                }else{
                    posi++;
                    matrice.get(pos.getX()).set(pos.getY(), true);
                } 
            }
        }
        if (posi == 3 && neg == 1){
            this.quit = true;
        }
    }

    List<Pair<Integer, Integer>> Adjacent(Pair<Integer, Integer> pos) {
        List<Pair<Integer, Integer>> vicini = new ArrayList<>();
        if ((pos.getX() - 1) >= 0 && (pos.getX() - 1) < size && (pos.getY() - 1) >= 0 && (pos.getY() - 1) < size) {
            vicini.add(new Pair<>(pos.getX() - 1, pos.getY() - 1));
        }
        if ((pos.getX() - 1) >= 0 && (pos.getX() - 1) < size && (pos.getY() + 1) >= 0 && (pos.getY() + 1) < size) {
            vicini.add(new Pair<>(pos.getX() - 1, pos.getY() + 1));
        }
        if ((pos.getX() + 1) >= 0 && (pos.getX() + 1) < size && (pos.getY() - 1) >= 0 && (pos.getY() - 1) < size) {
            vicini.add(new Pair<>(pos.getX() + 1, pos.getY() - 1));
        }
        if ((pos.getX() + 1) >= 0 && (pos.getX() + 1) < size && (pos.getY() + 1) >= 0 && (pos.getY() + 1) < size) {
            vicini.add(new Pair<>(pos.getX() + 1, pos.getY() + 1));
        }
        return vicini;
    }


    @Override
    public boolean isEnabled(Pair<Integer, Integer> pos) {
        if (matrice.get(pos.getX()).get(pos.getY())){
            return true;
        }
        return false;
    }

    @Override
    public boolean toQuit(){
        if (this.quit){
            return true;
        }
        return false;
    }

}
