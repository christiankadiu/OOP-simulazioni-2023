package a03a.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public class ParserFactoryImpl implements ParserFactory {

    @Override
    public <X> Parser<X> fromFinitePossibilities(Set<List<X>> acceptedSequences) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                List<X> list = new ArrayList<>();
                while (iterator.hasNext()) {
                    list.add(iterator.next());
                }
                return acceptedSequences.contains(list);
            }

        };
    }

    @Override
    public <X> Parser<X> fromGraph(X x0, Set<Pair<X, X>> transitions, Set<X> acceptanceInputs) {
        return new Parser<X>() {

            X start = x0;
            X second;

            @Override
            public boolean accept(Iterator<X> iterator) {
                if (iterator.hasNext()) {
                    second = iterator.next();

                    if (transitions.contains(new Pair<X, X>(start, second))) {
                        start = second;
                        return accept(iterator);
                    } else {
                        return false;
                    }
                }
                if (acceptanceInputs.contains(second)) {
                    return true;
                }
                return false;
            }
        };
    }

    @Override
    public <X> Parser<X> fromIteration(X x0, Function<X, Optional<X>> next) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromIteration'");
    }

    @Override
    public <X> Parser<X> recursive(Function<X, Optional<Parser<X>>> nextParser, boolean isFinal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recursive'");
    }

    @Override
    public <X> Parser<X> fromParserWithInitial(X x, Parser<X> parser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromParserWithInitial'");
    }

}
