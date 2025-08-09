package a01b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FlattenerFactoryImpl implements FlattenerFactory{

    private <X, Y> Flattener<X, Y> generic(Function<List<X>, Y> fun){
        return new Flattener<X,Y>() {

            List<Y> out = new ArrayList<>();
            List<X> tmp = new ArrayList<>();

            @Override
            public List<Y> flatten(List<List<X>> list) {
                for (List<X> list2 : list) {
                    out.add(fun.apply(list2));
                }
                return out;
            }
            
        };
    }

    @Override
    public Flattener<Integer, Integer> sumEach() {
        return generic(l -> l.stream().mapToInt(i -> i).sum());
    }

    @Override
    public <X> Flattener<X, X> flattenAll() {
        return new Flattener<X,X>() {

            @Override
            public List<X> flatten(List<List<X>> list) {
                return list.stream().flatMap(i -> i.stream()).toList();
            }
            
        };
    }

    @Override
    public Flattener<String, String> concatPairs() {
        return new Flattener<String,String>() {
            @Override
            public List<String> flatten(List<List<String>> list) {
                String out = "";
            int count = 0;
            List<String> lista = new ArrayList<>();
                for (List<String> list2 : list) {
                    out =  out + list2.stream().collect(StringBuilder::new, StringBuilder::append,
                    StringBuilder::append).toString();
                    count++;
                    if (count == 2){
                        lista.add(out);
                        out = "";
                        count = 0;
                    }
                }
                if (!out.isEmpty()){
                    lista.add(out);
                }
                return lista;
            }
            
        };
    }

    @Override
    public <I, O> Flattener<I, O> each(Function<List<I>, O> mapper) {
        return new Flattener<I,O>() {

            @Override
            public List<O> flatten(List<List<I>> list) {
                return list.stream().map(i -> mapper.apply(i)).toList();   
            }

            
        };
    }

    @Override
    public Flattener<Integer, Integer> sumVectors() {
        return new Flattener<Integer,Integer>() {

            @Override
            public List<Integer> flatten(List<List<Integer>> list) {
                List<Integer> lista = new ArrayList<>();
                int somma = 0;
                for (int i = 0; i < list.getFirst().size(); i++){
                    somma = 0;
                    for (List<Integer> l : list) {
                        somma = somma + l.get(i);
                    }
                    lista.add(somma);
                }
                return lista;
            }
            
        };
    }
    
}
