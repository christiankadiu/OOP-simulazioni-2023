package a03b.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {

    int size;
    List<Pair<Integer, Integer>> nemici;
    Pair<Integer, Integer> bishop;
    Set<Pair<Integer, Integer>> enabled;

    LogicsImpl(int size) {
        Random r = new Random();
        this.size = size;
        bishop = new Pair<Integer, Integer>(r.nextInt(size), r.nextInt(size));
        calculate(bishop);
        nemici = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Pair<Integer, Integer> p = new Pair<Integer, Integer>(r.nextInt(size), r.nextInt(size));
            if (!nemici.contains(p) && !p.equals(bishop)) {
                nemici.add(p);
            }
        }
    }

    @Override
    public int get(Pair<Integer, Integer> value) {
        if (value.equals(bishop)) {
            return 1;
        }
        if (nemici.contains(value)) {
            return 2;
        }
        return 0;
    }

    private void calculate(Pair<Integer, Integer> pos) {
        enabled = new HashSet<>();
        for (int i = pos.getX(), k = pos.getY(); i < this.size && k < this.size; i++, k++) {
            Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, k);
            if (!enabled.contains(p) && !p.equals(pos)) {
                this.enabled.add(p);
            }
        }
        for (int i = pos.getX(), k = pos.getY(); i >= 0 && k >= 0; i--, k--) {
            Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, k);
            if (!enabled.contains(p) && !p.equals(pos)) {
                this.enabled.add(p);
            }
        }
        for (int i = pos.getX(), k = pos.getY(); i >= 0 && k < this.size; i--, k++) {
            Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, k);
            if (!enabled.contains(p) && !p.equals(pos)) {
                this.enabled.add(p);
            }
        }
        for (int i = pos.getX(), k = pos.getY(); i < size && k >= 0; i++, k--) {
            Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, k);
            if (!enabled.contains(p) && !p.equals(pos)) {
                this.enabled.add(p);
            }
        }
    }

    @Override
    public boolean isEnabled(Pair<Integer, Integer> value) {
        return enabled.contains(value);
    }

    @Override
    public void hit(Pair<Integer, Integer> pos) {
        if (enabled.contains(pos)) {
            if (nemici.contains(pos)) {
                nemici.remove(pos);
            }
            bishop = pos;
            calculate(pos);
        }
    }
}
