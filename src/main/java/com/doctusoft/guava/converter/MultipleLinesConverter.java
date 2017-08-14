package com.doctusoft.guava.converter;

import com.google.common.base.Converter;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.io.Serializable;
import java.util.*;

public class MultipleLinesConverter extends Converter<List<String>, String> implements Serializable {
    
    public static final MultipleLinesConverter INSTANCE = new MultipleLinesConverter();
    
    private MultipleLinesConverter() {}
    
    private Object readResolve() { return INSTANCE; }
    
    protected List<String> doBackward(String string) { return new ArrayList<>(SPLITTER.splitToList(string)); }
    
    protected String doForward(List<String> stringList) { return JOINER.join(stringList); }
    
    private static final char SEPARATOR = '\n';
    
    private static final Joiner JOINER = Joiner.on(SEPARATOR);
    
    private static final Splitter SPLITTER = Splitter.on(SEPARATOR);
    
}
