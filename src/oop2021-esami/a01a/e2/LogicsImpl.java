package a01a.e2;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {

    private int size;
    private Optional<Pair<Integer, Integer>> current;
    private Set<Pair<Integer, Integer>> set;

    LogicsImpl(int size) {
        this.size = size;
        this.set = new HashSet<>();
        current = Optional.empty();
    }

    @Override
    public boolean hit(Pair<Integer, Integer> pos) {
        if (current.isEmpty()) {
            current = Optional.ofNullable(pos);
            return true;
        } else {
            compose(pos);
            current = Optional.empty();
            return false;
        }
    }

    private void compose(Pair<Integer, Integer> pos) {
        int minX = Math.min(current.get().getX(), pos.getX());
        int minY = Math.min(current.get().getY(), pos.getY());
        int maxX = Math.max(current.get().getX(), pos.getX());
        int maxY = Math.max(current.get().getY(), pos.getY());
        for (int i = minX; i <= maxX; i++) {
            for (int k = minY; k <= maxY; k++) {
                this.set.add(new Pair<Integer, Integer>(i, k));
            }
        }
        this.set = new HashSet<>(this.set.stream().distinct().collect(Collectors.toSet()));
    }

    @Override
    public boolean get(Pair<Integer, Integer> pos) {
        return this.set.contains(pos);
    }

    @Override
    public boolean toQuit() {
        if (this.set.size() == size * size) {
            return true;
        }
        return false;
    }

}
