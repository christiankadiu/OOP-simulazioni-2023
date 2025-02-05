package a02c.e2;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {

    final private int size;
    private Optional<Position> center;
    private Set<Position> quad;

    LogicsImpl(final int size) {
        this.size = size;
        center = Optional.empty();
        quad = new HashSet<>();
    }

    @Override
    public void hit(Position pos) {
        if (center.isEmpty()) {
            center = Optional.ofNullable(pos);
            generateQuad();
        }
    }

    private void generateQuad() {
        Set<Position> matrix = new HashSet<>();
        getMatrix(matrix);

        Position sx = new Position(center.get().x() - 1, center.get().y() - 1);
        Position dx = new Position(center.get().x() + 1, center.get().y() + 1);
        this.quad.addAll(
                (matrix.stream().filter(i -> i.x() >= sx.x() && i.x() <= dx.x() && i.y() <= dx.y() && i.y() >= sx.y()))
                        .filter(t -> (t.x() == sx.x() || t.x() == dx.x()) || (t.y() == sx.y() || t.y() == dx.y()))
                        .collect(Collectors.toSet()));

    }

    private void getMatrix(Set<Position> matrix) {
        for (int i = 0; i < this.size; i++) {
            for (int k = 0; k < this.size; k++) {
                matrix.add(new Position(i, k));
            }
        }
    }

    @Override
    public boolean get(Position value) {
        return this.quad.contains(value);
    }

}
