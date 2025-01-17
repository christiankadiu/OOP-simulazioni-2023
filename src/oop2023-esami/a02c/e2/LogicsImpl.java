package a02c.e2;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {

    int size;
    Optional<Position> center;
    Set<Position> matrice;
    Set<Position> quad;

    LogicsImpl(int size) {
        this.size = size;
        center = Optional.empty();
        this.matrice = new HashSet<>();
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                matrice.add(new Position(i, k));
            }
        }
        this.quad = new HashSet<>();
    }

    @Override
    public void hit(Position pos) {
        if (center.isEmpty()) {
            center = Optional.ofNullable(pos);
            create(center.get());
            return;
        }
    }

    private void create(Position c) {
        Position sx = new Position(c.x() - 1, c.y() - 1);
        Position dx = new Position(c.x() + 1, c.y() + 1);
        if (sx.x() >= 0 && sx.x() < size && sx.y() >= 0 && sx.y() < size && dx.x() >= 0 && dx.x() < size && dx.y() >= 0
                && dx.y() < size) {
            this.quad = new HashSet<>(this.matrice.stream()
                    .filter(i -> i.x() >= sx.x() && i.x() <= dx.x() && i.y() >= sx.y() && i.y() <= dx.y())
                    .filter(i -> i.x() == dx.x() || i.x() == sx.x() || i.y() == sx.y() || i.y() == dx.y())
                    .collect(Collectors.toSet()));
        }
    }

    @Override
    public boolean isPresent(Position value) {
        return this.quad.contains(value);
    }

}
