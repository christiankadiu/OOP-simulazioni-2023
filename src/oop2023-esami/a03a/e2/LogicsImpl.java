package a03a.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics{

    public enum dir{
        UP(-1), DOWN(1);
        
        int x;
        int y;

        dir(int y){
            this.y = y;
        }
    }

    final int height;
    final int width;
    final Position target;
    Set<Position> set;
    dir direction = dir.UP;
    int count = 0;
    boolean quit = false;

    LogicsImpl(final int width, final int height){
        this.height = height;
        this.width = width;
        Random r = new Random();
        target = new Position(width - 1, r.nextInt(height));
        this.set = new HashSet<>();
    }

    @Override
    public int get(Position value) {
        return value.equals(target) == true ? 1 : set.contains(value) == true ? 2 : 0;
    }

    @Override
    public void hit(Position pos) {
        if (count < 2){
            this.set = new HashSet<>();
            for (int i = pos.x() , k = pos.y(); i < width; k = k + direction.y, i++){
                var p = new Position(i, k);
                this.set.add(p);
                if (p.y() == 0){
                    direction = dir.DOWN;
                }
                if (p.y() == height - 1){
                    direction = dir.UP;
                }
            }
            if (this.set.contains(target)){
                this.quit = true;
            }
            count++;
        }
    }

    @Override
    public boolean toQuit() {
        return this.quit || this.count >= 2;
    }
}
