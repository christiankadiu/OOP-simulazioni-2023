package a01a.e2;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {

    int size;
    List<Pair<Integer, Integer>> set;

    LogicsImpl(int size) {
        this.size = size;
        this.set = new ArrayList<>();
    }

    @Override
    public boolean hit(Pair<Integer, Integer> pos) {
        if (isValid(pos) && !this.set.contains(pos)) {
            this.set.add(pos);
            return true;
        }
        if (!this.set.contains(pos)) {
            move();
        }
        return false;
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
        for (int i = pos.getX() - 1; i <= pos.getX() + 1; i++) {
            list.add(new Pair<Integer, Integer>(i, pos.getY() + 1));
        }
        for (int i = pos.getX() - 1; i <= pos.getX() + 1; i++) {
            list.add(new Pair<Integer, Integer>(i, pos.getY() - 1));
        }
        list.add(new Pair<Integer, Integer>(pos.getX(), pos.getY() + 1));
        list.add(new Pair<Integer, Integer>(pos.getX(), pos.getY() - 1));
        return list;
    }

    private void move() {
        this.set = new ArrayList<>(this.set.stream().map(pair -> new Pair<>(pair.getX() - 1, pair.getY() + 1))
                .collect(Collectors.toList()));
        print();
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

}
