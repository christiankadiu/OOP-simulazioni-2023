package a06.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ZipCombinerFactoryImpl implements ZipCombinerFactory{

    @Override
    public <X, Y> ZipCombiner<X, Y, Pair<X, Y>> classical() {
        return new ZipCombiner<X,Y,Pair<X,Y>>() {
            List<Pair<X, Y>> lista = new ArrayList<>();
            @Override
            public List<Pair<X, Y>> zipCombine(List<X> l1, List<Y> l2) {
                int current = 0;
                for (X x : l1) {
                    lista.add(new Pair<X,Y>(x, l2.get(current++)));
                }
                return lista;
            }
            
        };
    }

    @Override
    public <X, Y, Z> ZipCombiner<X, Y, Z> mapFilter(Predicate<X> predicate, Function<Y, Z> mapper) {
        return new ZipCombiner<X,Y,Z>() {

            List<Z> lista = new ArrayList<>();
            Y y;

            @Override
            public List<Z> zipCombine(List<X> l1, List<Y> l2) {
                Iterator<Y> it = l2.iterator();
                for (X x : l1) {
                    if (it.hasNext()){
                        y = it.next();
                    }
                    if (predicate.test(x)){
                        lista.add(mapper.apply(y));
                    }
                }
                return lista;
                
            }
            
        };
    }

    @Override
    public <Y> ZipCombiner<Integer, Y, List<Y>> taker() {
        return new ZipCombiner<Integer,Y,List<Y>>() {

            List<List<Y>> out = new ArrayList<>();

            @Override
            public List<List<Y>> zipCombine(List<Integer> l1, List<Y> l2) {
                for (Integer i : l1) {
                    out.add(l2.subList(0, i));
                }
                return out;
            }
            
        };
    }

    @Override
    public <X> ZipCombiner<X, Integer, Pair<X, Integer>> countUntilZero() {
        return new ZipCombiner<X,Integer,Pair<X,Integer>>() {
            List<Pair<X, Integer>> out = new ArrayList<>();
            int count = 0;
            int c;
            @Override
            public List<Pair<X, Integer>> zipCombine(List<X> l1, List<Integer> l2) {
                for (X x : l1) {
                    c = 0;
                    while (l2.get(count++) != 0 && count < l2.size()){
                        c++;
                    }
                    out.add(new Pair<X,Integer>(x, c));
                }
                return out;
            }
            
        };
    }
    
}
