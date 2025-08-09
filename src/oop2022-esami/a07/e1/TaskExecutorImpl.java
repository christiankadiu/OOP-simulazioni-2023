package a07.e1;

import java.lang.foreign.Linker.Option;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.List;

public class TaskExecutorImpl implements TaskExecutor {

    @Override
    public <T> Optional<T> executeUntilConditionOrBound(Task<T> task, Predicate<T> resultCondition, int bound) {
        boolean p = false;
        int i;
        for (i = 0; i < bound && p == false; i++) {
            task.computationStep();
            if (resultCondition.test(task.temporaryResult())) {
                p = true;
            }
        }
        if (i == bound) {
            return Optional.empty();
        }
        return Optional.ofNullable(task.temporaryResult());
    }

    @Override
    public <T> Iterator<T> executeForever(Task<T> task) {
        task.reset();
        return Stream.generate(() -> {
            T t = task.temporaryResult();
            task.computationStep();
            return t;
        }).iterator();
    }

}
