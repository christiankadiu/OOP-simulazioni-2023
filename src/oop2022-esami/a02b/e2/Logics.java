package a02b.e2;

import a05.e2.Position;

public interface Logics {

    void hit(Position position);

    void get();

    boolean isPresent(Position value);

    boolean isDisabled(Position value);

    boolean restart();

}
