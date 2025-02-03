package a06.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ZipCombinerFactoryImpl implements ZipCombinerFactory {

    @Override
    public <X, Y> ZipCombiner<X, Y, Pair<X, Y>> classical() {
        return new ZipCombiner<X, Y, Pair<X, Y>>() {

            @Override
            public List<Pair<X, Y>> zipCombine(List<X> l1, List<Y> l2) {
                List<Pair<X, Y>> out = new ArrayList<>();
                for (int i = 0; i < l1.size(); i++) {
                    out.add(new Pair<X, Y>(l1.get(i), l2.get(i)));
                }
                return out;
            }

        };
    }

    @Override
    public <X, Y, Z> ZipCombiner<X, Y, Z> mapFilter(Predicate<X> predicate, Function<Y, Z> mapper) {
        return new ZipCombiner<X, Y, Z>() {

            @Override
            public List<Z> zipCombine(List<X> l1, List<Y> l2) {
                List<Z> out = new ArrayList<>();
                for (int i = 0; i < l1.size(); i++) {
                    if (predicate.test(l1.get(i))) {
                        out.add(mapper.apply(l2.get(i)));
                    }
                }
                return out;
            }

        };
    }

    @Override
    public <Y> ZipCombiner<Integer, Y, List<Y>> taker() {
        return new ZipCombiner<Integer, Y, List<Y>>() {

            @Override
            public List<List<Y>> zipCombine(List<Integer> l1, List<Y> l2) {
                List<List<Y>> out = new ArrayList<>();
                for (int i = 0; i < l1.size(); i++) {
                    out.add(get(l1.get(i), l2));
                }
                return out;
            }

        };
    }

    private <X, Y> List<Y> get(int n, List<Y> l) {
        return l.stream().limit(n).toList();
    }

    @Override
    public <X> ZipCombiner<X, Integer, Pair<X, Integer>> countUntilZero() {
        return new ZipCombiner<X, Integer, Pair<X, Integer>>() {

            @Override
            public List<Pair<X, Integer>> zipCombine(List<X> l1, List<Integer> l2) {
                List<Pair<X, Integer>> out = new ArrayList<>();
                int prec = 0;
                int c;
                for (int i = 0; i < l1.size(); i++) {
                    c = count(l2, prec);
                    out.add(new Pair<X, Integer>(l1.get(i), c));
                    prec = prec + c;
                }
                return out;
            }

        };
    }

    private int count(List<Integer> l, int index) {
        int c = 0;
        for (int i = index; i < l.size(); i++) {
            if (l.get(i) != 0) {
                c++;
            }
            if (l.get(i) == 0 && i != index) {
                break;
            }
        }
        return c;
    }

}
