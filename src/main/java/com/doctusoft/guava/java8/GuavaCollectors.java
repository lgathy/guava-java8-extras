package com.doctusoft.guava.java8;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.math.LongMath;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

import static java.util.stream.Collector.Characteristics.*;

public interface GuavaCollectors {
    
    static <T> Optional<T> wrapOptional(java.util.Optional<T> javaOptional) {
        return javaOptional.isPresent() ? Optional.of(javaOptional.get()) : Optional.absent();
    }
    
    static <T> java.util.Optional<T> unwrapOptional(Optional<T> guavaOptional) {
        return guavaOptional.isPresent() ? java.util.Optional.of(guavaOptional.get()) : java.util.Optional.empty();
    }
    
    /**
     * @deprecated Since guava:21.0 user {@link ImmutableList#toImmutableList()}
     */
    @Deprecated
    static <T> Collector<T, ?, ImmutableList<T>> toImmutableList() {
        return (Collector) IMMUTABLE_LIST_COLLECTOR;
    }
    
    @Deprecated
    Collector<Object, ImmutableList.Builder, ImmutableList> IMMUTABLE_LIST_COLLECTOR = Collector.of(
        ImmutableList::builder,
        ImmutableList.Builder::add,
        (a, b) -> a.addAll(b.build()),
        ImmutableList.Builder::build
    );
    
    /**
     * @deprecated Since guava:21.0 user {@link ImmutableSet#toImmutableSet()}
     */
    @Deprecated
    static <T> Collector<T, ?, ImmutableSet<T>> toImmutableSet() {
        return (Collector) IMMUTABLE_SET_COLLECTOR;
    }
    
    @Deprecated
    Collector<Object, ImmutableSet.Builder, ImmutableSet> IMMUTABLE_SET_COLLECTOR = Collector.of(
        ImmutableSet::builder,
        ImmutableSet.Builder::add,
        (a, b) -> a.addAll(b.build()),
        ImmutableSet.Builder::build,
        UNORDERED
    );
    
    /**
     * @deprecated Since guava:21.0 user {@link ImmutableSortedSet#toImmutableSortedSet(Comparator)}
     */
    @Deprecated
    static <T extends Comparable<? super T>> Collector<T, ?, ImmutableSortedSet<T>> toImmutableSortedSet() {
        return (Collector) IMMUTABLE_SORTED_SET_COLLECTOR;
    }
    
    @Deprecated
    Collector<Comparable, TreeSet, ImmutableSortedSet> IMMUTABLE_SORTED_SET_COLLECTOR = Collector.of(
        TreeSet::new,
        TreeSet::add,
        (a, b) -> {
            a.addAll(b);
            return a;
        },
        ImmutableSortedSet::copyOfSorted,
        UNORDERED
    );
    
    Collector<? super Number, LongAccumulator, Long> CHECKED_LONG_SUM = Collector.of(
        () -> new LongAccumulator(LongMath::checkedAdd, 0L),
        (a, t) -> a.accumulate(t.longValue()),
        (a, b) -> {
            a.accumulate(b.get());
            return a;
        },
        LongAccumulator::get,
        UNORDERED, CONCURRENT
    );
    
}
