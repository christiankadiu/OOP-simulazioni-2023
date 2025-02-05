package a02c.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

public class LogicsImpl implements Logics {

    final private int size;
    private Optional<Position> center;
    private Set<Position> quad;
    private List<Position> vertexes;
    private Set<Position> matrix;

    LogicsImpl(final int size) {
        this.size = size;
        center = Optional.empty();
        quad = new HashSet<>();
        vertexes = new ArrayList<>();
        matrix = new HashSet<>();
        getMatrix(matrix);
    }

    @Override
    public void hit(Position pos) {
        if (center.isEmpty()) {
            center = Optional.ofNullable(pos);
            generateInitialQuad();
            return;
        }
        if (vertexes.contains(pos)) {
            updateVertexes(pos);
            generateGenericQuad();
        }
    }

    private void generateGenericQuad() {
        Position sx = vertexes.get(0);
        Position dx = vertexes.get(2);
        this.quad = new HashSet<>();
        this.quad.addAll(
                (matrix.stream().filter(i -> i.x() >= sx.x() && i.x() <= dx.x() && i.y() <= dx.y() && i.y() >= sx.y()))
                        .filter(t -> (t.x() == sx.x() || t.x() == dx.x()) || (t.y() == sx.y() || t.y() == dx.y()))
                        .collect(Collectors.toSet()));
    }

    private void updateVertexes(Position pos) {
        if (pos.equals(vertexes.get(0))) {
            vertexes.set(0, new Position(pos.x() - 1, pos.y() - 1));
            vertexes.set(1, new Position(vertexes.get(1).x(), vertexes.get(1).y() - 1));
            vertexes.set(3, new Position(vertexes.get(3).x() - 1, vertexes.get(3).y()));
        }
        if (pos.equals(vertexes.get(1))) {
            vertexes.set(1, new Position(pos.x() + 1, pos.y() - 1));
            vertexes.set(0, new Position(vertexes.get(0).x(), vertexes.get(1).y() - 1));
            vertexes.set(2, new Position(vertexes.get(2).x() + 1, vertexes.get(3).y()));
        }
        if (pos.equals(vertexes.get(2))) {
            vertexes.set(2, new Position(pos.x() + 1, pos.y() + 1));
            vertexes.set(1, new Position(vertexes.get(1).x() + 1, vertexes.get(1).y()));
            vertexes.set(3, new Position(vertexes.get(3).x(), vertexes.get(3).y() + 1));
        }
        if (pos.equals(vertexes.get(3))) {
            vertexes.set(3, new Position(pos.x() - 1, pos.y() + 1));
            vertexes.set(0, new Position(vertexes.get(0).x() - 1, vertexes.get(0).y()));
            vertexes.set(2, new Position(vertexes.get(2).x(), vertexes.get(2).y() + 1));
        }
    }

    private void generateInitialQuad() {

        Position sx = new Position(center.get().x() - 1, center.get().y() - 1);
        Position dx = new Position(center.get().x() + 1, center.get().y() + 1);
        this.quad.addAll(
                (matrix.stream().filter(i -> i.x() >= sx.x() && i.x() <= dx.x() && i.y() <= dx.y() && i.y() >= sx.y()))
                        .filter(t -> (t.x() == sx.x() || t.x() == dx.x()) || (t.y() == sx.y() || t.y() == dx.y()))
                        .collect(Collectors.toSet()));
        vertexes.add(new Position(this.quad.stream().map(i -> i.x()).mapToInt(x -> x).min().getAsInt(),
                this.quad.stream().map(i -> i.y()).mapToInt(x -> x).min().getAsInt()));
        vertexes.add(new Position(this.quad.stream().map(i -> i.x()).mapToInt(x -> x).max().getAsInt(),
                this.quad.stream().map(i -> i.y()).mapToInt(x -> x).min().getAsInt()));
        vertexes.add(new Position(this.quad.stream().map(i -> i.x()).mapToInt(x -> x).max().getAsInt(),
                this.quad.stream().map(i -> i.y()).mapToInt(x -> x).max().getAsInt()));
        vertexes.add(new Position(this.quad.stream().map(i -> i.x()).mapToInt(x -> x).min().getAsInt(),
                this.quad.stream().map(i -> i.y()).mapToInt(x -> x).max().getAsInt()));
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
