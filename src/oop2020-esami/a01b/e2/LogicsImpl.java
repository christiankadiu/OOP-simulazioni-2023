package a01b.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicsImpl implements Logics {
    int size;
    Pair<Integer, Integer> king;
    List<Pair<Integer, Integer>> pawns;

    LogicsImpl(int size) {
        Random r = new Random();
        this.size = size;
        king = new Pair<Integer, Integer>(size - 1, size - 1);
        this.pawns = new ArrayList<>();
        Pair<Integer, Integer> p;
        for (int i = 0; i < 3; i++) {
            do {
                p = new Pair<Integer, Integer>(r.nextInt(size - 2), r.nextInt(size - 2));
            } while (this.pawns.contains(p));
            pawns.add(p);
        }
    }

    @Override
    public int get(Pair<Integer, Integer> p) {
        if (this.pawns.contains(p)) {
            return 2;
        }
        if (p.equals(king)) {
            return 1;
        }
        return 0;
    }

    @Override
    public void hit(Pair<Integer, Integer> position) {
        if (Math.abs(position.getX() - king.getX()) <= 1 && Math.abs(position.getY() - king.getY()) <= 1) {
            if (pawns.contains(position)) {
                pawns.remove(position);
            }
            king = position;
        }
    }

    @Override
    public boolean toQuit() {
        return this.pawns.isEmpty();
    }

}
