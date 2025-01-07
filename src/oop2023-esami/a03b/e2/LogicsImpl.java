package a03b.e2;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class LogicsImpl implements Logics {

    int width;
    int height;
    Optional<Pair<Integer, Integer>> inizio = Optional.empty();
    Set<Pair<Integer, Integer>> punti;

    LogicsImpl(int width, int height) {
        this.width = width;
        this.height = height;
        punti = new HashSet<>();
    }

    @Override
    public boolean hit(Pair<Integer, Integer> pos) {
        if (inizio.isEmpty()) {
            inizio = Optional.ofNullable(pos);
            return true;
        }
        compose(pos);
        return false;
    }

    private void compose(Pair<Integer, Integer> pos) {
        Pair<Integer, Integer> p = pos;
        while (p.getY() != this.width) {
            p = new Pair<Integer, Integer>(p.getX(), p.getY() + 1);
            for (int i = p.getX() - 1; i <= p.getX() + 1; i++) {
                Pair<Integer, Integer> po = new Pair<>(i, p.getY());
                if (po.getY() == this.width) {
                    break;
                }
                this.punti.add(po);
            }
        }
    }

    @Override
    public boolean isPresent(Pair<Integer, Integer> value) {
        return this.punti.contains(value);
    }
}
