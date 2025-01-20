package a01b.e2;

import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics{

    public enum direction{
        RIGHT(1, 0), LEFT(-1, 0);
        
        int x;
        int y;

        direction(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    final private int size;
    private List<Position> list;
    private int count = 0;
    direction dir = direction.LEFT;
    

    LogicsImpl(final int size){
        this.size = size;
        list = new ArrayList<>();
    }

    @Override
    public void hit(Position pos) {
        if (count++ < 5){
            this.list.add(pos);
            return;
        }
        move();
    }

    private void move(){
        this.list = new ArrayList<Position>(this.list.stream().map(i -> new Position(i.x() + dir.x, i.y() + dir.y)).toList());
    }

    @Override
    public List<Position> get() {
        return this.list;
    }
}
