package a02a.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class ScannerFactoryImpl implements ScannerFactory {

    @Override
    public <X, Y> Scanner<X, List<Y>> collect(Predicate<X> filter, Function<X, Y> mapper) {
        return new Scanner<X, List<Y>>() {

            @Override
            public List<Y> scan(Iterator<X> input) {
                List<Y> list = new ArrayList<>();
                while (input.hasNext()) {
                    X x = input.next();
                    if (filter.test(x)) {
                        list.add(mapper.apply(x));
                    }
                }
                return list;
            }

        };
    }

    @Override
    public <X> Scanner<X, Optional<X>> findFirst(Predicate<X> filter) {
        return new Scanner<X, Optional<X>>() {

            @Override
            public Optional<X> scan(Iterator<X> input) {
                Optional<X> op = Optional.empty();
                while (input.hasNext()) {
                    X x = input.next();
                    if (filter.test(x)) {
                        return Optional.ofNullable(x);
                    }
                }
                return op;
            }

        };

    }

    @Override
    public Scanner<Integer, Optional<Integer>> maximalPrefix() {
        return new Scanner<Integer, Optional<Integer>>() {
            int prec;
            boolean p = true;

            @Override
            public Optional<Integer> scan(Iterator<Integer> input) {
                if (!input.hasNext()) {
                    return Optional.empty();
                }
                if (p && input.hasNext()) {
                    prec = input.next();
                }
                int x = prec;
                while (input.hasNext()) {
                    x = input.next();
                    if (x >= prec) {
                        prec = x;
                    } else {
                        return Optional.ofNullable(prec);
                    }
                }
                return Optional.ofNullable(x);
            }

        };
    }

    @Override
    public Scanner<Integer, List<Integer>> cumulativeSum() {

    }

}
