package a01b.e1;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class MathematicalFunctionsFactoryImpl implements MathematicalFunctionsFactory {

    public class MathematicalFunctionImpl<A, B> implements MathematicalFunction<A, B> {

        Predicate<A> pred;
        Function<A, B> fun;

        MathematicalFunctionImpl(Predicate<A> pred, Function<A, B> fun) {
            this.pred = pred;
            this.fun = fun;
        }

        @Override
        public Optional<B> apply(A a) {
            if (inDomain(a)) {
                return Optional.ofNullable(fun.apply(a));
            }
            return Optional.empty();
        }

        @Override
        public boolean inDomain(A a) {
            return pred.test(a);
        }

        @Override
        public <C> MathematicalFunction<A, C> composeWith(MathematicalFunction<B, C> f) {
            return new MathematicalFunctionImpl<A, C>(pred, (a) -> f.apply(fun.apply(a)).get());
        }

        @Override
        public MathematicalFunction<A, B> restrict(Set<A> subDomain) {
            return new MathematicalFunctionImpl<>(i -> subDomain.contains(i), fun);
        }

        @Override
        public MathematicalFunction<A, B> withUpdatedValue(A domainValue, B codomainValue) {
            return new MathematicalFunctionImpl<>(i -> {
                if (i.equals(domainValue)) {
                    return true;
                }
                return inDomain(i);
            }, i -> {
                if (i.equals(domainValue)) {
                    return codomainValue;
                }
                return this.apply(i).get();
            });
        }

    }

    @Override
    public <A, B> MathematicalFunction<A, B> constant(Predicate<A> domainPredicate, B value) {
        return new MathematicalFunctionImpl<>(domainPredicate, a -> value);
    }

    @Override
    public <A, B> MathematicalFunction<A, A> identity(Predicate<A> domainPredicate) {
        return new MathematicalFunctionImpl<>(domainPredicate, a -> a);
    }

    @Override
    public <A, B> MathematicalFunction<A, B> fromFunction(Predicate<A> domainPredicate, Function<A, B> function) {
        return new MathematicalFunctionImpl<>(domainPredicate, function);
    }

    @Override
    public <A, B> MathematicalFunction<A, B> fromMap(Map<A, B> map) {
        return new MathematicalFunctionImpl<>(map::containsKey, map::get);
    }

}
