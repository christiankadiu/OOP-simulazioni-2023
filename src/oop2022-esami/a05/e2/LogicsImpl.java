package a05.e2;

import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {
    int size;
    Set<Pair<Integer, Integer>> set;
    Pair<Integer, Integer> giocatore;
    Pair<Integer, Integer> computer;
    boolean quit = false;

    LogicsImpl(int size) {
        Random r = new Random();
        this.size = size;
        do {
            computer = new Pair<Integer, Integer>(0, r.nextInt(size));
            giocatore = new Pair<Integer, Integer>(this.size - 1, r.nextInt(size) + 1);
        } while (computer.getY() % 2 != 0 || giocatore.getY() % 2 == 0);
    }

    @Override
    public int isSomething(Pair<Integer, Integer> pos) {
        if (pos.equals(giocatore)) {
            return 1;
        }
        if (pos.equals(computer)) {
            return 2;
        }
        return 0;
    }

    @Override
    public void hit(Pair<Integer, Integer> position) {
        if (position.equals(computer)) {
            quit = true;
            System.out.println("vittoria");
            return;
        }
        if (isValid(position)) {
            giocatore = position;
            setComputer();
        }
    }

    boolean isValid(Pair<Integer, Integer> pos) {
        if (pos.getX() == giocatore.getX() - 1
                && (pos.getY() == giocatore.getY() + 1 || pos.getY() == giocatore.getY() - 1)) {
            if (pos.getX() == 0) {
                quit = true;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean toQuit() {
        return this.quit;
    }

    void setComputer() {
        Random r = new Random();
        if ((computer.getX() - giocatore.getX() >= -1 && computer.getX() - giocatore.getX() <= 1)
                && (computer.getY() - giocatore.getY() <= 1 && computer.getY() - giocatore.getY() >= -1)) {
            System.out.println("sconfitta");
            quit = true;
            return;
        }
        int segno = r.nextInt(2);
        if (segno == 0) {
            computer = new Pair<Integer, Integer>(computer.getX() + 1, computer.getY() + 1);
        } else {
            computer = new Pair<Integer, Integer>(computer.getX() + 1, computer.getY() - 1);
        }
        if (computer.getX() == size - 1) {
            quit = true;
        }
    }

}
