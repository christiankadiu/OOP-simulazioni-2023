package a03b.e1;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.NoSuchElementException;

public class LazyTreeFactoryImpl implements LazyTreeFactory {

    public class LazyTreeImpl<X> implements LazyTree<X> {

        Optional<X> root;
        Optional<LazyTree<X>> left;
        Optional<LazyTree<X>> right;

        LazyTreeImpl(Optional<X> root, Optional<LazyTree<X>> left, Optional<LazyTree<X>> right) {
            this.root = root;
            this.right = right;
            this.left = left;
        }

        @Override
        public boolean hasRoot() {
            if (this.root.isEmpty()) {
                return true;
            }
            return false;
        }

        @Override
        public X root() {
            return this.root();
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

    }

    @Override
    public <X> LazyTree<X> constantInfinite(X value) {
        return null;
    }

    @Override
    public <X> LazyTree<X> fromMap(X root, Map<X, Pair<X, X>> map) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromMap'");
    }

    @Override
    public <X> LazyTree<X> cons(Optional<X> root, Supplier<LazyTree<X>> leftSupp, Supplier<LazyTree<X>> rightSupp) {
        return new LazyTreeImpl<>(root, Optional.ofNullable(leftSupp.get()), Optional.ofNullable(rightSupp.get()));
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
