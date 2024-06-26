package org.example.stream;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.*;


public interface IStream<T> {

    static <T> IStream<T> of(Collection<T> col) {
        //Head
        return new Operation<T, T>(col) {
            @Override
            ISink<T> onWrapSink(ISink<T> downstreamSink) {
                return val -> downstreamSink.accept(val);
            }
        };
    }

    <R> IStream<R> map(Function<T, R> mapper);

    IStream<T> filter(Predicate<T> predicate);

    IStream<T> sorted(Comparator<T> cpt);

    IStream<T> sorted();

    <R> R collect(Supplier<R> supplier, BiConsumer<R, T> accumulator);

    <E> E reduce(E e, BiFunction<T, E, E> operation);
}