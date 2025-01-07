package a04.e1;

import java.lang.foreign.Linker.Option;
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
                if (list.size() > 0) {
                    return Optional.ofNullable(list.getFirst());
                }
                return Optional.empty();
            }

        };
    }

    @Override
    public <X, Y> ListExtractor<X, List<Y>> collectUntil(Function<X, Y> mapper, Predicate<X> stopCondition) {
        return new ListExtractor<X, List<Y>>() {

            @Override
            public List<Y> extract(List<X> list) {
                List<Y> lista = new ArrayList<>();
                for (X x : list) {
                    if (stopCondition.test(x)) {
                        break;
                    }
                    lista.add(mapper.apply(x));
                }
                return lista;
            }

        };
    }

    @Override
    public <X> ListExtractor<X, List<List<X>>> scanFrom(Predicate<X> startCondition) {
        return new ListExtractor<X, List<List<X>>>() {

            @Override
            public List<List<X>> extract(List<X> list) {
                List<List<X>> lista = new ArrayList<>();
                int index = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (startCondition.test(list.get(i))) {
                        index = i;
                        break;
                    }
                }
                for (int i = index; i < list.size(); i++) {
                    List<X> lista2 = new ArrayList<>();
                    for (int k = 0; k < list.size() - index - 1; k++) {
                        lista2.add(list.get(i + k));
                    }
                    lista.add(lista2);
                }
                return lista;
            }

        };
    }

    @Override
    public <X> ListExtractor<X, Integer> countConsecutive(X x) {
        return new ListExtractor<X, Integer>() {

            @Override
            public Integer extract(List<X> list) {
                if (list.contains(x)) {
                    int count = 0;
                    int index = list.indexOf(x);
                    count++;
                    for (int i = index + 1; i < list.size(); i++) {
                        if (list.get(i).equals(x)) {
                            count++;
                        } else {
                            break;
                        }
                    }
                    return count;
                } else {
                    return 0;
                }
            }

        };
    }

}
