package a05.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {

    private int size;
    private Position computer;
    private Position player;
    private boolean quit = false;

    LogicsImpl(int size) {
        Random r = new Random();
        this.size = size;
        do {
            player = new Position(r.nextInt(size), r.nextInt(size));
            computer = new Position(r.nextInt(size), r.nextInt(size));
        } while (computer.equals(player));

    }

    @Override
    public int get(Position value) {
        if (value.equals(player)) {
            return 1;
        }
        if (value.equals(computer)) {
            return 2;
        }
        return 0;
    }

    @Override
    public void hit(Position pos) {
        if (areAdjacent(player, pos)) {
            player = pos;
            if (areAdjacent(player, computer)) {
                moveComputer();
            }
            if (player.equals(computer)) {
                this.quit = true;
            }
        }
    }

    private void moveComputer() {
        Random r = new Random();
        List<Position> adjacents = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int k = -1; k <= 1; k++) {
                Position p = new Position(computer.x() + i, computer.y() + k);
                if (p.x() >= 0 && p.x() < size && p.y() >= 0 && p.y() < size && !areAdjacent(p, player)) {
                    adjacents.add(p);
                }
            }
        }
        if (adjacents.size() > 0) {
            int index = r.nextInt(adjacents.size());
            computer = adjacents.get(index);
        }
    }

    private boolean areAdjacent(Position p1, Position p2) {
        if (Math.abs(p1.x() - p2.x()) <= 1 && Math.abs(p1.y() - p2.y()) <= 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean toQuit() {
        return this.quit;
    }

}
