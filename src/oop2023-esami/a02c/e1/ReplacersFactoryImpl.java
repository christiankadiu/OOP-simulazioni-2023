package a02c.e1;

import java.util.ArrayList;
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
                List<List<T>> out = new ArrayList<>();
                for (int i = 0; i < input.size(); i++) {
                    if (input.get(i).equals(t)) {
                        List<T> tmp = new ArrayList<>(input);
                        tmp.add(i, t);
                        out.add(tmp);
                        break;
                    }
                }
                return out;
            }

        };
    }

    private <T> List<T> modify(List<T> input, List<T> target, int i) {
        if (i != -1) {
            input.addAll(i, target);
            input.remove(i + target.size());
            return input;
        }
        return List.of(); // i == -1 vuol dire che l'elemento non c'è
    }

    @Override
    public <T> Replacer<T> translateLastWith(List<T> target) {
        return new Replacer<T>() {

            @Override
            public List<List<T>> replace(List<T> input, T t) {
                List<List<T>> out = new ArrayList<>();
                int index = input.lastIndexOf(t);
                List<T> tmp = new ArrayList<>(input);
                List<T> tmp2 = modify(tmp, target, index);
                if (!tmp2.isEmpty()) {
                    out.add(tmp2);
                }
                return out;
            }
        };
    }

    private <T> List<T> added(List<T> input, int index) {
        input.remove(index);
        return input;
    }

    @Override
    public <T> Replacer<T> removeEach() {
        return new Replacer<T>() {

            int prec = 0;

            @Override
            public List<List<T>> replace(List<T> input, T t) {
                List<List<T>> out = new ArrayList<>();
                for (int i = 0; i < input.size(); i++) {
                    if (input.get(i).equals(t)) {
                        if (i > prec || i == 0) {
                            prec = i;
                            List<T> tmp = new ArrayList<>(input);
                            out.add(added(tmp, i));
                        }
                    }
                }
                return out;
            }

        };
    }

    @Override
    public <T> Replacer<T> replaceEachFromSequence(List<T> sequence) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'replaceEachFromSequence'");
    }

}
