package com.doctusoft.guava.converter;

import com.google.common.base.Converter;

import java.sql.Timestamp;
import java.time.Instant;

public class InstantToDbTimestampConverter extends Converter<Instant, Timestamp> {
    
    public static final InstantToDbTimestampConverter INSTANCE = new InstantToDbTimestampConverter();
    
    private InstantToDbTimestampConverter() {}
    
    private Object readResolve() { return INSTANCE; }
    
    protected Timestamp doForward(Instant instant) { return Timestamp.from(instant); }
    
    protected Instant doBackward(Timestamp timestamp) { return timestamp.toInstant(); }
    
}
