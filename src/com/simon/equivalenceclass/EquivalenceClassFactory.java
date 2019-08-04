package com.simon.equivalenceclass;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class EquivalenceClass<T> {

    private final T obj;
    private final List<EquivalenceClassFeature<T>> features;

    public EquivalenceClass(final T obj, final EquivalenceClassFeature<T>... features) {
        this.obj = obj;
        this.features = Arrays.asList(features);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("EquivalenceClass of ");
        sb.append(obj.getClass().toGenericString());
        sb.append(" with properties ");

        final StringJoiner params = new StringJoiner(", ");
        features.stream()
                .map(f -> f.extractFrom(this.obj))
                .map(Object::toString)
                .map(String::toString)
                .forEachOrdered(params::add);
        sb.append(params);

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        EquivalenceClass
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
