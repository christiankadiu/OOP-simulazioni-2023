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
    private boolean quit = false;
    

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
        for (int i = 0; i < this.list.size(); i++){
            if (list.get(i).x() + dir.x >= 0 && list.get(i).x() + dir.x < size){
                this.list.set(i, new Position(list.get(i).x() + dir.x, list.get(i).y() + dir.y));
            }
        }
        if (this.list.stream().anyMatch(i -> i.x() == size - 1) && dir.equals(direction.RIGHT)){
            quit = true;
            return;
        }
        if (this.list.stream().anyMatch(i -> i.x() == 0)){
            dir = direction.RIGHT;
        }
    }

    @Override
    public List<Position> get() {
        return this.list;
    }


    @Override
    public boolean toQuit() {
        return this.quit;
    }
}
