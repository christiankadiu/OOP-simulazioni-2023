package a03a.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class ParserFactoryImpl implements ParserFactory{

    @Override
    public <X> Parser<X> fromFinitePossibilities(Set<List<X>> acceptedSequences) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                List<X> list = new ArrayList<>();
                while (iterator.hasNext()){
                    list.add(iterator.next());
                }
                return acceptedSequences.contains(list);
            }
            
        };
    }

    @Override
    public <X> Parser<X> fromGraph(X x0, Set<Pair<X, X>> transitions, Set<X> acceptanceInputs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromGraph'");
    }

    @Override
    public <X> Parser<X> fromIteration(X x0, Function<X, Optional<X>> next) {
        return new Parser<X>() {

            Optional<X> x = Optional.of(x0);

            @Override
            public boolean accept(Iterator<X> iterator) {
                while(iterator.hasNext()){
                    if (!x.isPresent() || !iterator.next().equals(x.get())){
                        return false;
                    }
                    if (x.isPresent()){
                       
                    } x = next.apply(x.get());
                }
                if (x.isEmpty()){
                    return true;
                }
                return false;
            }
            
        };
    }

    @Override
    public <X> Parser<X> recursive(Function<X, Optional<Parser<X>>> nextParser, boolean isFinal) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                if (!iterator.hasNext()){
                    return isFinal;
                }
                while (iterator.hasNext()){
                    if (nextParser.apply(iterator.next()).isEmpty()){
                        return false;
                    }
                }
                return true;
            }
            
        };
    }

    @Override
    public <X> Parser<X> fromParserWithInitial(X x, Parser<X> parser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromParserWithInitial'");
    }
    
}
