package a05.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {
    int size;
    Position computer;
    Position player;
    Set<Position> matrix;

    LogicsImpl(int size) {
        this.size = size;
        Random r = new Random();
        matrix = new HashSet<>();
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (i % 2 == 0) {
                    if (k % 2 != 0) {
                        matrix.add(new Position(i, k));
                    }
                }
                if (i % 2 != 0) {
                    if (k % 2 == 0) {
                        matrix.add(new Position(i, k));
                    }
                }
            }
        }
        do {
            player = new Position(r.nextInt(size), size - 1);
        } while (!matrix.contains(player));
        do {
            computer = new Position(r.nextInt(size), r.nextInt(2));
        } while (!matrix.contains(computer));
    }

    @Override
    public int get(Position value) {
        if (value.equals(player)) {
            return 2;
        } else if (value.equals(computer)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean isDisabled(Position value) {
        return !this.matrix.contains(value);
    }
}
