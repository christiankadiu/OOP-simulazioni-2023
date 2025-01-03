package a06.e2;

import java.awt.RadialGradientPaint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.Icon;

public class LogicsImpl implements Logics {

    int size;
    List<List<Integer>> matrice;
    
    LogicsImpl(int size){
        this.size = size;
        Random r = new Random();
        matrice = new ArrayList<>();
        for (int i = 0; i < size; i++){
            List<Integer> raw = new ArrayList<>();
            matrice.add(raw);
            for (int k = 0; k < size; k++){
                raw.add(r.nextInt(2) + 1);
            }
        }
        print();
    }

    void print(){
        for (List<Integer> list : matrice) {
            for (Integer integer : list) {
                System.out.print(integer + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public int getNumber(int i, int j) {
        return matrice.get(j).get(i);
    }

    @Override
    public void hit() {
        for (List<Integer> list : matrice) {
            checkRiga(list);
        }
        print();
    }

    void checkRiga(List<Integer> lista){
        int c = 0;
        Iterator<Integer> it = lista.iterator();
        int prev = it.next();
        while (it.hasNext()){
            if (prev == it.next()){
                it.remove();
                break;
            }
        }
    }
}
