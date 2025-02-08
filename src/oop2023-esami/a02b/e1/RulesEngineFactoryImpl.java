package a02b.e1;

import java.util.ArrayList;
import java.util.List;

public class RulesEngineFactoryImpl implements RulesEngineFactory {

    /*
     * for (int i = current; i < list.size(); i++){
     * if (list.get(i).equals(rule.get1())){
     * current = i;
     * return true;
     * }
     * }
     * return false;
     */

    @Override
    public <T> List<List<T>> applyRule(Pair<T, List<T>> rule, List<T> input) {
        List<List<T>> out = new ArrayList<>();
        List<T> tmp = new ArrayList<>(input);
        for (int i = 0; i < tmp.size(); i++) {
            if (tmp.get(i).equals(rule.get1())) {
                out.add(replace(tmp, i, rule.get2()));
            }
        }
        return out;
    }

    private <T> List<T> replace(List<T> input, int i, List<T> rule) {
        List<T> out = new ArrayList<>(input);
        out.addAll(i, rule);
        out.remove(i + rule.size());
        return out;
    }

    @Override
    public <T> RulesEngine<T> singleRuleEngine(Pair<T, List<T>> rule) {
        return new RulesEngine<T>() {

            List<T> list = new ArrayList<>();
            boolean p = true;

            @Override
            public void resetInput(List<T> input) {
                list = new ArrayList<>(input);
                p = true;
            }

            @Override
            public boolean hasOtherSolutions() {
                if (p) {
                    p = false;
                    return true;
                }
                return false;
            }

            @Override
            public List<T> nextSolution() {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(rule.get1())) {
                        list = replace(list, i, rule.get2());
                    }
                }
                return list;
            }

        };
    }

    @Override
    public <T> RulesEngine<T> cascadingRulesEngine(Pair<T, List<T>> baseRule, Pair<T, List<T>> cascadeRule) {
        return new RulesEngine<T>() {

            List<T> list = new ArrayList<>();
            boolean p = true;
            boolean first = true;

            @Override
            public void resetInput(List<T> input) {
                list = new ArrayList<>(input);
                p = true;
            }

            @Override
            public boolean hasOtherSolutions() {
                if (p) {
                    p = false;
                    return true;
                }
                return false;
            }

            @Override
            public List<T> nextSolution() {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(baseRule.get1())) {
                        list = replace(list, i, baseRule.get2());
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(cascadeRule.get1())) {
                        list = replace(list, i, cascadeRule.get2());
                    }
                }
                return list;
            }

        };
    }

    @Override
    public <T> RulesEngine<T> conflictingRulesEngine(Pair<T, List<T>> rule1, Pair<T, List<T>> rule2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'conflictingRulesEngine'");
    }

}
