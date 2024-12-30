package a01b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FlattenerFactoryImpl implements FlattenerFactory {

    private <X, Y> Flattener<X, Y> generic(Predicate<List<X>> test, Function<List<X>, Y> map) {
        return new Flattener<X, Y>() {

            @Override
            public List<Y> flatten(List<List<X>> list) {
                List<Y> out = new ArrayList<>();
                List<X> temp = new ArrayList<>();
                for (List<X> lista : list) {
                    for (X x : lista) {
                        temp.add(x);
                    }
                    if (test.test(temp)) {
                        out.add(map.apply(temp));
                        temp = new ArrayList<>();
                    }
                }
                return out;
            }

        };
    }

    @Override
    public Flattener<Integer, Integer> sumEach() {
        return generic(l -> true, l -> l.stream().mapToInt(i -> i).sum());
    }

    @Override
    public Flattener<String, String> concatPairs() {
        return new Flattener<String, String>() {

            @Override
            public List<String> flatten(List<List<String>> list) {
                int count = 1;
                String s = "";
                String out = "";
                List<String> lista = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    for (int k = 0; k < list.get(i).size(); k++) {
                        s = s + list.get(i).get(k);
                    }
                    out = out + s;
                    s = "";
                    if (count % 2 == 0) {
                        lista.add(out);
                        out = "";
                    }
                    count++;
                }
                if (!out.isEmpty()) {
                    lista.add(out);
                }
                return lista;
            }
        };
    }

    @Override
    public <I, O> Flattener<I, O> each(Function<List<I>, O> mapper) {
        return generic(l -> true, l -> mapper.apply(l));
    }

    @Override
    public Flattener<Integer, Integer> sumVectors() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sumVectors'");
    }

    @Override
    public <X> Flattener<X, X> flattenAll() {
        return new Flattener<X, X>() {

            @Override
            public List<X> flatten(List<List<X>> list) {
                List<X> out = new ArrayList<>();
                for (List<X> list2 : list) {
                    for (X x : list2) {
                        out.add(x);
                    }
                }
                return out;
            }

        };
    }
}
