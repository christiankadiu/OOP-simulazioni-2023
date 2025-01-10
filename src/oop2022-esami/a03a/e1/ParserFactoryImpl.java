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
                while(iterator.hasNext()) {
                    lista.add(iterator.next());
                }
                return acceptedSequences.contains(lista);
            }
            
        };
    }

    @Override
    public <X> Parser<X> fromGraph(X x0, Set<Pair<X, X>> transitions, Set<X> acceptanceInputs) {
        return new Parser<X>() {
            X y0 = x0;
            @Override
            public boolean accept(Iterator<X> iterator) {
                if (!iterator.hasNext()){
                    return false;
                }
                if (iterator.hasNext()){
                    X app = iterator.next();
                    Pair<X, X> p = new Pair<X,X>(y0, app);
                    if (transitions.contains(p)){
                        y0 = app;
                        return true;
                    }
                    return false;
                }
                return true;
            }
            
        };
    }

    @Override
    public <X> Parser<X> fromIteration(X x0, Function<X, Optional<X>> next) {
        return new Parser<X>() {

            X y = x0;

            @Override
            public boolean accept(Iterator<X> iterator) {
                if (!iterator.next().equals(y)){
                    return false;
                }
                if (iterator.hasNext()){
                    X app = iterator.next();
                    if (next.apply(y).isPresent() && next.apply(y).get().equals(app)){
                        y = app;
                        return true;
                    }
                    return false;
                    
                }
                return true;
            }
            
        };
    }

    @Override
    public <X> Parser<X> recursive(Function<X, Optional<Parser<X>>> nextParser, boolean isFinal) {
       return new Parser<X>() {

        @Override
        public boolean accept(Iterator<X> iterator) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'accept'");
        }
        
       };
    }

    @Override
    public <X> Parser<X> fromParserWithInitial(X x, Parser<X> parser) {
        return new Parser<X>() {
            @Override
            public boolean accept(Iterator<X> iterator) {
                int c = 0;
                if(iterator.hasNext()){
                    if (c == 0){
                        c++;
                        if (iterator.next().equals(x)){
                            return true;
                        }
                        return false;
                    }else{
                        return parser.accept(iterator);
                    }
                }
                return false;
            }
        };
    }
    
}
