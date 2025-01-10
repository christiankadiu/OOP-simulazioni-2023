package a03b.e1;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.NoSuchElementException;

public class LazyTreeFactoryImpl implements LazyTreeFactory {

    @Override
    public <X> LazyTree<X> constantInfinite(X value) {

    }

    @Override
    public <X> LazyTree<X> fromMap(X root, Map<X, Pair<X, X>> map) {
        return cons(Optional.ofNullable(root), () -> map.get(root).getX(), () -> map.get(root).getY());
    }

    @Override
    public <X> LazyTree<X> cons(Optional<X> root, Supplier<LazyTree<X>> leftSupp, Supplier<LazyTree<X>> rightSupp) {

        return new LazyTree<X>() {
            Optional<X> rooto = root;
            Optional<LazyTree<X>> left = Optional.ofNullable(leftSupp.get());
            Optional<LazyTree<X>> right = Optional.ofNullable(rightSupp.get());

            @Override
            public boolean hasRoot() {
                if (this.rooto.isEmpty()) {
                    return true;
                }
                return false;
            }

            @Override
            public X root() {
                if (hasRoot()) {
                    return this.rooto.get();
                }
                throw new NoSuchElementException();
            }

            @Override
            public LazyTree<X> left() {
                if (hasRoot()) {
                    if (left.isPresent()) {
                        return left.get();
                    }
                }
                throw new NoSuchElementException();
            }

            @Override
            public LazyTree<X> right() {
                if (hasRoot()) {
                    if (right.isPresent()) {
                        return right.get();
                    }
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public <X> LazyTree<X> fromTwoIterations(X root, UnaryOperator<X> leftOp, UnaryOperator<X> rightOp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromTwoIterations'");
    }

    @Override
    public <X> LazyTree<X> fromTreeWithBound(LazyTree<X> tree, int bound) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromTreeWithBound'");
    }

}
