package a03b.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {

    int width;
    int height;
    Position target;
    Set<Position> set;
    boolean quit = false;

    LogicsImpl(int width, int height) {
        Random r = new Random();
        this.height = height;
        this.width = width;
        target = new Position(width - 1, r.nextInt(height));
        this.set = new HashSet<>();
        System.out.println("width" + width);
        System.out.println("height" + height);
    }

    @Override
    public int get(Position value) {
        return value.equals(target) == true ? 1 : this.set.contains(value) == true ? 2 : 0;
    }

    @Override
    public void hit(Position pos) {
        boolean q = false;
        int c = 0;
        for (int x = pos.x(); x < width; x++) {
            q = false;
            for (int y = pos.y() - c; y <= pos.y() + c; y++) {
                if (y < height && y >= 0) {
                    this.set.add(new Position(x, y));
                    if (y == 0 || y == height - 1) {
                        q = true;
                    }
                }
            }
            if (q) {
                break;
            }
            c++;
        }
        if (this.set.contains(target)) {
            quit = true;
        }
    }

    @Override
    public boolean toQuit() {
        return this.quit;
    }

}
