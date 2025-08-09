package a02a.e1;

import java.util.ArrayList;
import java.util.List;


public class RecursiveIteratorHelpersImpl implements RecursiveIteratorHelpers{

    @Override
    public <X> RecursiveIterator<X> fromList(List<X> list) {
        if (list.isEmpty()){
            return null;
        }
        return new RecursiveIterator<X>() {
            int current = 0;

            @Override
            public X getElement() {
                return list.get(current);
            }
            @Override
            public RecursiveIterator<X> next() {
                if (current++ >= list.size() - 1){
                    return null;
                }
                return this;
            }
        };
    }

    @Override
    public <X> List<X> toList(RecursiveIterator<X> input, int max) {
        List<X> lista = new ArrayList<>();
        lista.add(input.getElement());
        for (int i = 1; i < max && input.next() != null; i++){
            lista.add(input.getElement());
        }
        return lista;
    }

    @Override
    public <X, Y> RecursiveIterator<Pair<X, Y>> zip(RecursiveIterator<X> first, RecursiveIterator<Y> second) {
        return new RecursiveIterator<Pair<X,Y>>() {

            @Override
            public Pair<X, Y> getElement() {
                return new Pair<X,Y>(first.getElement(), second.getElement());
            }

            @Override
            public RecursiveIterator<Pair<X, Y>> next() {
                if (first.next() == null || second.next() == null){
                    return null;
                }
                return this;
            }
        };
    }

    @Override
    public <X> RecursiveIterator<Pair<X, Integer>> zipWithIndex(RecursiveIterator<X> iterator) {
        return new RecursiveIterator<Pair<X,Integer>>() {

            int current = 0;
            @Override
            public Pair<X, Integer> getElement() {
                return new Pair<X,Integer>(iterator.getElement(), current++);
            }

            @Override
            public RecursiveIterator<Pair<X, Integer>> next() {
                if (iterator.next() == null){
                    return null;
                }
                return this;
            }
            
        };
    }

    @Override
    public <X> RecursiveIterator<X> alternate(RecursiveIterator<X> first, RecursiveIterator<X> second) {
        return new RecursiveIterator<X>() {
            int current = 0;
            @Override
            public X getElement() {
                if (current == 0){
                    current = 1;
                    return first.getElement();
                }else{
                    current = 0;
                    return second.getElement();
                }
            }

            @Override
            public RecursiveIterator<X> next() {
                if (current == 1){
                    if (first.next() == null){
                        
                    }
                }else{
                    if (second.next() == null){

                    }
                }
                return this;
            }
            
        };
    }
    
}
