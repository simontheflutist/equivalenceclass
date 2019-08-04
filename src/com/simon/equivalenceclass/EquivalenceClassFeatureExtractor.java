package com.simon.equivalenceclass;


public interface EquivalenceClassFeature<T> extends FunctionalInterface {
    public Object extractFrom(T t);
}
