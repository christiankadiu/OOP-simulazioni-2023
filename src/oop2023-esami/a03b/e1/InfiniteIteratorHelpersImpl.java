package a03b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.Iterator;
import java.util.stream.Stream;

public class InfiniteIteratorHelpersImpl implements InfiniteIteratorsHelpers {

    private <X> InfiniteIterator<X> ofStream(Stream<X> s) {
        Iterator<X> it = s.iterator();
        return () -> it.next();
    }

    @Override
    public <X> InfiniteIterator<X> of(X x) {
        return new InfiniteIterator<X>() {

            @Override
            public X nextElement() {
                return x;
            }

        };
    }

    @Override
    public <X> InfiniteIterator<X> cyclic(List<X> l) {
        return new InfiniteIterator<X>() {
            List<X> lista = new ArrayList<>(l);
            int current = 0;

            @Override
            public X nextElement() {
                if (current == lista.size()) {
                    current = 0;
                }
                return lista.get(current++);
            }

        };
    }

    @Override
    public InfiniteIterator<Integer> incrementing(int start, int increment) {
        return ofStream(Stream.iterate(start, x -> x + increment));
    }

    @Override
    public <X> InfiniteIterator<X> alternating(InfiniteIterator<X> i, InfiniteIterator<X> j) {
        return new InfiniteIterator<X>() {
            boolean current = true;

            @Override
            public X nextElement() {
                if (current) {
                    current = false;
                    return i.nextElement();
                }
                current = true;
                return j.nextElement();
            }

        };
    }

    @Override
    public <X> InfiniteIterator<List<X>> window(InfiniteIterator<X> i, int n) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'window'");
    }

}
