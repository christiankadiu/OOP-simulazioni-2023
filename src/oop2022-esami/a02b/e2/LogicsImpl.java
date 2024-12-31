package a02b.e2;

import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics {

    List<List<Boolean>> matrice;
    boolean quit = false;
    int size;

    LogicsImpl(int size) {
        this.size = size;
        matrice = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            List<Boolean> raw = new ArrayList<>();
            matrice.add(raw);
            for (int k = 0; k < this.size; k++) {
                raw.add(false);
            }
        }
    }

    @Override
    public boolean get(Pair<Integer, Integer> position) {
        if (matrice.get(position.getX()).get(position.getY())) {
            matrice.get(position.getX()).set(position.getY(), false);
            return false;
        } else {
            matrice.get(position.getX()).set(position.getY(), true);
            return true;
        }
    }

    public List<Pair<Integer, Integer>> checkDiagonals() {
        for (int col = 0; col < size; col++) {
            List<Pair<Integer, Integer>> result = checkSingleDiagonal(0, col);
            if (result != null) {
                this.quit = true;
                return result;
            }
        }

        for (int row = 1; row < size; row++) {
            List<Pair<Integer, Integer>> result = checkSingleDiagonal(row, 0);
            if (result != null) {
                this.quit = true;
                return result;
            }
        }

        this.quit = false;
        return new ArrayList<>();
    }

    private List<Pair<Integer, Integer>> checkSingleDiagonal(int startRow, int startCol) {
        List<Pair<Integer, Integer>> diagonal = new ArrayList<>();
        int count = 0;
        int row = startRow, col = startCol;

        while (row < size && col < size) {
            diagonal.add(new Pair<>(row, col));
            if (matrice.get(row).get(col)) {
                count++;
            }
            row++;
            col++;
        }

        if (count == 3) {
            return diagonal;
        }

        return null;
    }

    @Override
    public boolean toQuit() {
        return this.quit;
    }

}
