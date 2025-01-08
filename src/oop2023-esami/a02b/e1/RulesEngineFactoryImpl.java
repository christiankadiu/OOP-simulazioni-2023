package a02b.e1;

import java.util.ArrayList;
import java.util.List;


public class RulesEngineFactoryImpl implements RulesEngineFactory {

    private <T> List<T> replaceAtPosition(int index, List<T> source, List<T> newElems) {
        List<T> result = new ArrayList<>(source);
        result.remove(index);
        result.addAll(index, newElems);
        return result;
    }

    @Override
    public <T> List<List<T>> applyRule(Pair<T, List<T>> rule, List<T> input) {
        List<List<T>> lista = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).equals(rule.get1())) {
                lista.add(replaceAtPosition(i, input, rule.get2()));
            }
        }
        return lista;
    }

    @Override
    public <T> RulesEngine<T> singleRuleEngine(Pair<T, List<T>> rule) {
        return new RulesEngine<T>() {
            List<T> lista;
            boolean can = true;

            @Override
            public void resetInput(List<T> input) {
                lista = new ArrayList<>(input);
                can = true;
            }

            @Override
            public boolean hasOtherSolutions() {
                if (can) {
                    can = false;
                    return true;
                }
                return can;
            }

            @Override
            public List<T> nextSolution() {
                for (int i = 0; i < lista.size(); i++) {
                    if (rule.get1().equals(lista.get(i))) {
                        lista.remove(i);
                        lista.addAll(i, rule.get2());
                    }
                }
                return lista;
            }

        };
    }

    @Override
    public <T> RulesEngine<T> cascadingRulesEngine(Pair<T, List<T>> baseRule, Pair<T, List<T>> cascadeRule) {
        return new RulesEngine<T>() {
            List<T> lista;
            boolean can = true;
            int count = 0;
            @Override
            public void resetInput(List<T> input) {
                lista = new ArrayList<>(input);
            }

            @Override
            public boolean hasOtherSolutions() {
               return count <= 2;
            }

            @Override
            public List<T> nextSolution() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'nextSolution'");
            }
            
        };
    }

    @Override
    public <T> RulesEngine<T> conflictingRulesEngine(Pair<T, List<T>> rule1, Pair<T, List<T>> rule2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'conflictingRulesEngine'");
    }

}
