package a01c.e2;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics{
    int size;
    Optional<Position> sx = Optional.empty();
    Optional<Position> dx = Optional.empty();
    Set<Position> set;
    Set<Position> matrice;

    LogicsImpl(int size){
        this.size = size;
        this.set = new HashSet<>();
        matrice = new HashSet<>();
        for (int i = 0; i < size; i++){
            for (int k = 0; k < size; k++){
                matrice.add(new Position(i, k));
            }
        }
    }

    @Override
    public boolean hit(Position pos) {
        if (sx.isEmpty()){
            sx = Optional.ofNullable(pos);
            set.add(sx.get());
            return true;
        }
        if (dx.isEmpty()){
            dx = Optional.ofNullable(pos);
            set.add(dx.get());
            return true;
        }
        if (this.set.size() == 2){
            drawRectangle();
            return false;
        }
        expand();
        return false;
    }

    private void drawRectangle(){
        int minX = Math.min(sx.get().x(), dx.get().x());
        int minY = Math.min(sx.get().y(), dx.get().y());
        int maxX = Math.max(sx.get().x(), dx.get().x());
        int maxY = Math.max(sx.get().y(), dx.get().y());
        for (int raw = minX; raw <= maxX; raw++){
            for (int col = minY; col <= maxY; col++){
                this.set.add(new Position(raw, col));
            }
        }
    }

    private void expand(){
        Position minPosition = getMin();
        Position maxPosition = getMax();
        this.set.addAll(this.matrice.stream().filter(i -> i.x() >= minPosition.x() && i.x() <= maxPosition.x() && i.y() >= minPosition.y() && i.y() <= maxPosition.y())
            .filter(x -> (x.x() == minPosition.x() || x.x() == maxPosition.x()) ||  (x.y() == minPosition.y() || x.y() == maxPosition.y())).collect(Collectors.toSet()));
    }

    private Position getMin(){
        return new Position(this.set.stream().map(i -> i.x()).mapToInt(x -> x).min().getAsInt() - 1, this.set.stream().map(i -> i.y()).mapToInt(x -> x).min().getAsInt() - 1);
    }

    private Position getMax(){
        return new Position(this.set.stream().map(i -> i.x()).mapToInt(x -> x).max().getAsInt() + 1, this.set.stream().map(i -> i.y()).mapToInt(x -> x).max().getAsInt() + 1);
    }

    @Override
    public boolean isPresent(Position value) {
        return this.set.contains(value);
    }

    @Override
    public boolean toQuit() {
        return this.set.size() == this.matrice.size();
    }
}
