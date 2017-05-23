package com.doctusoft.guava.converter;

import com.google.common.base.Converter;

import java.io.Serializable;
import java.time.LocalDate;

public final class LocalDateStringConverter extends Converter<LocalDate, String> implements Serializable {
    
    public static final LocalDateStringConverter INSTANCE = new LocalDateStringConverter();
    
    private LocalDateStringConverter() {}
    
    private Object readResolve() { return INSTANCE; }
    
    protected String doForward(LocalDate localDate) { return localDate.toString(); }
    
    protected LocalDate doBackward(String date) { return LocalDate.parse(date); }
}
