package a02c.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ListSplitterFactoryImpl implements ListSplitterFactory {

    @Override
    public <X> ListSplitter<X> asPairs() {
        return new ListSplitter<X>() {

            @Override
            public List<List<X>> split(List<X> list) {
                List<List<X>> out = new ArrayList<>();
                int count = 0;
                List<X> app = new ArrayList<>();
                for (X x : list) {
                    app.add(x);
                    count++;
                    if (count == 2) {
                        out.add(app);
                        count = 0;
                        app = new ArrayList<>();
                    }
                }
                return out;
            }

        };
    }

    @Override
    public <X> ListSplitter<X> asTriplets() {
        return new ListSplitter<X>() {

            @Override
            public List<List<X>> split(List<X> list) {
                List<List<X>> out = new ArrayList<>();
                int count = 0;
                List<X> app = new ArrayList<>();
                for (X x : list) {
                    app.add(x);
                    count++;
                    if (count == 3) {
                        out.add(app);
                        count = 0;
                        app = new ArrayList<>();
                    }
                }
                return out;
            }

        };
    }

    @Override
    public <X> ListSplitter<X> asTripletsWithRest() {
        return new ListSplitter<X>() {

            @Override
            public List<List<X>> split(List<X> list) {
                List<List<X>> out = new ArrayList<>();
                int count = 0;
                List<X> app = new ArrayList<>();
                for (X x : list) {
                    app.add(x);
                    count++;
                    if (count == 3) {
                        out.add(app);
                        count = 0;
                        app = new ArrayList<>();
                    }
                }
                if (!app.isEmpty()) {
                    out.add(app);
                }
                return out;
            }

        };
    }

    @Override
    public <X> ListSplitter<X> bySeparator(X separator) {
        return new ListSplitter<X>() {

            @Override
            public List<List<X>> split(List<X> list) {
                List<List<X>> out = new ArrayList<>();
                List<X> app = new ArrayList<>();
                for (X x : list) {
                    if (x.equals(separator)) {
                        out.add(app);
                        out.add(List.of(x));
                        app = new ArrayList<>();
                    } else {
                        app.add(x);
                    }

                }
                if (!app.isEmpty()) {
                    out.add(app);
                }
                return out;
            }

        };
    }

    @Override
    public <X> ListSplitter<X> byPredicate(Predicate<X> predicate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'byPredicate'");
    }

}
