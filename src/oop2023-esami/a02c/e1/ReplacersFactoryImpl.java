package a02c.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
                List<T> list = new ArrayList<>(input);
                List<List<T>> out = new ArrayList<>();
                int index = input.indexOf(t);
                if (index != -1) {
                    list.add(index, t);
                    out.add(list);
                }
                return out;
            }

        };
    }

    private <T> List<T> removeAtPosition(List<T> input, int index) {
        List<T> out = new ArrayList<>(input);
        out.remove(index);
        return out;
    }

    @Override
    public <T> Replacer<T> translateLastWith(List<T> target) {
        return new Replacer<T>() {

            @Override
            public List<List<T>> replace(List<T> input, T t) {
                List<T> list = new ArrayList<>(input);
                List<List<T>> out = new ArrayList<>();
                int index = input.lastIndexOf(t);
                if (index != -1) {
                    list.addAll(index, target);
                    list.remove(index + target.size());
                    out.add(list);
                }
                return out;
            }

        };
    }

    @Override
    public <T> Replacer<T> removeEach() {
        return new Replacer<T>() {

            @Override
            public List<List<T>> replace(List<T> input, T t) {
                List<T> list = new ArrayList<>(input);
                List<List<T>> out = new ArrayList<>();
                for (int i = 0; i < input.size(); i++) {
                    if (list.get(i).equals(t)) {
                        out.add(removeAtPosition(list, i));
                    }
                }
                return out;
            }

        };
    }

    private <T> List<T> replaceAtPosition(List<T> input, int index, T target) {
        List<T> out = new ArrayList<>(input);
        out.set(index, target);
        return out;
    }

    @Override
    public <T> Replacer<T> replaceEachFromSequence(List<T> sequence) {
        return new Replacer<T>() {

            @Override
            public List<List<T>> replace(List<T> input, T t) {
                Iterator<T> it = sequence.iterator();
                List<T> list = new ArrayList<>(input);
                List<List<T>> out = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(t)) {
                        if (it.hasNext()) {
                            out.add(replaceAtPosition(list, i, it.next()));
                        }
                    }
                }
                return out;
            }

        };
    }

}