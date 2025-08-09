package a03b.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicsImpl implements Logics {

    public enum dir {
        UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0);

        int x;
        int y;

        dir(int i, int j) {
            this.x = i;
            this.y = j;
        }
    }

    int size;
    List<Position> set;
    dir direction = dir.UP;
    dir prev = dir.UP;
    int count = 0;

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
        Position p = new Position(last.x() + direction.x, last.y() + direction.y);

        // Verifica se la prossima posizione è valida
        if (isValid(p)) {
            set.add(p); // Aggiunge la nuova posizione valida
        } else {
            // Cambia direzione se la prossima posizione non è valida
            direction = nextDirection(direction);
            p = new Position(last.x() + direction.x, last.y() + direction.y);
            if (isValid(p)) {
                set.add(p);
            }
        }
    }

    // Verifica se una posizione è valida
    private boolean isValid(Position p) {
        return p.x() >= 0 && p.x() < size && p.y() >= 0 && p.y() < size && !set.contains(p);
    }

    // Restituisce la prossima direzione in senso orario
    private dir nextDirection(dir current) {
        switch (current) {
            case UP:
                return dir.RIGHT;
            case RIGHT:
                return dir.DOWN;
            case DOWN:
                return dir.LEFT;
            case LEFT:
                return dir.UP;
            default:
                throw new IllegalStateException("Direzione sconosciuta: " + current);
        }
    }
}
