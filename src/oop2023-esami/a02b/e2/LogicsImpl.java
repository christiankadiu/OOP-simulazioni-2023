package a02b.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

public class LogicsImpl implements Logics {
    int size;
    Optional<Position> center;
    Set<Position> quad;
    Set<Position> matrix;
    List<Position> vicini;

    LogicsImpl(final int size) {
        this.size = size;
        center = Optional.empty();
        this.matrix = new HashSet<>();
        this.quad = new HashSet<>();
        vicini = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                this.matrix.add(new Position(i, k));
            }
        }
    }

    @Override
    public void hit(Position pos) {
        if (center.isEmpty()) {
            center = Optional.ofNullable(pos);
            generate();
            return;
        }
        if (vicini.contains(pos)) {
            changeCenter(pos);
            generate();
        }
    }

    private void changeCenter(Position pos) {
        if (pos.equals(vicini.get(0))) {
            center = Optional.of(new Position(center.get().x() - 3, center.get().y() - 3));
        }
        if (pos.equals(vicini.get(1))) {
            center = Optional.of(new Position(center.get().x() + 3, center.get().y() - 3));
        }
        if (pos.equals(vicini.get(2))) {
            center = Optional.of(new Position(center.get().x() + 3, center.get().y() + 3));
        }
        if (pos.equals(vicini.get(3))) {
            center = Optional.of(new Position(center.get().x() - 3, center.get().y() + 3));
        }
    }

    private void generate() {
        Position topLeft = new Position(center.get().x() - 2, center.get().y() - 2);
        Position bottomDown = new Position(center.get().x() + 2, center.get().y() + 2);
        this.quad = new HashSet<>(this.matrix.stream()
                .filter(i -> i.x() >= topLeft.x() && i.x() <= bottomDown.x() && i.y() >= topLeft.y()
                        && i.y() <= bottomDown.y())
                .collect(Collectors.toSet()));
        vicini = new ArrayList<>();
        vicini.add(new Position(center.get().x() - 1, center.get().y() - 1));
        vicini.add(new Position(center.get().x() + 1, center.get().y() - 1));
        vicini.add(new Position(center.get().x() + 1, center.get().y() + 1));
        vicini.add(new Position(center.get().x() - 1, center.get().y() + 1));
        for (Position position : vicini) {
            this.quad.remove(position);
        }
    }

    @Override
    public boolean get(Position value) {
        return this.quad.contains(value);
    }
}
