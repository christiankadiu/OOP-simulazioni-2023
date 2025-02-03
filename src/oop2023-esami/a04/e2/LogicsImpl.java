package a04.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;;

public class LogicsImpl implements Logics {
    int size;
    List<Position> list;
    Set<Position> set;

    LogicsImpl(int size) {
        this.size = size;
        list = new ArrayList<>();
        Random r = new Random();
        list.add(new Position(r.nextInt(size), 0));
        set = new HashSet<>();
        set.add(new Position(r.nextInt(size), 0));
    }

    @Override
    public void hit(Position pos) {
        if (!this.set.contains(pos) && pos.y() != 0) {
            if (isInDiagonal(pos)) {
                list.add(pos);
                this.set.add(pos);
                return;
            }
            this.set.add(pos);
        }
    }

    private boolean isInDiagonal(Position pos) {
        Position last = list.get(list.size() - 1);
        if ((pos.x() == last.x() + 1 || pos.x() == last.x() - 1) && pos.y() == last.y() + 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean get(Position value) {
        return this.set.contains(value);
    }

    @Override
    public boolean toQuit() {
        return (this.list.get(list.size() - 1).y() == size - 1);
    }
}
