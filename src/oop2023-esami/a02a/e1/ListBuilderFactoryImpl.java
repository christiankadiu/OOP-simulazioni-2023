package a02a.e1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class ListBuilderFactoryImpl implements ListBuilderFactory{

    private <R,T> ListBuilder<R> flatMap(ListBuilder<T> lb, Function<T, ListBuilder<R>> fun){
        return new ListBuilderImpl<>(lb.build().stream().flatMap(t -> fun.apply(t).build().stream()));
    }

    private class ListBuilderImpl<T> implements ListBuilder<T>{

        private List<T> lista;

        public ListBuilderImpl(Stream<T> stream){
            lista = stream.toList();
        }

        @Override
        public ListBuilder<T> add(List<T> list) {
            return new ListBuilderImpl<>(Stream.concat(this.lista.stream(), list.stream()));
        }

        @Override
        public ListBuilder<T> concat(ListBuilder<T> lb) {
            return add(lb.build());
        }

        @Override
        public ListBuilder<T> replaceAll(T t, ListBuilder<T> lb) {
            return flatMap(this, tt -> tt.equals(t) ? lb : fromElement(t));
        }

        @Override
        public ListBuilder<T> reverse() {
            var l = new ArrayList<>(lista);
            Collections.reverse(l);
            return new ListBuilderImpl<>(l.stream());
        }

        @Override
        public List<T> build() {
            return this.lista;
        }

    }

    @Override
    public <T> ListBuilder<T> empty() {
        return new ListBuilderImpl<>(Stream.empty());
    }

    @Override
    public <T> ListBuilder<T> fromElement(T t) {
        return new ListBuilderImpl<>(Stream.of(t));
    }

    @Override
    public <T> ListBuilder<T> fromList(List<T> list) {
        return new ListBuilderImpl<>(list.stream());
    }

    @Override
    public <T> ListBuilder<T> join(T start, T stop, List<ListBuilder<T>> builderList) {
        return null;
    }


    
}
