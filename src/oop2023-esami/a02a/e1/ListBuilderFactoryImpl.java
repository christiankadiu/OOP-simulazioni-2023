package a02a.e1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListBuilderFactoryImpl implements ListBuilderFactory{

    @Override
    public <T> ListBuilder<T> empty() {
        return (ListBuilder<T>) fromList(Stream.of().toList());
    }

    @Override
    public <T> ListBuilder<T> fromElement(T t) {
        return fromList(Stream.of(t).toList());
    }

    public <T> ListBuilder<T> fromList(List<T> list) {
        return new ListBuilder<T>() {

            List<T> l = new ArrayList<>(list);

            @Override
            public ListBuilder<T> add(List<T> list) {
                return fromList(Stream.concat(l.stream(), list.stream()).toList());
            }

            @Override
            public ListBuilder<T> concat(ListBuilder<T> lb) {
                return add(lb.build());
            }

            @Override
            public ListBuilder<T> replaceAll(T t, ListBuilder<T> lb) {
                for (int i = 0; i < l.size(); i++){
                    if (l.get(i).equals(t)){
                        l = modify(l, lb.build(), i);
                    }
                }
                return fromList(l);
            }

            private List<T> modify(List<T> input, List<T> target, int index){
                input.addAll(index, target);
                input.remove(index + target.size());
                return input;
            }

            @Override
            public ListBuilder<T> reverse() {
                var l2 = new ArrayList<>(l);
                Collections.reverse(l2);
                return fromList(l2);
            }

            @Override
            public List<T> build() {
                return this.l;
            }
            
        };
    }
    
    @Override
    public <T> ListBuilder<T> join(T start, T stop, List<ListBuilder<T>> builderList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'join'");
    }

    
    
}
