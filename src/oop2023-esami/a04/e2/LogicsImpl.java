package a04.e2;

import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics {

    int size;
    List<Pair<Integer, Integer>> lista;

    LogicsImpl(int size) {
        this.size = size;
        lista = new ArrayList<>();
    }

    void print() {
        for (Pair<Integer, Integer> pair : lista) {
            System.out.print(pair + "\t");
        }
    }

    @Override
    public void hit(Pair<Integer, Integer> pos) {
        if (lista.isEmpty()) {
            if (isValid(pos)) {
                lista.add(pos);
            }
            return;
        }
        if (validate(pos)) {
            lista.add(pos);
        }
        print();
        System.out.println();
    }

    private boolean isValid(Pair<Integer, Integer> pos) {
        return pos.getY() == 0;
    }

    private boolean validate(Pair<Integer, Integer> pos) {
        var p = lista.getLast();
        if ((pos.getX() == p.getX() + 1 || pos.getX() == p.getX() - 1) && (pos.getY() == p.getY() + 1)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean toQuit() {
        if (lista.size() > 0) {
            return lista.getLast().getY() == this.size - 1;
        }
        return false;
    }
}
