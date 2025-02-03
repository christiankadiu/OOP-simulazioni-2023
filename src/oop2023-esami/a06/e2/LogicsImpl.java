package a06.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.List;

public class LogicsImpl implements Logics {

    int size;
    List<List<Integer>> set;
    Optional<Position> first;
    Optional<Position> second;
    Set<Position> disabled;
    List<Position> current;

    LogicsImpl(int size) {
        this.size = size;
        this.set = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            List<Integer> raw = new ArrayList<>();
            set.add(raw);
            for (int k = 0; k < size; k++) {
                raw.add(r.nextInt(7));
            }
        }
        first = Optional.empty();
        second = Optional.empty();
        disabled = new HashSet<>();
        current = new ArrayList<>();
    }

    @Override
    public void hit(Position pos) {
        if (first.isEmpty()) {
            first = Optional.of(pos);
            return;
        }
        if (first.isPresent()) {
            if (first.get().equals(pos)) {
                this.disabled.add(pos);
                this.disabled.add(first.get());
            }
            first = Optional.empty();
            return;
        }
    }

    @Override
    public int getNumber(Position pos) {
        return set.get(pos.x()).get(pos.y());
    }

    @Override
    public boolean isDisabled(Position value) {
        return this.disabled.contains(value);
    }

}
