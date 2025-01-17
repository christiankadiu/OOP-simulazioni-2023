package a01a.e2;

import java.util.List;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {

    int size;
    List<Pair<Integer, Integer>> set;
    boolean quit = false;

    LogicsImpl(int size) {
        this.size = size;
        this.set = new ArrayList<>();
    }

    @Override
    public void hit(Pair<Integer, Integer> pos) {
        if (isValid(pos)) {
            this.set.add(pos);
        } else {
            move();
        }
    }

    private boolean isValid(Pair<Integer, Integer> p) {
        for (Pair<Integer, Integer> pair : set) {
            if (Adjacent(pair).contains(p)) {
                return false;
            }
        }
        return true;
    }

    private List<Pair<Integer, Integer>> Adjacent(Pair<Integer, Integer> pos) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (!(dx == 0 && dy == 0)) {
                    int newX = pos.getX() + dx;
                    int newY = pos.getY() + dy;
                    if (newX >= 0 && newX < size && newY >= 0 && newY < size) {
                        list.add(new Pair<>(newX, newY));
                    }
                }
            }
        }
        return list;
    }

    private void move() {
        this.set = new ArrayList<>(this.set.stream().map(pair -> new Pair<>(pair.getX() + 1, pair.getY() - 1))
                .collect(Collectors.toList()));
        for (Pair<Integer, Integer> pair : set) {
            if (pair.getX() < 0 || pair.getX() >= size || pair.getY() < 0 || pair.getY() >= size) {
                this.quit = true;
            }
        }
    }

    @Override
    public boolean isPresent(Pair<Integer, Integer> value) {
        return this.set.contains(value);
    }

    private void print() {
        for (Pair<Integer, Integer> pair : set) {
            System.out.print(pair + "\t");
        }
        System.out.println();
    }

    @Override
    public List<Pair<Integer, Integer>> getNumbers() {
        return this.set;
    }

    @Override
    public boolean toQuit() {
        return this.quit;
    }

}
