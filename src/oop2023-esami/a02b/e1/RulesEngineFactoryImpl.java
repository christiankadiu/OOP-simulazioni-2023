package a02b.e1;

import java.util.ArrayList;
import java.util.List;

public class RulesEngineFactoryImpl implements RulesEngineFactory{

    @Override
    public <T> List<List<T>> applyRule(Pair<T, List<T>> rule, List<T> input) {
        List<List<T>> result = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < input.size(); i++) {
            List<T> l = new ArrayList<>(input);
            if (input.get(i).equals(rule.get1())){
                int index = l.indexOf(input.get(i));
                l.remove(index);
                l.addAll(index, rule.get2());
                result.add(l);
                count++;
            }
        }
        return result;
    }

    @Override
    public <T> RulesEngine<T> singleRuleEngine(Pair<T, List<T>> rule) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'singleRuleEngine'");
    }

    @Override
    public <T> RulesEngine<T> cascadingRulesEngine(Pair<T, List<T>> baseRule, Pair<T, List<T>> cascadeRule) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cascadingRulesEngine'");
    }

    @Override
    public <T> RulesEngine<T> conflictingRulesEngine(Pair<T, List<T>> rule1, Pair<T, List<T>> rule2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'conflictingRulesEngine'");
    }

   
    
}
