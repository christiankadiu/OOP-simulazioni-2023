package a03a.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WindowingFactoryImpl implements WindowingFactory {

    @Override
    public <X> Windowing<X, X> trivial() {
        return new Windowing<X,X>() {

            @Override
            public Optional<X> process(X x) {
                return Optional.ofNullable(x);
            }
            
        };
    }

    @Override
    public <X> Windowing<X, Pair<X, X>> pairing() {
        return new Windowing<X,Pair<X,X>>() {

            List<X> list = new ArrayList<>();

            @Override
            public Optional<Pair<X, X>> process(X x) {
                if (list.isEmpty()){
                    list.add(x);
                    return Optional.empty();
                }
                list.add(x);
                return Optional.ofNullable(new Pair<>(list.get(list.size() - 2), list.get(list.size() - 1)));
            }
            
        };
    }

    private int sum(List<Integer> list){
        return list.stream().mapToInt(x -> x).sum();
    }

    @Override
    public Windowing<Integer, Integer> sumLastFour() {
        return new Windowing<Integer,Integer>() {
            List<Integer> list = new ArrayList<>();
            @Override
            public Optional<Integer> process(Integer x) {
                if (list.size() < 3){
                    list.add(x);
                    return Optional.empty();
                }
                list.add(x);
                return Optional.ofNullable(sum(list.subList(list.size() - 4, list.size())));
            }
        };
    }

    @Override
    public <X> Windowing<X, List<X>> lastN(int n) {
        return new Windowing<X,List<X>>() {
            List<X> list = new ArrayList<>();
            @Override
            public Optional<List<X>> process(X x) {
                if (list.size() < n - 1){
                    list.add(x);
                    return Optional.empty();
                }
                list.add(x);
                return Optional.ofNullable(list.subList(list.size() - n , list.size()));
            }
            
        };
    }

    @Override
    public Windowing<Integer, List<Integer>> lastWhoseSumIsAtLeast(int n) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lastWhoseSumIsAtLeast'");
    }

}
