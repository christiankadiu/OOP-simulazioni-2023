package a04.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class EitherFactoryImpl<A, B> implements EitherFactory{

    @SuppressWarnings("hiding")
    private class EitherImpl<A, B> implements Either<A, B>{

        Optional<B> success;
        Optional<A> failure;

        EitherImpl(Optional<A> fail, Optional<B> succ){
            this.success = succ;
            this.failure = fail;
        }

        @Override
        public boolean isFailure() {
            return this.failure.isPresent();
        }

        @Override
        public boolean isSuccess() {
            return !isFailure();
        }

        @Override
        public Optional<A> getFailure() {
            return this.failure;
        }

        @Override
        public Optional<B> getSuccess() {
            return this.success;
        }

        @Override
        public B orElse(B other) {
            if (isSuccess()){
                return this.success.get();
            }
            return other;
        }

        @Override
        public <B1> Either<A, B1> map(Function<B, B1> function) {
            if (isSuccess()){
                return new EitherImpl<A, B1>(this.failure, Optional.of(function.apply(this.success.get())));
            }
            return failure(null);
        }

        @Override
        public <B1> Either<A, B1> flatMap(Function<B, Either<A, B1>> function) {
            if (isSuccess()){
                return function.apply(this.success.get());
            }
            return failure(this.failure.get());
        }

        @Override
        public <A1> Either<A1, B> filterOrElse(Predicate<B> predicate, A1 failure) {
            if (this.isFailure() || predicate.test(this.success.get())){
                return failure(failure);
            }
            return new EitherImpl<>(Optional.of(failure), this.success);
        }

        @Override
        public <C> C fold(Function<A, C> funFailure, Function<B, C> funSuccess) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'fold'");
        }

    }
   

    @Override
    public <A, B> Either<A, B> success(B b) {
        return new EitherImpl<A,B>(Optional.empty(), Optional.ofNullable(b));
    }

    @Override
    public <A, B> Either<A, B> failure(A a) {
        return new EitherImpl<>(Optional.ofNullable(a), Optional.empty());
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
            if (function.apply(b).getFailure().isPresent()){
                return failure(function.apply(b).getFailure().get());
            }
            lista.add(function.apply(b).getSuccess().get());
        }
        return success(lista);
    }
    
}
