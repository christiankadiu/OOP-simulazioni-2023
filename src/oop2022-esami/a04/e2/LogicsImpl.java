package a04.e2;

import java.util.Random;

public class LogicsImpl implements Logics {

    int size;
    Pair<Integer, Integer> rook;
    Pair<Integer, Integer> king;
    boolean vittoria;
    Boolean quit = false;

    LogicsImpl(int size) {
        Random random = new Random();
        this.size = size;
        do {
            king = new Pair<Integer, Integer>(random.nextInt(size), random.nextInt(size));
            rook = new Pair<Integer, Integer>(random.nextInt(size), random.nextInt(size));
        } while (king.equals(rook));
    }

    @Override
    public boolean hit(Pair<Integer, Integer> pos) {
        if (validPosition(pos)) {
            rook = pos;
            setKing();
            return true;
        }
        return false;
    }

    private boolean validPosition(Pair<Integer, Integer> pos) {
        if (pos.equals(king)) {
            rook = king;
            vittoria = false;
            quit = true;
        }
        if (pos.getX() == rook.getX() && pos.getY() - rook.getY() < size && pos.getY() - rook.getY() > -size) {
            return true;
        }
        if (pos.getY() == rook.getY() && pos.getX() - rook.getX() < size && pos.getX() - rook.getX() > -size) {
            return true;
        }
        return false;
    }

    @Override
    public int isSomething(Pair<Integer, Integer> pos) {
        if (pos.equals(king)) {
            return 1;
        } else if (pos.equals(rook)) {
            return 2;
        } else {
            return 0;
        }
    }

    void setKing() {
        if (king.getX() - rook.getX() <= 1 && king.getY() - rook.getY() <= 1) {
            king = rook;
            vittoria = true;
            quit = true;
            return;
        }
        Random r = new Random();
        int x = r.nextInt(2);
        int y = r.nextInt(2);
        int segno = r.nextInt(2);
        if (segno == 0) {
            king = new Pair<Integer, Integer>(king.getX() + x, king.getY() + y);
        } else {
            king = new Pair<Integer, Integer>(king.getX() - x, king.getY() - y);
        }
    }

    @Override
    public boolean toQuit() {
        return quit;
    }

    @Override
    public boolean win() {
        return vittoria;
    }

}