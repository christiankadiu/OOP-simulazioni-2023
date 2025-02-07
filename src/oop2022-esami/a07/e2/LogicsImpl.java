package a07.e2;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {

    private int size;
    private Set<Position> activated;
    private Set<Position> matrix;
    boolean quit = false;

    LogicsImpl(final int size) {
        this.size = size;
        activated = new HashSet<>();
        matrix = new HashSet<>();
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                matrix.add(new Position(i, k));
            }
        }
    }

    @Override
    public void hit(Position position) {
        Set<Position> neighbours = new HashSet<>(getNeighbours(position));
        if (count(neighbours) > 3) {
            deactivate(neighbours);
        } else {
            activate(neighbours);
        }
        if (this.activated.size() >= (this.size * this.size) / 2) {
            this.quit = true;
        }
    }

    private Set<Position> getNeighbours(Position pos) {
        Position topLeft = new Position(pos.x() - 1, pos.y() - 1);
        Position bottomRight = new Position(pos.x() + 1, pos.y() + 1);
        return this.matrix.stream()
                .filter(i -> i.x() >= topLeft.x() && i.x() <= bottomRight.x() && i.y() >= topLeft.y()
                        && i.y() <= bottomRight.y())
                .collect(Collectors.toSet());
    }

    private int count(Set<Position> set) {
        int count = 0;
        for (Position position : set) {
            if (activated.contains(position)) {
                count++;
            }
        }
        return count;
    }

    private void deactivate(Set<Position> set) {
        for (Position position : set) {
            this.activated.remove(position);
        }
    }

    private void activate(Set<Position> set) {
        for (Position position : set) {
            this.activated.add(position);
        }
    }

    @Override
    public boolean get(Position value) {
        return this.activated.contains(value);
    }

    @Override
    public boolean isOver() {
        return this.quit;
    }

}
