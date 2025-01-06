package a02b.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicsImpl implements Logics {

    int size;
    Set<Pair<Integer, Integer>> set;
    Pair<Integer, Integer> inizio;
    boolean quit = false;

    LogicsImpl(int size){
        this.size = size;
        set = new HashSet<>();
    }

    @Override
    public void hit(Pair<Integer, Integer> pos) {
        if (set.isEmpty()){
            this.inizio = pos;
            inizializzazione(pos);
        } else {
            if (pos.equals(inizio)){
                this.quit = true;
            }
            if (adjacent().contains(pos)){
                // Spostamento di 1 unità, non moltiplicare per 3
                this.inizio = new Pair<>(inizio.getX() + (3*(pos.getX() - inizio.getX())), 
                                         inizio.getY() + (3*(pos.getY() - inizio.getY())));
                inizializzazione(inizio);
            }
        }
    }

    @Override
    public boolean isPresent(Pair<Integer, Integer> value) {
        return this.set.contains(value);
    }

    private void inizializzazione(Pair<Integer, Integer> pos){
        this.set = new HashSet<>();
        set.add(pos);
        for (int i = pos.getX() - 2; i <= pos.getX() + 2; i++){
            Pair<Integer, Integer> p = new Pair<Integer,Integer>(i, pos.getY() - 2);
            if (i >= 0 && i < size && (pos.getY() - 2) >= 0 && (pos.getY() - 2) < size){
                if (!set.contains(p)){
                    set.add(p);
                }
            }
        }
        for (int i = pos.getX() - 2; i <= pos.getX() + 2; i++){
            Pair<Integer, Integer> p = new Pair<Integer,Integer>(i, pos.getY());
            if (i >= 0 && i < size && pos.getY() >= 0 && pos.getY() < size){
                if (!set.contains(p)){
                    set.add(p);
                }
            }
        }
        for (int i = pos.getX() - 2; i <= pos.getX() + 2; i++){
            Pair<Integer, Integer> p = new Pair<Integer,Integer>(i, pos.getY() + 2);
            if (i >= 0 && i < size && (pos.getY() + 2) >= 0 && (pos.getY() + 2) < size){
                if (!set.contains(p)){
                    set.add(p);
                }
            }
        }
        for (int i = pos.getX() - 2; i <= pos.getX() + 2; i = i + 2){
            Pair<Integer, Integer> p = new Pair<Integer,Integer>(i, pos.getY() - 1);
            if (i >= 0 && i < size && (pos.getY() - 1) >= 0 && (pos.getY() - 1) < size){
                if (!set.contains(p)){
                    set.add(p);
                }
            }
        }
        for (int i = pos.getX() - 2; i <= pos.getX() + 2; i = i + 2){
            Pair<Integer, Integer> p = new Pair<Integer,Integer>(i, pos.getY() + 1);
            if (i >= 0 && i < size && (pos.getY() + 1) >= 0 && (pos.getY() + 1) < size){
                if (!set.contains(p)){
                    set.add(p);
                }
            }
        }
    }

    Set<Pair<Integer, Integer>> adjacent(){
        Set<Pair<Integer, Integer>> ritorno = new HashSet<>();
        ritorno.add(new Pair<Integer,Integer>(inizio.getX() + 1, inizio.getY() + 1));
        ritorno.add(new Pair<Integer,Integer>(inizio.getX() - 1, inizio.getY() - 1));
        ritorno.add(new Pair<Integer,Integer>(inizio.getX() + 1, inizio.getY() - 1));
        ritorno.add(new Pair<Integer,Integer>(inizio.getX() - 1, inizio.getY() + 1));
        return ritorno;
    }

    @Override
    public boolean toQuit() {
        return this.quit;
    }

    
}
