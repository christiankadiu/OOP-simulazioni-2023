package a03c.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class DeterministicParserFactoryImpl implements DeterministicParserFactory {

    @Override
    public DeterministicParser oneSymbol(String s) {
        return new DeterministicParser() {

            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                List<String> lista = new ArrayList<>(tokens);
                Iterator<String> it = lista.iterator();
                if (tokens.contains(s)) {
                    while (it.hasNext()) {
                        if (it.next().equals(s)) {
                            it.remove();
                        }
                    }
                    return Optional.ofNullable(lista);
                }
                return Optional.empty();
            }

        };
    }

    @Override
    public DeterministicParser twoSymbols(String s1, String s2) {
        return new DeterministicParser() {

            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                List<String> lista = new ArrayList<>(tokens);
                Iterator<String> it = lista.iterator();
                if (tokens.contains(s1) && tokens.contains(s2)) {
                    while (it.hasNext()) {
                        String t = it.next();
                        if (t.equals(s1) || t.equals(s2)) {
                            it.remove();
                        }
                    }
                    return Optional.ofNullable(lista);
                }
                return Optional.empty();
            }

        };
    }

    @Override
    public DeterministicParser possiblyEmptyIncreasingSequenceOfPositiveNumbers() {
        return new DeterministicParser() {

            @Override
            public Optional<List<String>> accepts(List<String> tokens) {
                ArrayList<String> lista = new ArrayList<>(tokens);
                List<String> res = new ArrayList<>();
                for (int i = 0; i < tokens.size(); i++) {
                    if (i != 0 && Integer.parseInt(tokens.get(i)) < Integer.parseInt(tokens.get(i - 1))) {
                        res.add(tokens.get(i));
                    }
                }
                return Optional.ofNullable(res);
            }
        };
    }

    @Override
    public DeterministicParser sequenceOfParsersWithDelimiter(String start, String stop, String delimiter,
            DeterministicParser element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sequenceOfParsersWithDelimiter'");
    }

    @Override
    public DeterministicParser sequence(DeterministicParser first, DeterministicParser second) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sequence'");
    }

}
