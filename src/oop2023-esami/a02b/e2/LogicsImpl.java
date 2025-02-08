package a02b.e2;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {
    int size;
    Optional<Position> center;
    Set<Position> quad;
    Set<Position> matrix;

    LogicsImpl(final int size) {
        this.size = size;
        center = Optional.empty();
        this.matrix = new HashSet<>();
        this.quad = new HashSet<>();
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
    }

    private void generate() {
        Position topLeft = new Position(center.get().x() - 2, center.get().y() - 2);
        Position bottomDown = new Position(center.get().x() + 2, center.get().y() + 2);
        this.quad = new HashSet<>(this.matrix.stream()
                .filter(i -> i.x() >= topLeft.x() && i.x() <= bottomDown.x() && i.y() >= topLeft.y()
                        && i.y() <= bottomDown.y())
                .collect(Collectors.toSet()));
        this.quad.remove(new Position(center.get().x() - 1, center.get().y() - 1));
        this.quad.remove(new Position(center.get().x() + 1, center.get().y() - 1));
        this.quad.remove(new Position(center.get().x() + 1, center.get().y() + 1));
        this.quad.remove(new Position(center.get().x() - 1, center.get().y() + 1));
    }

    @Override
    public boolean get(Position value) {
        return this.quad.contains(value);
    }
}
