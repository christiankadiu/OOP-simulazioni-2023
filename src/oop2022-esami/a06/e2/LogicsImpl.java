package a06.e2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LogicsImpl implements Logics {

    int size;
    List<List<Integer>> list;

    LogicsImpl(final int size) {
        Random r = new Random();
        this.size = size;
        list = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            List<Integer> raw = new ArrayList<>();
            list.add(raw);
            for (int k = 0; k < size; k++) {
                raw.add(r.nextInt(2) + 1);
            }
        }
        print();
    }

    @Override
    public int getNumber(Position pos) {
        return list.get(pos.y()).get(size - pos.x() - 1);
    }

    private void print() {
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                System.out.print(list.get(i).get(k) + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public void collapse() {
        for (int i = 0; i < this.size; i++) {
            coll(list.get(i));
        }
    }

    private void coll(List<Integer> l) {
        Iterator<Integer> it = l.iterator();
        Integer prec = null; // Cambiato da 0 a null per evitare conflitti con il valore 0
        while (it.hasNext()) {
            Integer c = it.next();
            if (prec != null && prec.equals(c)) {
                it.remove(); // Rimuove l'elemento se è uguale al precedente
            } else {
                prec = c; // Aggiorna il precedente solo se l'elemento non è uguale
            }
        }
    }

}
