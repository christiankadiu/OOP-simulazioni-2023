package a01a.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreeFactoryImpl implements TreeFactory {

    public class TreeImpl<E> implements Tree<E> {

        E root;
        List<Tree<E>> children;

        TreeImpl(E root, List<Tree<E>> children) {
            this.root = root;
            this.children = children;
        }

        @Override
        public E getRoot() {
            return this.root;
        }

        @Override
        public List<Tree<E>> getChildren() {
            return this.children;
        }

        @Override
        public Set<E> getLeafs() {
            return this.children.stream().flatMap(i -> i.getLeafs().stream()).collect(Collectors.toSet());
        }

        @Override
        public Set<E> getAll() {
            return Stream.concat(Stream.of(this.root), this.children.stream()
                    .flatMap(i -> i.getAll().stream())).collect(Collectors.toSet());
        }

    }

    @Override
    public <E> Tree<E> singleValue(E root) {
        return new TreeImpl<E>(root, new ArrayList<>());
    }

    @Override
    public <E> Tree<E> twoChildren(E root, Tree<E> child1, Tree<E> child2) {
        return new TreeImpl<E>(root, Stream.of(child1, child2).toList());
    }

    @Override
    public <E> Tree<E> oneLevel(E root, List<E> children) {
        return new TreeImpl<E>(root, children.stream().map(i -> singleValue(i)).toList());
    }

    @Override
    public <E> Tree<E> chain(E root, List<E> list) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chain'");
    }

}
