package com.doctusoft.guava.converter;

import com.google.common.base.Converter;

import java.time.Instant;

public class InstantToLongConverter extends Converter<Instant, Long> {
    
    public static final InstantToLongConverter INSTANCE = new InstantToLongConverter();
    
    private InstantToLongConverter() {}
    
    private Object readResolve() { return INSTANCE; }
    
    protected Long doForward(Instant instant) { return instant.toEpochMilli(); }
    
    protected Instant doBackward(Long value) { return Instant.ofEpochMilli(value); }
}
