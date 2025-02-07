package a02b.e1;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

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
                if (++current < list.size()) {
                    return true;
                }
                current--;
                return false;
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

            int current = 1;

            @Override
            public X getElement() {
                return input.getElement();
            }

            @Override
            public boolean advance() {
                if (input.advance() && current++ < max) {
                    return true;
                }
                return false;
            }

        };
    }

    @Override
    public <X> void forEach(Cursor<X> input, Consumer<X> consumer) {
        Stream.generate(() -> {
            X x = input.getElement();
            input.advance();
            return x;
        }).forEach(i -> consumer.accept(i));
    }

    @Override
    public <X> List<X> toList(Cursor<X> input, int max) {
        List<X> list = Stream.generate(() -> {
            X x = input.getElement();
            input.advance();
            return x;
        }).limit(max).toList();
        return list.stream().distinct().toList();
    }

}
