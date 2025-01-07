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
        this.punti.add(pos);
        while (pos.getX() != inizio.get().getX() || pos.getX() != this.width) {
            pos = new Pair<Integer, Integer>(pos.getX() + 1, pos.getY());
            for (int i = pos.getY() - 1; i <= pos.getY() + 1; i++) {
                this.punti.add(new Pair<Integer, Integer>(pos.getX(), i));
            }
        }
    }

    @Override
    public boolean isPresent(Pair<Integer, Integer> value) {
        return this.punti.contains(value);
    }
}
