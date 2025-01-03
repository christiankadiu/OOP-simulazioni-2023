package a07.e1;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TaskExecutorImpl implements TaskExecutor {

    @Override
    public <T> Optional<T> executeUntilConditionOrBound(Task<T> task, Predicate<T> resultCondition, int bound) {
        int count = 0;
        T res = null;
        task.reset();
        while (!resultCondition.test(task.temporaryResult()) && count < bound) {
            task.computationStep();
            res = task.temporaryResult();
            count++;
            if (count == bound) {
                res = null;
            }
        }
        return Optional.ofNullable(res);
    }

    @Override
    public <T> Iterator<T> executeForever(Task<T> task) {
        task.reset();
        return Stream.generate(() -> {
            task.computationStep();
            return task.temporaryResult();
        }).iterator();
    }

}
