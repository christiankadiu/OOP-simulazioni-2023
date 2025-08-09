package a02c.e2;

import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics {
    private int size;
    List<Position> list;

    public LogicsImpl(int size) {
        this.size = size;
        list = new ArrayList<>();
    }

    @Override
    public boolean hit(Position position) {
        if (!list.contains(position)) {
            list.add(position);
            return true;
        }
        return false;
    }

    private boolean dist(List<Position> last) {
        for (Position position : last) {
            for (Position position2 : last) {
                if (Math.abs(position.x() - position2.x()) > 1 || Math.abs(position.y() - position2.y()) > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean check() {
        if (this.list.size() >= 4) {
            return dist(this.list.subList(list.size() - 4, list.size()))
                    && !isAtBorder(this.list.subList(list.size() - 4, list.size()));
        }
        return false;
    }

    private boolean isAtBorder(List<Position> last) {
        for (Position position : last) {
            if (position.x() == 0 || position.x() == size - 1 || position.y() == 0 || position.y() == size - 1) {
                return true;
            }
        }
        return false;
    }
}
