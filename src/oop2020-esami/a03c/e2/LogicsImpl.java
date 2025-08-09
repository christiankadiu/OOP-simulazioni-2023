package a03c.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicsImpl implements Logics {
    int size;
    List<Pair<Integer, Integer>> lista;

    LogicsImpl(int size) {
        Random r = new Random();
        this.size = size;
        lista = new ArrayList<>();
        lista.add(new Pair<Integer, Integer>(r.nextInt(size), 0));
    }

    @Override
    public Pair<Integer, Integer> get() {
        return lista.get(0);
    }

}
