package a07.e2;

import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics {

    int size;
    List<List<Boolean>> matrice;

    LogicsImpl(int size) {
        this.size = size;
        matrice = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Boolean> raw = new ArrayList<>();
            matrice.add(raw);
            for (int k = 0; k < size; k++) {
                raw.add(false);
            }
        }
    }

    List<Pair<Integer, Integer>> adjacent(Pair<Integer, Integer> pos) {
        List<Pair<Integer, Integer>> lista = new ArrayList<>();
        for (int x = pos.getX() - 1; x <= pos.getX() + 1; x++) {
            if (x >= 0 && x < size && (pos.getY() - 1) < size && (pos.getY() - 1) >= 0) {
                lista.add(new Pair<Integer, Integer>(x, pos.getY() - 1));
            }
        }
        for (int x = pos.getX() - 1; x <= pos.getX() + 1; x++) {
            if (x >= 0 && x < size && (pos.getY() + 1) < size && (pos.getY() + 1) >= 0) {
                lista.add(new Pair<Integer, Integer>(x, pos.getY() + 1));
            }
        }
        for (int y = pos.getY() - 1; y <= pos.getY() + 1; y++) {
            if (y >= 0 && y < size && (pos.getX() + 1) < size && (pos.getX() + 1) >= 0) {
                Pair<Integer, Integer> p = new Pair<Integer, Integer>(pos.getX() + 1, y);
                if (!lista.contains(pos)) {
                    lista.add(p);
                }
            }
        }
        for (int y = pos.getY() - 1; y <= pos.getY() + 1; y++) {
            if (y >= 0 && y < size && (pos.getX() - 1) < size && (pos.getX() - 1) >= 0) {
                Pair<Integer, Integer> p = new Pair<Integer, Integer>(pos.getX() - 1, y);
                if (!lista.contains(pos)) {
                    lista.add(p);
                }
            }
        }
        lista.add(pos);
        return lista;
    }

    @Override
    public void hit(Pair<Integer, Integer> pos) {
        for (Pair<Integer, Integer> p : adjacent(pos)) {
            matrice.get(p.getX()).set(p.getY(), true);
        }
    }

    @Override
    public boolean isPresent(Pair<Integer, Integer> value) {
        return matrice.get(value.getX()).get(value.getY());
    }

    @Override

    public boolean toQuit() {
        int count = 0;
        for (List<Boolean> list : matrice) {
            for (Boolean boolean1 : list) {
                if (boolean1) {
                    count++;
                }
            }
        }
        if (count > (size * size) / 2) {
            return true;
        }
        return false;
    }

}
