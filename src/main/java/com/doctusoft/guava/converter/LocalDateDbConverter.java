package com.doctusoft.guava.converter;

import com.google.common.base.Converter;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public final class LocalDateDbConverter extends Converter<LocalDate, Date> implements Serializable {
    
    public static final LocalDateDbConverter INSTANCE = new LocalDateDbConverter();
    
    private LocalDateDbConverter() {}
    
    private Object readResolve() { return INSTANCE; }
    
    protected Date doForward(LocalDate localDate) { return Date.valueOf(localDate); }
    
    protected LocalDate doBackward(Date date) { return date.toLocalDate(); }
}
