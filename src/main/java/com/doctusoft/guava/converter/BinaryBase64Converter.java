package com.doctusoft.guava.converter;

import com.google.common.base.Converter;

import java.io.Serializable;
import java.util.*;

public final class BinaryBase64Converter extends Converter<byte[], String> implements Serializable {
    
    public static final BinaryBase64Converter INSTANCE = new BinaryBase64Converter();
    
    private BinaryBase64Converter() {}
    
    private Object readResolve() { return INSTANCE; }
    
    protected String doForward(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
    
    protected byte[] doBackward(String s) {
        return Base64.getDecoder().decode(s);
    }
    
}
