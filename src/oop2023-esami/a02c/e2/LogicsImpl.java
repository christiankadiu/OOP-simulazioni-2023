package a02c.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {

    int size;
    Optional<Position> center;
    Set<Position> matrice;
    Set<Position> quad;
    List<Position> vertici;

    LogicsImpl(int size){
        this.size = size;
        center = Optional.empty();
        matrice = new HashSet<>();
        for (int i = 0; i < size; i++){
            for (int k = 0; k < size; k++){
                this.matrice.add(new Position(i, k));
            }
        }
        quad = new HashSet<>();
        vertici = new ArrayList<>();
    }

    @Override
    public void hit(Position pos) {
        if (center.isEmpty()){
            center = Optional.of(pos);
            compose();
            return;
        }
        if (vertici.contains(pos)){
            if (pos.equals(vertici.get(0))){
                recompose(new Position(pos.x() - 1, pos.y() - 1), vertici.get(2));
                updateVertexes();
                return;
            }
            if (pos.equals(vertici.get(1))){
                recompose(new Position(pos.x() + 1, pos.y() - 1), vertici.get(3));
                updateVertexes();
                return;
            }
            if (pos.equals(vertici.get(2))){
                recompose(new Position(pos.x() + 1, pos.y() + 1), vertici.get(0));
                updateVertexes();
                return;
            }
            if (pos.equals(vertici.get(3))){
                recompose(new Position(pos.x() - 1, pos.y() + 1), vertici.get(1));
                updateVertexes();
                return;
            }
        }
    }

    private void compose(){
        Position sx = new Position(center.get().x() - 1, center.get().y() - 1);
        Position dx = new Position(center.get().x() + 1, center.get().y() + 1);
        this.quad = this.matrice.stream().filter(i -> i.x() >= sx.x() && i.x() <= dx.x() && i.y() >= sx.y() && i.y() <= dx.y())
            .filter(i -> i.x() == sx.x() || i.x() == dx.x() || i.y() == sx.y() || i.y() == dx.y()).collect(Collectors.toSet());
            vertici.add((new Position(this.quad.stream().map(i -> i.x()).mapToInt(x -> x).min().getAsInt(), this.quad.stream().map(i -> i.y()).mapToInt(x -> x).min().getAsInt())));
            vertici.add((new Position(this.quad.stream().map(i -> i.x()).mapToInt(x -> x).max().getAsInt(), this.quad.stream().map(i -> i.y()).mapToInt(x -> x).min().getAsInt())));
            vertici.add((new Position(this.quad.stream().map(i -> i.x()).mapToInt(x -> x).max().getAsInt(), this.quad.stream().map(i -> i.y()).mapToInt(x -> x).max().getAsInt())));
            vertici.add((new Position(this.quad.stream().map(i -> i.x()).mapToInt(x -> x).min().getAsInt(), this.quad.stream().map(i -> i.y()).mapToInt(x -> x).max().getAsInt())));
    }

    @Override
    public boolean isPresent(Position value) {
       return this.quad.contains(value);
    }

    private void updateVertexes(){
        vertici.set(0, (new Position(this.quad.stream().map(i -> i.x()).mapToInt(x -> x).min().getAsInt(), this.quad.stream().map(i -> i.y()).mapToInt(x -> x).min().getAsInt())));
        vertici.set(1, (new Position(this.quad.stream().map(i -> i.x()).mapToInt(x -> x).max().getAsInt(), this.quad.stream().map(i -> i.y()).mapToInt(x -> x).min().getAsInt())));
        vertici.set(2, (new Position(this.quad.stream().map(i -> i.x()).mapToInt(x -> x).max().getAsInt(), this.quad.stream().map(i -> i.y()).mapToInt(x -> x).max().getAsInt())));
        vertici.set(3, (new Position(this.quad.stream().map(i -> i.x()).mapToInt(x -> x).min().getAsInt(), this.quad.stream().map(i -> i.y()).mapToInt(x -> x).max().getAsInt())));
    }

    private void recompose(Position p1, Position p2){
        int minX = Math.min(p1.x(), p2.x());
        int minY = Math.min(p1.y(), p2.y());
        int maxX = Math.max(p1.x(), p2.x());
        int maxY = Math.max(p1.y(), p2.y());
        this.quad = new HashSet<>();
        for (int raw = minX; raw <= maxX; raw++){
            for (int col = minY; col <= maxY; col++){
                if ((raw == minX || raw == maxX) || (col == minY || col == maxY)){
                    quad.add(new Position(raw, col));
                }
            }
        }
    }

    private void printVertexes(){

    }

}
