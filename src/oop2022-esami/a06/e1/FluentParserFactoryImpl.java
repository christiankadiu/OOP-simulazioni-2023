package a06.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

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
            List<Integer> lista = new ArrayList<>();

            @Override
            public FluentParser<List<Integer>> accept(List<Integer> value) {
                if (!value.isEmpty() && !lista.isEmpty()) {
                    if (lista.size() != value.size() - 1) {
                        throw new IllegalStateException();
                    }
                    for (int i = 0; i < lista.size(); i++) {
                        if (!lista.get(i).equals(value.get(i))) {
                            throw new IllegalStateException();
                        }
                    }
                    if (lista.getLast() + 1 != value.getLast()) {
                        throw new IllegalStateException();
                    }
                }
                lista = new ArrayList<>(value);
                return this;
            }
        };
    }

    @Override
    public FluentParser<Integer> repetitiveIncrementalNaturals() {
        return new FluentParser<Integer>() {
            int current = -1;
            int count = 0;

            @Override
            public FluentParser<Integer> accept(Integer value) {
                if (current == 0 && value == 0 && count != 1) {
                    throw new IllegalStateException();
                }
                if (value == 0) {
                    current = -1;
                }
                if (value != current + 1) {
                    throw new IllegalStateException();
                }
                current = value;
                count++;
                return this;
            }

        };
    }

    @Override
    public FluentParser<String> repetitiveIncrementalStrings(String s) {
        return new FluentParser<String>() {
            String current = "";
            int count = 0;

            @Override
            public FluentParser<String> accept(String value) {
                if (current.equals(s) && value.equals(s) && count != 1) {
                    throw new IllegalStateException();
                }
                if (value.equals(s)) {
                    current = "";
                }
                if (!value.equals(current + s)) {
                    throw new IllegalStateException();
                }
                current = value;
                count++;
                return this;
            }

        };
    }

    @Override
    public FluentParser<Pair<Integer, List<String>>> incrementalPairs(int i0, UnaryOperator<Integer> op, String s) {
        return new FluentParser<Pair<Integer, List<String>>>() {
            int current = i0;

            @Override
            public FluentParser<Pair<Integer, List<String>>> accept(Pair<Integer, List<String>> value) {
                if (value.getX() != current) {
                    throw new IllegalStateException();
                }
                if (value.getY().size() != current) {
                    for (String string : value.getY()) {
                        if (!string.equals(s)) {
                            throw new IllegalStateException();
                        }
                    }
                }
                current = op.apply(current);
                return this;
            }

        };
    }

}
