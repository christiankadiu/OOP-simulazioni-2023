package a07.e1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskFactoryImpl implements TaskFactory {

    @Override
    public Task<Integer> counter() {
        return new Task<Integer>() {

            int count = 0;

            @Override
            public void reset() {
                count = 0;
            }

            @Override
            public void computationStep() {
                count++;
            }

            @Override
            public Integer temporaryResult() {
                return count;
            }

        };
    }

    @Override
    public Task<List<Integer>> fibonacciSequenceCreator() {
        return new Task<List<Integer>>() {

            List<Integer> list = new ArrayList<>();

            @Override
            public void reset() {
                list = new ArrayList<>();
            }

            @Override
            public void computationStep() {
                if (list.isEmpty()) {
                    list.add(0);
                    return;
                }
                if (list.size() == 1) {
                    list.add(1);
                    return;
                }
                list.add(list.get(list.size() - 2) + list.get(list.size() - 1));
            }

            @Override
            public List<Integer> temporaryResult() {
                return list;
            }

        };
    }

    @Override
    public Task<Set<Integer>> removeBiggerThan(Set<Integer> set, int bound) {
        return null;
    };
}
