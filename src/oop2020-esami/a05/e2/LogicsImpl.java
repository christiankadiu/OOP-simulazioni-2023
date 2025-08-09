package a05.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicsImpl implements Logics {

    int size;

    List<List<Integer>> matrice;

    LogicsImpl(int size) {
        Random r = new Random();
        this.size = size;
        matrice = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Integer> raw = new ArrayList<>();
            matrice.add(raw);
            for (int k = 0; k < size; k++) {
                raw.add(r.nextInt(10));
            }
        }
    }

    @Override
    public int getNumber(Pair<Integer, Integer> pos) {
        return matrice.get(pos.getX()).get(pos.getY());
    }

    @Override
    public void hit(Pair<Integer, Integer> position) {
        int tmp = matrice.get(position.getX()).get(position.getY());
        compose(position, tmp);
        print();
    }

    private void compose(Pair<Integer, Integer> pos, int tmp) {
        for (Pair<Integer, Integer> p : Adjacent(pos)) {
            matrice.get(p.getX()).set(p.getY(), tmp);
            matrice.get(pos.getX()).set(pos.getY(), 10);
        }
    }

    private List<Pair<Integer, Integer>> Adjacent(Pair<Integer, Integer> pos) {
        List<Pair<Integer, Integer>> lista = new ArrayList<>();
        for (int i = pos.getX() - 1; i <= pos.getX() + 1; i++) {
            if (i >= 0 && i < size && pos.getY() - 1 >= 0 && pos.getY() - 1 < size) {
                Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, pos.getY() - 1);
                lista.add(p);
            }
        }
        for (int i = pos.getX() - 1; i <= pos.getX() + 1; i++) {
            if (i >= 0 && i < size && pos.getY() + 1 >= 0 && pos.getY() + 1 < size) {
                Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, pos.getY() + 1);
                lista.add(p);
            }
        }
        if (pos.getX() - 1 >= 0 && pos.getX() - 1 < size) {
            lista.add(new Pair<Integer, Integer>(pos.getX() - 1, pos.getY()));
        }
        if (pos.getX() + 1 >= 0 && pos.getX() + 1 < size) {
            lista.add(new Pair<Integer, Integer>(pos.getX() + 1, pos.getY()));
        }
        return lista;
    }

    private void print() {
        for (List<Integer> list : matrice) {
            for (Integer optional : list) {
                System.out.print(optional + "\t");
            }
            System.out.println();
        }
    }
}
