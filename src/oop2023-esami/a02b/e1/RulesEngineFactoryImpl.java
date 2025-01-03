package a02b.e1;

import java.util.ArrayList;
import java.util.List;

public class RulesEngineFactoryImpl implements RulesEngineFactory{

    @Override
    public <T> List<List<T>> applyRule(Pair<T, List<T>> rule, List<T> input) {
        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).equals(rule.get1())){
                List<T> l1 = new ArrayList<>(input);
                l1.addAll(i, rule.get2());
                l1.remove(i + rule.get2().size());
                result.add(l1);
            }
        }
        return result;
    }

    @Override
    public <T> RulesEngine<T> singleRuleEngine(Pair<T, List<T>> rule) {
        return new RulesEngine<T>() {
            List<T> lista = new ArrayList<>();
            int current = 0;
            @Override
            public void resetInput(List<T> input) {
                lista = new ArrayList<>(input);
                current = 0;
            }

            @Override
            public boolean hasOtherSolutions() {
                if (current == 1){
                    return false;
                }
                return true;
            }

            @Override
            public List<T> nextSolution() {
                List<T> pol = new ArrayList<>();
                for (int i = 0; i < lista.size(); i++){
                    if (lista.get(i).equals(rule.get1())){
                        lista.addAll(i, rule.get2());
                        lista.remove(i + rule.get2().size());
                    }
                }
                current = 1;
                return lista;
            }
            
        };
    }

    @Override
    public <T> RulesEngine<T> cascadingRulesEngine(Pair<T, List<T>> baseRule, Pair<T, List<T>> cascadeRule) {
        return new RulesEngine<T>() {
            List<T> lista = new ArrayList<>();
            int current = 0;
            @Override
            public void resetInput(List<T> input) {
                lista = new ArrayList<>(input);
                current = 0;
            }

            @Override
            public boolean hasOtherSolutions() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'hasOtherSolutions'");
            }

            @Override
            public List<T> nextSolution() {
                
            }
            
        };
    }

    @Override
    public <T> RulesEngine<T> conflictingRulesEngine(Pair<T, List<T>> rule1, Pair<T, List<T>> rule2) {
        return new RulesEngine<T>() {
            List<T> lista = new ArrayList<>();
            int current = 0;
            List<T> regola1 = new ArrayList<>(rule1.get2());
            List<T> regola2 = new ArrayList<>(rule2.get2());
            @Override
            public void resetInput(List<T> input) {
                lista = new ArrayList<>(input);
                current = 0;
            }

            @Override
            public boolean hasOtherSolutions() {
                if (current == 1){
                    return false;
                }
                return true;
            }

            @Override
            public List<T> nextSolution() {
                List<T> pol = new ArrayList<>();
                for (int i = 0; i < lista.size(); i++){
                    if (lista.get(i).equals(regola1)){
                        regola1.addAll(regola2);
                        lista.addAll(i, regola1);
                        lista.remove(i + rule1.get2().size() + rule2.get2().size());
                    }
                }
                current = 1;
                return lista;
            }
            
        };
    }
    
}
