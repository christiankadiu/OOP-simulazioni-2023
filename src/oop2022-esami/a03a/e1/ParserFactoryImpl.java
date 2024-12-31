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
                List<X> lista = new ArrayList<>();
                while (iterator.hasNext()){
                    lista.add(iterator.next());
                }   
                if (acceptedSequences.contains(lista)){
                    return true;
                }
                return false;
            }
        };
    }

    @Override
    public <X> Parser<X> fromGraph(X x0, Set<Pair<X, X>> transitions, Set<X> acceptanceInputs) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                if (iterator.hasNext()){
                    if (acceptanceInputs.contains(iterator.next())){
                        return true;
                    }else{
                        Pair<X, X> pair = new Pair<X,X>(x0, iterator.next());
                        if (transitions.contains(pair)){
                            return accept(iterator);
                        }
                    }
                }
                return false;
            }
            
        };
    }

    @Override
    public <X> Parser<X> fromIteration(X x0, Function<X, Optional<X>> next) {
        return new Parser<X>() {
            X prev = x0;
            X curr = next.apply(x0).get();
            @Override
            public boolean accept(Iterator<X> iterator) {
                if (next.apply(prev).isPresent() && next.apply(prev) != Optional.empty()){
                    if (iterator.hasNext()){
                        curr = iterator.next();
                        return accept(iterator);
                    }else{
                        return false;
                    }
                }else{
                    return true;
                }
            }
        };
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
