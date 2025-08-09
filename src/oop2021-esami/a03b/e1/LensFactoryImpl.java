package a03b.e1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LensFactoryImpl implements LensFactory {

    @Override
    public <E> Lens<List<E>, E> indexer(int i) {
        return new Lens<List<E>, E>() {

            @Override
            public E get(List<E> s) {
                return s.get(i);
            }

            @Override
            public List<E> set(E a, List<E> s) {
                List<E> list = new ArrayList<>(s);
                list.set(i, a);
                return list;
            }

        };
    }

    @Override
    public <E> Lens<List<List<E>>, E> doubleIndexer(int i, int j) {
        return new Lens<List<List<E>>, E>() {

            @Override
            public E get(List<List<E>> s) {
                return s.get(i).get(j);
            }

            @Override
            public List<List<E>> set(E a, List<List<E>> s) {
                List<List<E>> list = new LinkedList<>();
                for (List<E> list2 : s) {
                    list.add(new LinkedList<>(list2));
                }
                list.get(i).set(j, a);
                return list;
            }

        };
    }

    @Override
    public <A, B> Lens<Pair<A, B>, A> left() {
        return new Lens<Pair<A, B>, A>() {

            @Override
            public A get(Pair<A, B> s) {
                return s.get1();
            }

            @Override
            public Pair<A, B> set(A a, Pair<A, B> s) {
                return new Pair<A, B>(a, s.get2());
            }

        };
    }

    @Override
    public <A, B> Lens<Pair<A, B>, B> right() {
        return new Lens<Pair<A, B>, B>() {

            @Override
            public B get(Pair<A, B> s) {
                return s.get2();
            }

            @Override
            public Pair<A, B> set(B a, Pair<A, B> s) {
                return new Pair<A, B>(s.get1(), a);
            }

        };
    }

    @Override
    public <A, B, C> Lens<List<Pair<A, Pair<B, C>>>, C> rightRightAtPos(int i) {
        return new Lens<List<Pair<A, Pair<B, C>>>, C>() {

            @Override
            public C get(List<Pair<A, Pair<B, C>>> s) {
                return s.get(i).get2().get2();
            }

            @Override
            public List<Pair<A, Pair<B, C>>> set(C a, List<Pair<A, Pair<B, C>>> s) {
                List<Pair<A, Pair<B, C>>> list = new ArrayList<>();
                for (Pair<A, Pair<B, C>> pair : s) {
                    list.add(new Pair<A, Pair<B, C>>(pair.get1(), pair.get2()));
                }
                list.set(i, new Pair<A, Pair<B, C>>(list.get(i).get1(), new Pair<B, C>(list.get(i).get2().get1(), a)));
                return list;
            }

        };
    }

}
