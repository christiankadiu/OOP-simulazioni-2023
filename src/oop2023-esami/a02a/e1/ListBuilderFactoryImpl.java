package a02a.e1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ListBuilderFactoryImpl implements ListBuilderFactory {

    public class ListBuilderImpl<T> implements ListBuilder<T> {

        List<T> list = new ArrayList<>();

        ListBuilderImpl(List<T> l) {
            list = new ArrayList<>(l);
        }

        @Override
        public ListBuilder<T> add(List<T> list) {
            return new ListBuilderImpl<>(Stream.concat(this.list.stream(), list.stream()).toList());
        }

        @Override
        public ListBuilder<T> concat(ListBuilder<T> lb) {
            return add(lb.build());
        }

        @Override
        public ListBuilder<T> replaceAll(T t, ListBuilder<T> lb) {
            boolean p = true;
            while (p) {
                int index = list.indexOf(t);
                if (index == -1) {
                    p = false;
                }
                replace(list, index, lb.build());
            }
            return this;
        }

        @Override
        public ListBuilder<T> reverse() {
            Collections.reverse(list);
            return this;
        }

        @Override
        public List<T> build() {
            return list;
        }

    }

    private <T> List<T> replace(List<T> input, int i, List<T> l) {
        input.addAll(i, l);
        if (i + l.size() < input.size()) {
            l.remove(i + l.size());
        }
        return l;
    }

    @Override
    public <T> ListBuilder<T> empty() {
        return new ListBuilderImpl<T>((List<T>) Stream.of().toList());
    }

    @Override
    public <T> ListBuilder<T> fromElement(T t) {
        return new ListBuilderImpl<>(Stream.of(t).toList());
    }

    @Override
    public <T> ListBuilder<T> fromList(List<T> list) {
        return new ListBuilderImpl<>(list);
    }

    @Override
    public <T> ListBuilder<T> join(T start, T stop, List<ListBuilder<T>> builderList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'join'");
    }

}
