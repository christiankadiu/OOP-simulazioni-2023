package a02c.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {

    public enum direction {
        UPL(-1, -1), UPR(1, -1), DOWNL(-1, 1), DOWNR(1, 1);

        direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;
    }

    int size;
    Set<Position> set;
    Set<Position> matrice;
    Optional<Position> center;
    List<Position> vertici;
    direction dir = direction.UPR;

    LogicsImpl(int size) {
        this.size = size;
        this.set = new HashSet<>();
        matrice = new HashSet<>();
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                matrice.add(new Position(i, k));
            }
        }
        center = Optional.empty();
        vertici = new ArrayList<>();
    }

    @Override
    public void hit(Position pos) {
        if (center.isEmpty()) {
            center = Optional.of(pos);
            compose();
            return;
        }
        if (isValid(pos)) {
            change(pos);
        }
    }

    private void compose() {
        Position sx = new Position(center.get().x() - 1, center.get().y() - 1);
        Position dx = new Position(center.get().x() + 1, center.get().y() + 1);
        this.set = new HashSet<>(this.matrice.stream()
                .filter(i -> i.x() >= sx.x() && i.x() <= dx.x() && i.y() <= dx.y() && i.y() >= sx.y())
                .filter((i -> (i.x() == sx.x() || i.x() == dx.x()) || (i.y() == sx.y() || i.y() == dx.y())))
                .collect(Collectors.toSet()));
        vertici.add(new Position(this.set.stream().map(i -> i.x()).mapToInt(x -> x).min().getAsInt(),
                this.set.stream().map(i -> i.y()).mapToInt(x -> x).min().getAsInt()));
        vertici.add(new Position(this.set.stream().map(i -> i.x()).mapToInt(x -> x).max().getAsInt(),
                this.set.stream().map(i -> i.y()).mapToInt(x -> x).min().getAsInt()));
        vertici.add(new Position(this.set.stream().map(i -> i.x()).mapToInt(x -> x).max().getAsInt(),
                this.set.stream().map(i -> i.y()).mapToInt(x -> x).max().getAsInt()));
        vertici.add(new Position(this.set.stream().map(i -> i.x()).mapToInt(x -> x).min().getAsInt(),
                this.set.stream().map(i -> i.y()).mapToInt(x -> x).max().getAsInt()));
    }

    @Override
    public boolean isPresent(Position value) {
        return this.set.contains(value);
    }

    private boolean isValid(Position pos) {
        if (vertici.contains(pos)) {
            if (pos.equals(vertici.get(0))) {
                dir = direction.UPL;
            } else if (pos.equals(vertici.get(1))) {
                dir = direction.UPR;
            } else if (pos.equals(vertici.get(2))) {
                dir = direction.DOWNR;
            } else {
                dir = direction.DOWNL;
            }
        }
        return true;
    }

    private void change(Position pos) {
        print();
        System.out.println();
        this.set = this.set.stream()
                .map(i -> {
                    if (i.x() == pos.x() || i.y() == pos.y()) {
                        return new Position(i.x() + dir.x, i.y() + dir.y);
                    } else {
                        return i;
                    }
                }).collect(Collectors.toSet());
        print();
    }

    private void print() {
        for (Position position : set) {
            System.out.print(position + "\t");
        }
    }

}
