package a03b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.Iterator;
import java.util.stream.Stream;

public class InfiniteIteratorHelpersImpl implements InfiniteIteratorsHelpers {

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

            int current = 0;
            @Override
            public X nextElement() {
                if (current == l.size()){
                    current = 0;
                }
                return l.get(current++);
            }
            
        };
    }

    @Override
    public InfiniteIterator<Integer> incrementing(int start, int increment) {
        return new InfiniteIterator<Integer>() {

            int current = 0;

            @Override
            public Integer nextElement() {
                return start + (current++ * increment);
            }
            
        };
    }

    @Override
    public <X> InfiniteIterator<X> alternating(InfiniteIterator<X> i, InfiniteIterator<X> j) {
        return new InfiniteIterator<X>() {

            boolean p = true;
            
            @Override
            public X nextElement() {
                if (p){
                    p = false;
                    return i.nextElement();
                }
                p = true;
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
