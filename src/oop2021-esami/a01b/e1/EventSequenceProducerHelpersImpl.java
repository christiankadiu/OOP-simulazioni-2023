package a01b.e1;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class EventSequenceProducerHelpersImpl implements EventSequenceProducerHelpers{

    @Override
    public <E> EventSequenceProducer<E> fromIterator(Iterator<Pair<Double, E>> iterator) {
        return new EventSequenceProducer<E>() {

            @Override
            public Pair<Double, E> getNext() throws NoSuchElementException {
                return iterator.next();
            }
            
        };
    }

    @Override
    public <E> List<E> window(EventSequenceProducer<E> sequence, double fromTime, double toTime) {
        return Stream.generate(() -> {
            try{
                return sequence.getNext();
            }catch(NoSuchElementException e){
                return null;
            }
        }).takeWhile(i -> i != null).filter(x -> (x.get1() >= fromTime && x.get1() <= toTime)).map(t -> t.get2()).toList();
    }

    @Override
    public <E> Iterable<E> asEventContentIterable(EventSequenceProducer<E> sequence) {
        List<E> lista = Stream.generate(() -> {
            try{
                return sequence.getNext();
            }catch(NoSuchElementException e){
                return null;
            }
        }).takeWhile(x -> x != null).map(Pair::get2).toList();
        return lista;
    }

    @Override
    public <E> Optional<Pair<Double, E>> nextAt(EventSequenceProducer<E> sequence, double time) {
        List<Pair<Double, E>> lista = Stream.generate(() -> {
            try{
                return sequence.getNext();
            }catch(NoSuchElementException e){
                return null;
            }
        }).takeWhile(x -> x != null).filter(s -> s.get1() > time).toList();
        return lista.isEmpty() ? Optional.empty() : Optional.ofNullable(lista.get(0));
    }

    @Override
    public <E> EventSequenceProducer<E> filter(EventSequenceProducer<E> sequence, Predicate<E> predicate) {
        return new EventSequenceProducer<E>() {

            @Override
            public Pair<Double, E> getNext() throws NoSuchElementException {
                Pair<Double, E> p = sequence.getNext();
                if (predicate.test(p.get2())){
                    return p;
                }
                throw new NoSuchElementException();
            }
            
        };
    }
    
}
