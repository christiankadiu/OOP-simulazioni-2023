package a01a.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {
    int size;
    List<Pair<Integer, Integer>> g1;
    List<Pair<Integer, Integer>> g2;
    List<List<Boolean>> matrice;
    boolean t = true;

    LogicsImpl(int size) {
        this.size = size;
        g1 = new ArrayList<>();
        g2 = new ArrayList<>();
        matrice = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Boolean> raw = new ArrayList<>();
            matrice.add(raw);
            for (int k = 0; k < size; k++) {
                raw.add(false);
            }
        }
    }

    @Override
    public boolean hit(Pair<Integer, Integer> position) {
        if (t && !g1.contains(position) && isValid(position)) {
            g1.add(position);
            matrice.get(position.getX()).set(position.getY(), true);
            t = false;
            return true;
        }
        if (!t && !g2.contains(position) && isValid(position)) {
            g2.add(position);
            matrice.get(position.getX()).set(position.getY(), true);
            t = true;
            return true;
        }
        return false;
    }

    private boolean isValid(Pair<Integer, Integer> pos) {
        if (pos.getX() == size - 1) {
            return true;
        }
        if (pos.getX() + 1 < size) {
            if (matrice.get(pos.getX() + 1).get(pos.getY())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean toQuit() {
        boolean b1 = g1.stream().collect(Collectors.groupingBy(i -> i.getX(), Collectors.counting())).values()
                .stream().anyMatch(count -> count >= 3);
        boolean b2 = g1.stream().collect(Collectors.groupingBy(i -> i.getY(), Collectors.counting())).values()
                .stream().anyMatch(count -> count >= 3);
        boolean c1 = g2.stream().collect(Collectors.groupingBy(i -> i.getX(), Collectors.counting())).values()
                .stream().anyMatch(count -> count >= 3);
        boolean c2 = g2.stream().collect(Collectors.groupingBy(i -> i.getY(), Collectors.counting())).values()
                .stream().anyMatch(count -> count >= 3);
        return b1 || b2 || c1 || c2;
    }

}
