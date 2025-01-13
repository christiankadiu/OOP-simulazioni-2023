package a03a.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WindowingFactoryImpl implements WindowingFactory {

    @Override
    public <X> Windowing<X, X> trivial() {
        return new Windowing<X, X>() {

            @Override
            public Optional<X> process(X x) {
                return Optional.ofNullable(x);
            }

        };
    }

    @Override
    public <X> Windowing<X, Pair<X, X>> pairing() {
        return new Windowing<X, Pair<X, X>>() {

            List<X> list = new ArrayList<>();

            @Override
            public Optional<Pair<X, X>> process(X x) {
                if (list.isEmpty()) {
                    list.add(x);
                    return Optional.empty();
                }
                list.add(x);
                return Optional.ofNullable(new Pair<>(list.get(list.size() - 2), list.get(list.size() - 1)));
            }

        };
    }

    @Override
    public Windowing<Integer, Integer> sumLastFour() {
        return new Windowing<Integer, Integer>() {
            List<Integer> list = new ArrayList<>();

            @Override
            public Optional<Integer> process(Integer x) {
                int somma = 0;
                list.add(x);
                if (list.size() < 4) {
                    return Optional.empty();
                }
                for (int i = 1; i <= 4; i++) {
                    somma = somma + list.get(list.size() - i);
                }
                return Optional.of(somma);
            }

        };
    }

    @Override
    public <X> Windowing<X, List<X>> lastN(int n) {
        return new Windowing<X, List<X>>() {
            List<X> list = new ArrayList<>();

            @Override
            public Optional<List<X>> process(X x) {
                List<X> out = new ArrayList<>();

                list.add(x);
                if (list.size() < 4) {
                    return Optional.empty();
                }
                for (int i = list.size() - 4; i <= list.size() - 1; i++) {
                    out.add(list.get(i));
                }
                return Optional.ofNullable(out);
            }

        };
    }

    @Override
    public Windowing<Integer, List<Integer>> lastWhoseSumIsAtLeast(int n) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lastWhoseSumIsAtLeast'");
    }

}
