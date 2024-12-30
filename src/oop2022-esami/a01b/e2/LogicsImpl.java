package a01b.e2;

import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.ArrayType;

public class LogicsImpl implements Logics {

    int size;
    List<List<Boolean>> matrice;

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
    public List<Pair<Integer, Integer>> get(Pair<Integer, Integer> position) {
        List<Pair<Integer, Integer>> lista = new ArrayList<>();
        if (matrice.get(position.getX()).get(position.getY())) {
            return lista;
        } else {
            lista = setta(position);
            return lista;
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

    private List<Pair<Integer, Integer>> setta(Pair<Integer, Integer> pos) {
        List<Pair<Integer, Integer>> lista = new ArrayList<>(Adjacent(pos));
        for (Pair<Integer, Integer> posit : lista) {
            if (matrice.get(posit.getX()).get(posit.getY())) {
                matrice.get(posit.getX()).set(posit.getY(), false);
            } else {
                matrice.get(posit.getX()).set(posit.getY(), true);
            }
        }
        return lista;
    }

}
