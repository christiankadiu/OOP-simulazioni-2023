package a02b.e2;

import java.util.List;

public interface Logics {

    boolean get(Pair<Integer, Integer> position);

    List<Pair<Integer, Integer>> checkDiagonals();

    boolean toQuit();

}
