package a03b.e1;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EquivalenceFactoryImpl implements EquivalenceFactory {

    @Override
    public <X> Equivalence<X> fromPartition(Set<Set<X>> partition) {
        return new Equivalence<X>() {

            Set<Set<X>> set = new HashSet<>(partition);

            @Override
            public boolean areEquivalent(X x1, X x2) {
                return set.stream().anyMatch(i -> i.contains(x1) && i.contains(x2));
            }

            @Override
            public Set<X> domain() {
                return set.stream().flatMap(i -> i.stream()).collect(Collectors.toSet());
            }

            @Override
            public Set<X> equivalenceSet(X x) {
                return set.stream().filter(i -> i.contains(x)).flatMap(i -> i.stream()).collect(Collectors.toSet());
            }

            @Override
            public Set<Set<X>> partition() {
                return this.set;
            }

            @Override
            public boolean smallerThan(Equivalence<X> eq) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'smallerThan'");
            }

        };
    }

    @Override
    public <X> Equivalence<X> fromPredicate(Set<X> domain, BiPredicate<X, X> predicate) {
        return new Equivalence<X>() {

            Set<X> dom = new HashSet<>(domain);

            @Override
            public boolean areEquivalent(X x1, X x2) {
                return predicate.test(x1, x2) && domain.contains(x1) && domain.contains(x2);
            }

            @Override
            public Set<X> domain() {
                return this.dom;
            }

            @Override
            public Set<X> equivalenceSet(X x) {
                return this.dom.stream().filter(i -> predicate.test(i, x)).collect(Collectors.toSet());
            }

            @Override
            public Set<Set<X>> partition() {
                Set<Set<X>> set = new HashSet<>();
                for (X x : domain) {
                    if (!noCont(set, x)) {
                        Set<X> app = new HashSet<>();
                        for (X x2 : domain) {
                            if (!x.equals(x2) && (predicate.test(x, x2))) {
                                app.add(x2);
                            } else {
                                set.add(app);
                                app = new HashSet<>();
                            }
                        }
                    }
                }
                return set;
            }

            @Override
            public boolean smallerThan(Equivalence<X> eq) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'smallerThan'");
            }

        };
    }

    @Override
    public <X> Equivalence<X> fromPairs(Set<Pair<X, X>> pairs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromPairs'");
    }

    @Override
    public <X, Y> Equivalence<X> fromFunction(Set<X> domain, Function<X, Y> function) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromFunction'");
    }

    private <X> boolean noCont(Set<Set<X>> set, X x) {
        return set.stream().anyMatch(i -> i.contains(x));
    }

}
