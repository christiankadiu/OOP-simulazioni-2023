package a02b.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LogicsImpl implements Logics {
    List<Pair<Integer, Integer>> set;
    int size;

    LogicsImpl(int size) {
        this.size = size;
        set = new ArrayList<>();
    }

    @Override
    public void hit(Pair<Integer, Integer> p) {
        if (set.isEmpty()) {
            set.add(p);
            compose(p);
        }
    }

    private void compose(Pair<Integer, Integer> p) {
        for (int i = p.getY() - 2; i <= p.getY() + 2; i++) {
            for (int k = p.getX() - 2; k <= p.getX() + 2; k++) {
                Pair<Integer, Integer> pos = new Pair<Integer, Integer>(k, i);
                if ((pos.getY() != p.getY() - 1 && pos.getY() != p.getY() + 1) ||
                        (pos.getX() != p.getX() - 1 && pos.getX() != p.getX() + 1)) {
                    if (!set.contains(pos)) {
                        set.add(pos);
                    }
                }
            }
        }
    }

    @Override
    public boolean isPresent(Pair<Integer, Integer> value) {
        return set.contains(value);
    }

    private boolean isValid(Pair<Integer, Integer> pos) {
        Pair<Integer, Integer> p = set.getFirst();
        if ((pos.getX() == p.getX() - 1 && pos.getY() == p.getY() - 1)
                || (pos.getX() == p.getX() + 1 && pos.getY() == p.getY() + 1)
                || (pos.getX() == p.getX() - 1 && pos.getY() == p.getY() + 1)
                || (pos.getX() == p.getX() + 1 && pos.getY() == p.getY() - 1)) {
            return true;
        }
        return false;
    }

}