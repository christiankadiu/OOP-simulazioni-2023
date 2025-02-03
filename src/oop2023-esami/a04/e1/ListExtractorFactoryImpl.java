package a04.e1;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListExtractorFactoryImpl implements ListExtractorFactory{

    @Override
    public <X> ListExtractor<X, Optional<X>> head() {
        return new ListExtractor<X,Optional<X>>() {

            @Override
            public Optional<X> extract(List<X> list) {
                return list.size() > 0 ? Optional.ofNullable(list.get(0)) : Optional.empty();
            }
            
        };
    }

    @Override
    public <X, Y> ListExtractor<X, List<Y>> collectUntil(Function<X, Y> mapper, Predicate<X> stopCondition) {
        return new ListExtractor<X,List<Y>>() {

            @Override
            public List<Y> extract(List<X> list) {
                return list.stream().takeWhile(i -> stopCondition.test(i)).map(x -> mapper.apply(x)).toList();
            }
            
        };
    }

    @Override
    public <X> ListExtractor<X, List<List<X>>> scanFrom(Predicate<X> startCondition) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scanFrom'");
    }

    @Override
    public <X> ListExtractor<X, Integer> countConsecutive(X x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countConsecutive'");
    }
    
}
