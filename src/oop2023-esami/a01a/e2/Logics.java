package a01a.e2;

import java.awt.Component;
import java.util.List;

public interface Logics {

    void hit(Pair<Integer, Integer> pos);

    boolean isPresent(Pair<Integer, Integer> value);

    List<Pair<Integer, Integer>> getNumbers();

}
