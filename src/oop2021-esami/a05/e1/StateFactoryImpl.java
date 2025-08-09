package a05.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class StateFactoryImpl implements StateFactory{

    @Override
    public <S, A> State<S, A> fromFunction(Function<S, Pair<S, A>> fun) {
        return new State<S,A>() {

            List<A> lista = new ArrayList<>();

            @Override
            public S nextState(S s) {
                return fun.apply(s).get1();
            }

            @Override
            public A value(S s) {
                lista.add(fun.apply(s).get2());
                return fun.apply(s).get2();
            }

            @Override
            public <B> State<S, B> map(Function<A, B> fun) {
                return fromFunction(s -> new Pair<>(nextState(s), fun.apply(value(s))));
            }

            @Override
            public Iterator<A> iterator(S s0) {
                return new Iterator<A>() {

                    S tmp = s0;

                    @Override
                    public boolean hasNext() {
                        return true;
                    }

                    @Override
                    public A next() {
                        A n = value(tmp);
                        tmp = nextState(tmp);
                        return n;
                    }
                    
                };
            }
            
        };
    }

    @Override
    public <S, A, B> State<S, B> compose(State<S, A> state1, State<S, B> state2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compose'");
    }

    @Override
    public State<Integer, String> incSquareHalve() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'incSquareHalve'");
    }

    @Override
    public State<Integer, Integer> counterOp(CounterOp op) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'counterOp'");
    }
    
}
