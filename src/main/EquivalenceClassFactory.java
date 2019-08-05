package main;

import java.util.*;

public class EquivalenceClassFactory<T> {

    private final List<EquivalenceClassFeature<? super T>> features;

    public EquivalenceClassFactory(final EquivalenceClassFeature<? super T>... features) {
        this.features = Arrays.asList(features);
    }

    public <R extends T> EquivalenceClass buildFrom(final R val) {
        return new EquivalenceClass(val);
    }

    public final class Map<V> extends HashMap<EquivalenceClass, V> {}
    public final class Set extends HashSet<EquivalenceClass> {}

    public final class EquivalenceClass {

        private final T val;

        private <R extends T> EquivalenceClass(final R val) {
            this.val = val;
        }

        @Override
        public String toString() {
            // Collect a comma-separated list "key=value".
            final StringJoiner params = new StringJoiner(", ");
            features.stream()
                    .map(feature -> feature.stateValue(this.val))
                    .forEach(params::add);

            // Form a String description of this equivalence class.
            return new StringBuilder()
                    .append("EquivalenceClass of ")
                    .append(this.val.getClass().toGenericString())
                    .append(" with properties ")
                    .append(params)
                    .toString();
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj)
                return true;

            if (obj == null || obj.getClass() != this.getClass())
                return false;

            final EquivalenceClass equivalenceClass = (EquivalenceClass) obj;
            for (final EquivalenceClassFeature<? super T> feature : features) {
                final Object myValue = feature.extractFrom(this.val);
                final Object theirValue = feature.extractFrom(equivalenceClass.val);

                if (!myValue.equals(theirValue)) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public int hashCode() {
            int hashCode = 0;

            for (final EquivalenceClassFeature<? super T> feature : features) {
                final Object val = feature.extractFrom(this.val);
                hashCode = (31 * hashCode) ^ val.hashCode();
            }

            return hashCode;
        }
    }
}
