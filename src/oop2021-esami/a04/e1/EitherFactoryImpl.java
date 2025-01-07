package a04.e1;

import java.lang.foreign.Linker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EitherFactoryImpl implements EitherFactory {

    public class EitherImpl<A, B> implements Either<A, B> {
        Optional<A> a;
        Optional<B> b;

        private EitherImpl(Optional<A> a, Optional<B> b) {
            if (a.isPresent() && b.isPresent()) {
                throw new IllegalArgumentException();
            }
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean isFailure() {
            return this.a.isPresent();
        }

        @Override
        public boolean isSuccess() {
            return !isFailure();
        }

        @Override
        public Optional<A> getFailure() {
            return a;
        }

        @Override
        public Optional<B> getSuccess() {
            return b;
        }

        @Override
        public B orElse(B other) {
            if (b.isPresent()) {
                return b.get();
            }
            return other;
        }

        @Override
        public <B1> Either<A, B1> map(Function<B, B1> function) {
            if (isSuccess()) {
                return new EitherImpl<A, B1>(Optional.empty(), Optional.of(function.apply(b.get())));
            } else {
                return (Either<A, B1>) failure(a);
            }
        }

        @Override
        public <B1> Either<A, B1> flatMap(Function<B, Either<A, B1>> function) {
            return function.apply(b.get());
        }

        @Override
        public <A1> Either<A1, B> filterOrElse(Predicate<B> predicate, A1 failure) {

        }

        @Override
        public <C> C fold(Function<A, C> funFailure, Function<B, C> funSuccess) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'fold'");
        }

    }

    @Override
    public <A, B> Either<A, B> success(B b) {
        return new EitherImpl<>(Optional.empty(), Optional.of(b));
    }

    @Override
    public <A, B> Either<A, B> failure(A a) {
        return new EitherImpl<>(Optional.of(a), Optional.empty());
    }

    @Override
    public <A> Either<Exception, A> of(Supplier<A> computation) {
        try {
            A a = computation.get();
            return success(a);
        } catch (Exception e) {
            return failure(e);
        }
    }

    @Override
    public <A, B, C> Either<A, List<C>> traverse(List<B> list, Function<B, Either<A, C>> function) {
        List<C> lista = new ArrayList<>();
        for (B b : list) {
            if (function.apply(b).isFailure()) {
                return failure(function.apply(b).getFailure().get());
            }
            lista.add(function.apply(b).getSuccess().get());
        }
        return success(lista);
    }

}
