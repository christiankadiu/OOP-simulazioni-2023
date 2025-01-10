package a03b.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicsImpl implements Logics {

    int size;
    List<Pair<Integer, Integer>> giocatore;
    List<Pair<Integer, Integer>> computer;

    LogicsImpl(int size) {
        this.size = size;
        giocatore = new ArrayList<>();
        computer = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            Pair<Integer, Integer> p1;
            Pair<Integer, Integer> p2;
            p1 = new Pair<Integer, Integer>(size - 1, i);
            do {
                p2 = new Pair<Integer, Integer>(r.nextInt(2), r.nextInt(size));
            } while (computer.contains(p2));
            giocatore.add(p1);
            computer.add(p2);
        }
    }

    @Override
    public int get(Pair<Integer, Integer> value) {
        return giocatore.contains(value) == true ? 2 : computer.contains(value) == true ? 1 : 0;
    }

    @Override
    public void hit(Pair<Integer, Integer> position) {
        if (giocatore.contains(position)) {
            int index = giocatore.indexOf(position);
            calculate(position, index);
        }
    }

    private void calculate(Pair<Integer, Integer> pos, int index) {
        Pair<Integer, Integer> p1 = new Pair<>(pos.getX() - 1, pos.getY() - 1);
        Pair<Integer, Integer> p2 = new Pair<>(pos.getX() - 1, pos.getY() + 1);
        Pair<Integer, Integer> p3 = new Pair<>(pos.getX() - 1, pos.getY());
        if (computer.contains(p1)) {
            computer.remove(p1);
            giocatore.set(index, p1);
            return;
        }
        if (computer.contains(p2)) {
            computer.remove(p2);
            giocatore.set(index, p2);
            return;
        }
        if (!computer.contains(p3) && p3.getX() >= 0 && p3.getX() < size && p3.getY() >= 0 && p3.getY() < size) {
            giocatore.set(index, new Pair<Integer, Integer>(pos.getX() - 1, pos.getY()));
        }
    }

}
