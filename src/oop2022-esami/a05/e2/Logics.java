package a05.e2;

public interface Logics {

    int get(Position value);

    boolean isDisabled(Position value);

    void hit(Position pos);

    boolean toQuit();

}
