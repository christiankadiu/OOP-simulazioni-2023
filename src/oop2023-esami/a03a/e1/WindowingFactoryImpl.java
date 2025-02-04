package a03a.e1;

import java.lang.foreign.Linker.Option;
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
                list.add(x);
                if (list.size() < 2) {
                    return Optional.empty();
                }
                return Optional.of(new Pair<>(list.get(list.size() - 2), list.get(list.size() - 1)));
            }

        };
    }

    @Override
    public Windowing<Integer, Integer> sumLastFour() {
        return new Windowing<Integer, Integer>() {

            List<Integer> list = new ArrayList<>();

            @Override
            public Optional<Integer> process(Integer x) {
                list.add(x);
                if (list.size() < 4) {
                    return Optional.empty();
                }
                return Optional.of(sum(list));
            }

        };
    }

    private <X> int sum(List<Integer> list) {
        int somma = 0;
        for (int i = 4; i >= 1; i--) {
            somma = somma + list.get(list.size() - i);
        }
        return somma;
    }

    @Override
    public <X> Windowing<X, List<X>> lastN(int n) {
        return new Windowing<X, List<X>>() {

            List<X> list = new ArrayList<>();

            @Override
            public Optional<List<X>> process(X x) {
                list.add(x);
                if (list.size() < n) {
                    return Optional.empty();
                }
                return Optional.of(list.subList(list.size() - n, list.size()));
            }

        };
    }

    @Override
    public Windowing<Integer, List<Integer>> lastWhoseSumIsAtLeast(int n) {
        return new Windowing<Integer, List<Integer>>() {

            List<Integer> list = new ArrayList<>();

            @Override
            public Optional<List<Integer>> process(Integer x) {
                list.add(x);
                return count(list, n);
            }

        };
    }

    private Optional<List<Integer>> count(List<Integer> list, int n) {
        int somma = 0;
        int i;
        for (i = list.size() - 1; i >= 0; i--) {
            somma = somma + list.get(i);
            if (somma >= n) {
                break;
            }
        }
        if (somma >= n) {
            return Optional.of(list.subList(i, list.size()));
        }
        return Optional.empty();
    }

}
