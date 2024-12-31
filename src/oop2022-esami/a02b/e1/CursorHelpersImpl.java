package a02b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CursorHelpersImpl implements CursorHelpers {

    @Override
    public <X> Cursor<X> fromNonEmptyList(List<X> list) {
        return new Cursor<X>() {

            int current = 0;

            @Override
            public X getElement() {
                return list.get(current);
            }

            @Override
            public boolean advance() {
                if (++current >= list.size()) {
                    current--;
                    return false;
                }
                return true;
            }

        };
    }

    @Override
    public Cursor<Integer> naturals() {
        return new Cursor<Integer>() {

            int current = 0;

            @Override
            public Integer getElement() {
                return current;
            }

            @Override
            public boolean advance() {
                current++;
                return true;
            }

        };
    }

    @Override
    public <X> Cursor<X> take(Cursor<X> input, int max) {
        return new Cursor<X>() {

            int current = 0;

            @Override
            public X getElement() {
                return input.getElement();
            }

            @Override
            public boolean advance() {
                if (input.advance()) {
                    current++;
                    if (current >= max) {
                        return false;
                    }
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    @Override
    public <X> void forEach(Cursor<X> input, Consumer<X> consumer) {
        do {
            consumer.accept(input.getElement());
        } while (input.advance());
    }

    @Override
    public <X> List<X> toList(Cursor<X> input, int max) {
        List<X> lista = new ArrayList<>();
        lista.add(input.getElement());
        for (int i = 1; i < max && input.advance(); i++) {
            lista.add(input.getElement());
        }
        return lista;
    }

}
