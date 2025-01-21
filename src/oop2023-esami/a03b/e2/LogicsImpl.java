package a03b.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class LogicsImpl implements Logics {

    private final int width;
    private final int height;
    private final Position target;
    Set<Position> set;

    LogicsImpl(final int width, final int height){
        this.width = width;
        this.height = height;
        Random r = new Random();
        target = new Position(r.nextInt(width), r.nextInt(height));
        this.set = new HashSet<>();
    }

    @Override
    public void hit(Position pos) {
        compose(pos);
    }

    @Override
    public int get(Position value) {
        return value.equals(target) == true ? 1 : this.set.contains(value) == true ? 2 : 0;
    }

    private void compose(Position pos){
        int c = 0;
        for(int x = pos.x(); x < width; x++){
            if (pos.y() - c >= 0 && pos.y() + c < height){
                for (int k = pos.y() - c; k <= pos.y() + c; k++){
                    this.set.add(new Position(x, k));
                }
            }
            c++;
        }
    }

    @Override
    public boolean toQuit() {
        return this.set.contains(target);
    }

}
