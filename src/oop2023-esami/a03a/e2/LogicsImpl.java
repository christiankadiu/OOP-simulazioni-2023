package a03a.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

public class LogicsImpl implements Logics {

    int width;
    int height;
    Pair<Integer, Integer> target;
    Set<Pair<Integer, Integer>> set;

    LogicsImpl(int width, int height) {
        Random r = new Random();
        this.width = height;
        this.height = width;
        target = new Pair<Integer, Integer>(height - 1, r.nextInt(width));
        System.out.println(target);
        set = new HashSet<>();
    }

    @Override
    public boolean get(Pair<Integer, Integer> pos) {
        return pos.equals(target);
    }

    @Override
    public void hit(Pair<Integer, Integer> pos) {
        int i = pos.getX();
        int k = pos.getY();
        int a = -1;
        while (i < height) {
            Pair<Integer, Integer> p = new Pair<Integer, Integer>(i, k);
            set.add(p);
            if (k == 0 || k == width - 1) {
                a = -a;
            }
            k = k + a;
            i++;
        }
    }

    @Override
    public boolean isPresent(Pair<Integer, Integer> value) {
        return this.set.contains(value);
    }

}
