package a05.e2;

import java.util.Random;

public class LogicsImpl implements Logics {

    private int size;
    Position player;
    Position computer;
    private boolean quit = false;

    public LogicsImpl(int size) {
        Random r = new Random();
        this.size = size;
        do {
            player = new Position(r.nextInt(size), r.nextInt(size));
            computer = new Position(r.nextInt(size), r.nextInt(size));
        } while (Adjacent(player, computer));
    }

    private boolean Adjacent(Position x, Position y) {
        return Math.abs(x.x() - y.x()) <= 1 && Math.abs(x.y() - y.y()) <= 1;
    }

    @Override
    public int isSomething(a05.e2.Position value) {
        return value.equals(player) == true ? 1 : value.equals(computer) == true ? 2 : 0;
    }

    @Override
    public void hit(Position pos) {
        if (Adjacent(pos, player)) {
            player = pos;
            if (player.equals(computer)) {
                this.quit = true;
            }
            if (isAttacked()) {
                moveComputer();
            }
        }
    }

    private void moveComputer() {
        if (!isAtBorder()) {
            Random r = new Random();
            int x = r.nextInt(2);
            int y = r.nextInt(2);
            int segno = r.nextInt(2);
            if (segno == 0) {
                if (computer.x() + x >= 0 && computer.x() + x < size && computer.y() + y >= 0
                        && computer.y() + y < size) {
                    computer = new Position(computer.x() + x, computer.y() + y);
                }
            }
            if (segno == 1) {
                if (computer.x() - x >= 0 && computer.x() - x < size && computer.y() - y >= 0
                        && computer.y() - y < size) {
                    computer = new Position(computer.x() - x, computer.y() - y);
                }
            }
        }

    }

    @Override
    public boolean toQuit() {
        return this.quit;
    }

    private boolean isAttacked() {
        return Adjacent(computer, player);
    }

    private boolean isAtBorder() {
        if (computer.x() == 0 || computer.x() == size - 1 || computer.y() == 0 || computer.y() == size - 1) {
            return true;
        }
        return false;
    }

}
