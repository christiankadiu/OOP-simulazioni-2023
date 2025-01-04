package a02c.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class ReplacersFactoryImpl implements ReplacersFactory {

    @Override
    public <T> Replacer<T> noReplacement() {
        return new Replacer<T>() {
            @Override
            public List<List<T>> replace(List<T> input, T t) {
                return new ArrayList<>();
            }
        };
    }

    @Override
    public <T> Replacer<T> duplicateFirst() {
        return new Replacer<T>() {

            @Override
            public List<List<T>> replace(List<T> input, T t) {
                List<List<T>> lista = new ArrayList<>();
                List<T> newList = new ArrayList<>(input);
                if (newList.contains(t)) {
                    int index = newList.indexOf(t);
                    newList.add(index, t);
                    lista.add(newList);
                }
                return lista;
            }

        };
    }

    @Override
    public <T> Replacer<T> translateLastWith(List<T> target) {
        return new Replacer<T>() {

            @Override
            public List<List<T>> replace(List<T> input, T t) {
                List<List<T>> lista = new ArrayList<>();
                List<T> newList = new ArrayList<>(input);
                if (newList.contains(t)) {
                    int index = newList.lastIndexOf(t);
                    newList.remove(index);
                    newList.addAll(index, target);
                    lista.add(newList);
                }
                return lista;
            }

        };
    }

    @Override
    public <T> Replacer<T> removeEach() {
        return new Replacer<T>() {
            int current = 0;

            @Override
            public List<List<T>> replace(List<T> input, T t) {
                List<List<T>> lista = new ArrayList<>();
                List<T> newList = new ArrayList<>(input);
                boolean can = true;
                while (can) {
                    if (newList.contains(t)) {
                        int index = newList.indexOf(t);
                        newList.remove(index);
                        List<T> app = new ArrayList<>(input);
                        app.remove(index);
                        lista.add(app);
                    } else {
                        can = false;
                    }
                }
                return lista;
            }

        };
    }

    @Override
    public <T> Replacer<T> replaceEachFromSequence(List<T> sequence) {
        return new Replacer<T>() {
            int current = 0;

            @Override
            public List<List<T>> replace(List<T> input, T t) {
                List<List<T>> lista = new ArrayList<>();
                List<T> newList = new ArrayList<>(input);

                while (current < sequence.size() && newList.contains(t)) {
                    int index = newList.indexOf(t);
                    newList.remove(index);
                    List<T> app = new ArrayList<>(newList);
                    app.add(index, sequence.get(current));
                    current++;
                    lista.add(app);
                }

                return lista;
            }

        };
    }

}
