package main;

@FunctionalInterface
public interface EquivalenceClassFeatureExtractor<T>  {
    // Can extract feature from T, but can also do it for any subtype of T.
    Object extractFrom(T obj);
}
