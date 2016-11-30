package com.doctusoft.guava.converter;

import com.google.common.base.Converter;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;

import java.io.Serializable;
import java.util.*;

import static java.util.Objects.*;

public class CommaSeparatedSortedSetConverter<T extends Comparable<? super T>> extends Converter<Set<T>, String> implements Serializable {
    
    private final Converter<String, T> elementConverter;
    
    public CommaSeparatedSortedSetConverter(Converter<String, T> elementConverter) {
        this.elementConverter = requireNonNull(elementConverter, "elementConverter");
    }
    
    protected Set<T> doBackward(String string) {
        TreeSet<T> set = new TreeSet<>();
        Iterables.transform(SPLITTER.split(string), elementConverter).forEach(set::add);
        return set;
    }
    
    protected String doForward(Set<T> set) { return JOINER.join(ImmutableSortedSet.copyOf(set)); }
    
    private static final String SEPARATOR = ", ";
    
    private static final Joiner JOINER = Joiner.on(SEPARATOR);
    
    private static final Splitter SPLITTER = Splitter.on(SEPARATOR).omitEmptyStrings();
    
}
