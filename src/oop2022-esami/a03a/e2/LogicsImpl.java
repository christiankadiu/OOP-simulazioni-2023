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
            player = position;
            compute();
        }
    }

    private boolean isValid(Pair<Integer, Integer> pos) {
        return pos.getX() == player.getX() || pos.getY() == player.getY();
    }

    private void compute() {
        Random r = new Random();
        if (Math.abs(computer.getX() - player.getX()) <= 1 && Math.abs(computer.getY() - player.getY()) <= 1) {
            computer = player;
            System.out.println("vince il computer");
            this.quit = true;
            return;
        }
        int dir = r.nextInt(2);
        switch (dir) {
            case 0:
                computer = new Pair<Integer, Integer>(computer.getX(), r.nextInt(size));
                break;
            case 1:
                computer = new Pair<Integer, Integer>(r.nextInt(size), computer.getY());
                break;
        }
    }

}
