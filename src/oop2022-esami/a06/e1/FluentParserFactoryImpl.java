package a06.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class FluentParserFactoryImpl implements FluentParserFactory {

    @Override
    public FluentParser<Integer> naturals() {
        return new FluentParser<Integer>() {

            int current = 0;

            @Override
            public FluentParser<Integer> accept(Integer value) {
                if (value != current++) {
                    throw new IllegalStateException();
                }
                return this;
            }

        };
    }

    @Override
    public FluentParser<List<Integer>> incrementalNaturalLists() {
        return new FluentParser<List<Integer>>() {

            List<Integer> list = new ArrayList<>();
            boolean p = true;

            @Override
            public FluentParser<List<Integer>> accept(List<Integer> value) {
                if (p && value.isEmpty()) {
                    p = false;
                    return this;
                }
                if (value.size() == list.size() + 1) {
                    if (list.size() != 0) {
                        if (value.getLast() == list.getLast() + 1) {
                            list = value;
                            return this;
                        }
                        throw new IllegalStateException();
                    } else {
                        if (value.get(0) == 0) {
                            list = value;
                            return this;
                        }
                        throw new IllegalStateException();
                    }
                }
                throw new IllegalStateException();
            }

        };
    }

    @Override
    public FluentParser<Integer> repetitiveIncrementalNaturals() {
        return new FluentParser<Integer>() {

            boolean inizio = true;
            int count = 1;
            int current = 0;
            List<Integer> tmp = new ArrayList<>();

            @Override
            public FluentParser<Integer> accept(Integer value) {
                if (current < count) {
                    if (value == current++) {
                        return this;
                    }
                    throw new IllegalStateException();
                }
                current = 0;
                count++;
                if (value == current++) {
                    return this;
                }
                throw new IllegalStateException();
            }

        };
    }

    @Override
    public FluentParser<String> repetitiveIncrementalStrings(String s) {
        return null;
    }

    @Override
    public FluentParser<Pair<Integer, List<String>>> incrementalPairs(int i0, UnaryOperator<Integer> op, String s) {
        return new FluentParser<Pair<Integer, List<String>>>() {

            Pair<Integer, List<String>> current = new Pair<>(i0, new ArrayList<>());

            @Override
            public FluentParser<Pair<Integer, List<String>>> accept(Pair<Integer, List<String>> value) {
                if (value.equals(current)) {
                    int c = op.apply(current.getX());
                    current = new Pair<Integer, List<String>>(c, getList(c, s));
                    return this;
                }
                throw new IllegalStateException();
            }

        };
    }

    private List<String> getList(int count, String s) {
        return Stream.generate(() -> s).limit(count).toList();
    }

}
