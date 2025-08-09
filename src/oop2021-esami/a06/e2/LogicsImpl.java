package a06.e2;

import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics {
    int size;
    List<Pair<Integer, Integer>> punti;
    List<Pair<Integer, Integer>> set;
    boolean primo = true;

    LogicsImpl(int size) {
        this.size = size;
        this.punti = new ArrayList<>();
        set = new ArrayList<>();
    }

    @Override
    public boolean hit(Pair<Integer, Integer> p) {
        if (isValid(p)) {
            this.punti.add(p);
            adding(p);
            return true;
        }
        return false;
    }

    @Override
    public boolean compute() {
        if (primo) {
            primo = false;
            return primo;
        }
        return false;
    }

    private boolean isValid(Pair<Integer, Integer> pos) {
        if ((Math.abs(pos.getX() - punti.getLast().getX()) <= 1 || Math.abs(pos.getY() - punti.getLast().getY()) <= 1)
                || this.punti.contains(pos)) {
            return false;
        }
        return true;
    }

    private void adding(Pair<Integer, Integer> pos) {
        this.set.add(new Pair<Integer, Integer>(pos.getX(), pos.getY() + 1));
        this.set.add(new Pair<Integer, Integer>(pos.getX(), pos.getY() - 1));
    }

    @Override
    public boolean is(Pair<Integer, Integer> value) {
        return set.contains(value);
    }

}
