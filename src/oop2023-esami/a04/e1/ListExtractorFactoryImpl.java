package a04.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListExtractorFactoryImpl implements ListExtractorFactory {

    @Override
    public <X> ListExtractor<X, Optional<X>> head() {
        return new ListExtractor<X, Optional<X>>() {

            @Override
            public Optional<X> extract(List<X> list) {
                return list.size() > 0 ? Optional.ofNullable(list.get(0)) : Optional.empty();
            }

        };
    }

    @Override
    public <X, Y> ListExtractor<X, List<Y>> collectUntil(Function<X, Y> mapper, Predicate<X> stopCondition) {
        return new ListExtractor<X, List<Y>>() {

            @Override
            public List<Y> extract(List<X> list) {
                List<Y> tmp = new ArrayList<>();
                for (int i = 0; i < list.size() && !stopCondition.test(list.get(i)); i++) {
                    tmp.add(mapper.apply(list.get(i)));
                }
                return tmp;
            }

        };
    }

    @Override
    public <X> ListExtractor<X, List<List<X>>> scanFrom(Predicate<X> startCondition) {
        return new ListExtractor<X, List<List<X>>>() {

            @Override
            public List<List<X>> extract(List<X> list) {
                List<List<X>> out = new ArrayList<>();
                int index = -1;
                for (int i = 0; i < list.size(); i++) {
                    if (startCondition.test(list.get(i))) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    for (int i = index; i < list.size(); i++) {
                        out.add(bro(list, index, i));
                    }
                }
                return out;
            }

        };
    }

    private <X> List<X> bro(List<X> l, int index, int x) {
        List<X> out = new ArrayList<>();
        for (int i = 0; i <= x - index; i++) {
            out.add(l.get(index + i));
        }
        return out;
    }

    @Override
    public <X> ListExtractor<X, Integer> countConsecutive(X x) {
        return new ListExtractor<X, Integer>() {

            @Override
            public Integer extract(List<X> list) {
                int count = 0;
                boolean p = false;
                int index = list.indexOf(x);
                if (index != -1) {
                    return conteggio(list, index, x);
                }
                return 0;
            }

        };
    }

    private <X> int conteggio(List<X> l, int index, X x) {
        int count = 0;
        for (int i = index; i < l.size(); i++) {
            if (l.get(i).equals(x)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

}
