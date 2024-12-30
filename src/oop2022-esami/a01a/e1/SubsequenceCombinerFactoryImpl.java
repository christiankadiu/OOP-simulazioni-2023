package a01a.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class SubsequenceCombinerFactoryImpl implements SubsequenceCombinerFactory {

    private <X, Y> SubsequenceCombiner<X, Y> generic(Predicate<List<X>> ready, Function<List<X>, Y> mapper) {
        return new SubsequenceCombiner<X, Y>() {

            @Override
            public List<Y> combine(List<X> list) {
                List<Y> lista = new ArrayList<>();
                List<X> tempList = new ArrayList<>();
                for (X x : list) {
                    tempList.add(x);
                    if (ready.test(tempList)) {
                        lista.add(mapper.apply(tempList));
                        tempList = new ArrayList<>();
                    }
                }
                if (!tempList.isEmpty()) {
                    lista.add(mapper.apply(tempList));
                }
                return lista;
            }
        };
    }

    @Override
    public SubsequenceCombiner<Integer, Integer> tripletsToSum() {
        return generic(l -> l.size() == 3, l -> l.stream().mapToInt(i -> i).sum());
    }

    @Override
    public <X> SubsequenceCombiner<X, List<X>> tripletsToList() {
        return generic(l -> l.size() == 3, /* ArrayList::new */ l -> new ArrayList<>(l));
    }

    @Override
    public SubsequenceCombiner<Integer, Integer> countUntilZero() {
        return generic(l -> !l.isEmpty() && l.get(l.size() - 1) == 0,
                l -> l.get(l.size() - 1) == 0 ? l.size() - 1 : l.size());
    }

    @Override
    public <X, Y> SubsequenceCombiner<X, Y> singleReplacer(Function<X, Y> function) {
        return generic(l -> l.size() == 1, l -> function.apply(l.get(0)));
    }

    @Override
    public SubsequenceCombiner<Integer, List<Integer>> cumulateToList(int threshold) {
        return generic(l -> l.stream().mapToInt(i -> i).sum() >= 100, ArrayList::new);
    }

    /*
     * @Override
     * public SubsequenceCombiner<Integer, Integer> tripletsToSum() {
     * return new SubsequenceCombiner<Integer,Integer>() {
     * 
     * @Override
     * public List<Integer> combine(List<Integer> list) {
     * int count = 1;
     * int somma = 0;
     * List<Integer> lista = new ArrayList<>();
     * for (Integer integer : list) {
     * somma = somma + integer;
     * if(count % 3 == 0){
     * lista.add(somma);
     * somma = 0;
     * }
     * count++;
     * }
     * if (somma != 0){
     * lista.add(somma);
     * }
     * return lista;
     * }
     * };
     * }
     * 
     * @Override
     * public <X> SubsequenceCombiner<X, List<X>> tripletsToList() {
     * return new SubsequenceCombiner<X,List<X>>() {
     * 
     * @Override
     * public List<List<X>> combine(List<X> list) {
     * List<List<X>> mat = new ArrayList<>();
     * List<X> raw = new ArrayList<>();
     * for (int i = 0; i < list.size(); i++ ) {
     * raw.add(list.get(i));
     * if (raw.size() == 3){
     * mat.add(raw);
     * raw = new ArrayList<>();
     * }
     * }
     * if (!raw.isEmpty()){
     * mat.add(raw);
     * }
     * return mat;
     * }
     * 
     * };
     * }
     * 
     * @Override
     * public SubsequenceCombiner<Integer, Integer> countUntilZero() {
     * return new SubsequenceCombiner<Integer,Integer>() {
     * 
     * @Override
     * public List<Integer> combine(List<Integer> list) {
     * int count = 0;
     * List<Integer> lista = new ArrayList<>();
     * for (Integer integer : list) {
     * count++;
     * if(integer == 0){
     * lista.add(count - 1);
     * count = 0;
     * }
     * }
     * if(count != 0){
     * lista.add(count);
     * }
     * return lista;
     * }
     * };
     * }
     * 
     * @Override
     * public <X, Y> SubsequenceCombiner<X, Y> singleReplacer(Function<X, Y>
     * function) {
     * return new SubsequenceCombiner<X,Y>() {
     * 
     * @Override
     * public List<Y> combine(List<X> list) {
     * List<Y> lista = new ArrayList<>();
     * for (X x : list) {
     * lista.add(function.apply(x));
     * }
     * return lista;
     * }
     * 
     * };
     * }
     * 
     * @Override
     * public SubsequenceCombiner<Integer, List<Integer>> cumulateToList(int
     * threshold) {
     * return new SubsequenceCombiner<Integer,List<Integer>>() {
     * 
     * @Override
     * public List<List<Integer>> combine(List<Integer> list) {
     * List<List<Integer>> lista = new ArrayList<>();
     * List<Integer> raw = new ArrayList<>();
     * int somma = 0;
     * for (Integer integer : list) {
     * somma = somma + integer;
     * raw.add(integer);
     * if (somma >= 100){
     * lista.add(raw);
     * raw = new ArrayList<>();
     * somma = 0;
     * }
     * }
     * lista.add(raw);
     * return lista;
     * }
     * 
     * };
     * }
     */

}
