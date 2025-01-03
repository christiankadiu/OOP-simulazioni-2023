package a07.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TaskFactoryImpl implements TaskFactory {

    @Override
    public Task<Integer> counter() {
        return new Task<Integer>() {

            int count = 0;

            @Override
            public void reset() {
                this.count = 0;
            }

            @Override
            public void computationStep() {
                this.count++;
            }

            @Override
            public Integer temporaryResult() {
                return this.count;
            }

        };
    }

    @Override
    public Task<List<Integer>> fibonacciSequenceCreator() {
        return new Task<List<Integer>>() {
            List<Integer> lista;

            @Override
            public void reset() {
                lista = new ArrayList<>();
            }

            @Override
            public void computationStep() {
                if (lista.size() >= 2) {
                    lista.add(lista.get(lista.size() - 1) + lista.get(lista.size() - 2));
                } else {
                    lista.add(lista.size());
                }
            }

            @Override
            public List<Integer> temporaryResult() {
                return lista;
            }

        };
    }

    @Override
    public Task<Set<Integer>> removeBiggerThan(Set<Integer> set, int bound) {
        return new Task<Set<Integer>>() {
            TreeSet<Integer> settone = new TreeSet<>(set);

            @Override
            public void reset() {
                settone = new TreeSet<Integer>(set);
            }

            @Override
            public void computationStep() {
                Integer higher = settone.higher(bound);
                if (higher != null) {
                    settone.remove(higher);
                }
            }

            @Override
            public Set<Integer> temporaryResult() {
                return settone;
            }

        };
    }

}
