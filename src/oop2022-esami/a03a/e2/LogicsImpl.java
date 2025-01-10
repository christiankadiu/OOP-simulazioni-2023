package a03a.e2;

import java.util.Random;

public class LogicsImpl implements Logics {

    int size;
    Pair<Integer, Integer> computer;
    Pair<Integer, Integer> player;
    boolean quit = false;

    LogicsImpl(int size) {
        Random r = new Random();
        this.size = size;
        do {
            computer = new Pair<Integer, Integer>(r.nextInt(size), r.nextInt(size));
            player = new Pair<Integer, Integer>(r.nextInt(size), r.nextInt(size));
        } while (computer.equals(player));
    }

    @Override
    public void hit(Pair<Integer, Integer> position) {
        if (isValid(position)) {
            if (position.equals(computer)) {
                player = computer;
                computer = new Pair<Integer, Integer>(null, null);
                System.out.println("hai vinto!!");
                this.quit = true;
                return;
            }
            player = position;
            compute();
        }
    }

    private boolean isValid(Pair<Integer, Integer> pos) {
        return pos.getX() == player.getX() || pos.getY() == player.getY();
    }

    private void compute() {
        Random r = new Random();
        if (computer.getY() == player.getY()
                || computer.getX() == player.getX()) {
            computer = player;
            player = new Pair<Integer, Integer>(null, null);
            System.out.println("vince il computer");
            this.quit = true;
            return;
        }
        int dir = r.nextInt(2);
        switch (dir) {
            case 0:
                Pair<Integer, Integer> p;
                do {
                    p = new Pair<Integer, Integer>(computer.getX(), r.nextInt(size));
                } while (p.equals(computer));
                computer = p;
                break;
            case 1:
                Pair<Integer, Integer> p1;
                do {
                    p1 = new Pair<Integer, Integer>(r.nextInt(size), computer.getY());
                } while (p1.equals(computer));
                computer = p1;
                break;
        }
    }

    @Override
    public int get(Pair<Integer, Integer> value) {
        return value.equals(player) == true ? 1 : value.equals(computer) == true ? 2 : 0;
    }

    @Override
    public boolean toQuit() {
        return this.quit;
    }

}
