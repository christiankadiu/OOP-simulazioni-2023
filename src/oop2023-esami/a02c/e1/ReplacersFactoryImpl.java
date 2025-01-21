package a02c.e1;

import java.util.ArrayList;
import java.util.List;

public class ReplacersFactoryImpl implements ReplacersFactory {

    @Override
    public <T> Replacer<T> noReplacement() {
        return new Replacer<T>() {

            @Override
            public List<List<T>> replace(List<T> input, T t) {
                return List.of();
            }
            
        };
    }

    @Override
    public <T> Replacer<T> duplicateFirst() {
        return new Replacer<T>() {

            @Override
            public List<List<T>> replace(List<T> input, T t) {
                List<T> list = new ArrayList<>(input);
                int i = list.indexOf(t);
                if (i != -1){
                    list.add(i, t);
                    return List.of(list);
                }
                return List.of();
            }
            
        };
    }

    @Override
    public <T> Replacer<T> translateLastWith(List<T> target) {
        return new Replacer<T>() {

            @Override
            public List<List<T>> replace(List<T> input, T t) {
                List<T> list = new ArrayList<>(input);
                int index = list.lastIndexOf(t);
                if (index != -1){
                    list.addAll(index, target);
                    list.remove(index + target.size());
                    return List.of(list);
                }
                return List.of();
            }
            
        };
    }

    @Override
    public <T> Replacer<T> removeEach() {
        return new Replacer<T>() {

            @Override
            public List<List<T>> replace(List<T> input, T t) {
                List<List<T>> out = new ArrayList<>();
                for (int i = 0; i < input.size(); i++){
                    if (input.get(i).equals(t)){
                        out.add(modify(new ArrayList<>(input), i));
                    }
                }
                return out;
             }
        };
    }

    private <T> List<T> modify(List<T> input, int i){
        input.remove(i);
        return input;
    }

    @Override
    public <T> Replacer<T> replaceEachFromSequence(List<T> sequence) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'replaceEachFromSequence'");
    }

    

}
