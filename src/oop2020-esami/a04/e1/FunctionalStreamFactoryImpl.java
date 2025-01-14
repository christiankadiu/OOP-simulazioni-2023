package a04.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import a04.e1.FunctionalStream.NextResult;

public class FunctionalStreamFactoryImpl implements FunctionalStreamFactory{

    public static class NextResultImpl<E> implements NextResult<E>{

        E e;
        FunctionalStream<E> fs;

        public NextResultImpl(E element, FunctionalStream<E> fs) {
			this.e = element;
			this.fs = fs;
		}

        @Override
        public E getElement() {
            return this.e;
        }

        @Override
        public FunctionalStream<E> getStream() {
            return this.fs;
        }
        
    }

    public class FunctionalStreamImpl<E> implements FunctionalStream<E>{


        @Override
        public List<E> toList(int size) {
            List<E> lista = new ArrayList<>();
            for (int i = 0; i < size; i++){
                lista.add(this.next().getElement());
            }
            return lista;
        }

        @Override
        public Iterator<E> toIterator() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'toIterator'");
        }


        @Override
        public NextResult<E> next() {
            return 
        }

    }

    @Override
    public <E> FunctionalStream<E> fromListRepetitive(List<E> list) {
        
    }

    @Override
    public <E> FunctionalStream<E> iterate(E initial, UnaryOperator<E> op) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterate'");
    }

    @Override
    public <A, B> FunctionalStream<B> map(FunctionalStream<A> fstream, Function<A, B> mapper) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'map'");
    }
    
}
