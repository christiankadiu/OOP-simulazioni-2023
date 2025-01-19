package a03b.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicsImpl implements Logics {
    int size;
    List<Position> set;

    LogicsImpl(final int size) {
        this.size = size;
        this.set = new ArrayList<>();
    }

    @Override
    public void hit() {
        Random r = new Random();
        if (this.set.isEmpty()) {
            this.set.add(new Position(r.nextInt(size), r.nextInt(size)));
            return;
        }
        calculate();
    }

    @Override
    public List<Position> getPositons() {
        return this.set;
    }

    private void calculate() {
        Position last = set.get(set.size() - 1);
        Position p = new Position(set.get(set.size() - 1).x(), set.get(set.size() - 1).y() - 1);
        if (p.x() >= 0 && p.x() < size && p.y() >= 0 && p.y() < size) {
            set.add(p);
            return;
        }
        if (last.x() + 1)
    }
}
