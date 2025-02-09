package a02b.e2;

import java.util.HashSet;
import java.util.Set;

import a05.e2.Position;

public class LogicsImpl implements Logics {

    int size;
    Set<Position> activated;
    Set<Position> disabled;
    boolean p = false;

    LogicsImpl(final int size) {
        this.size = size;
        this.activated = new HashSet<>();
        this.disabled = new HashSet<>();
    }

    @Override
    public void hit(Position position) {
        if (!this.activated.contains(position)) {
            this.activated.add(position);
        }
    }

    @Override
    public void get() {
        int raw;
        int i;
        int count = 0;
        for (int col = 0; col < size; col++) {
            i = col;
            raw = 0;
            while (i < size && raw < size) {
                if (this.activated.contains(new Position(i, raw))) {
                    count++;
                }
                i++;
                raw++;
            }
            if (count == 3) {
                disabled.addAll(recupera(col, 0));
                this.p = true;
                break;
            }
        }
    }

    private Set<Position> recupera(int col, int raw) {
        Set<Position> set = new HashSet<>();
        while (col < size && raw < size) {
            set.add(new Position(col, raw));
            col++;
            raw++;
        }
        return set;
    }

    @Override
    public boolean isPresent(Position value) {
        return this.activated.contains(value);
    }

    @Override
    public boolean isDisabled(Position value) {
        return this.disabled.contains(value);
    }

    @Override
    public boolean restart() {
        return this.p;
    }

}
