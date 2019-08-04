package main;


public class EquivalenceClassFeature<T> {
    private final String featureName;
    private final EquivalenceClassFeatureExtractor<? super T> featureExtractor;

    public EquivalenceClassFeature(final String featureName,
                                   final EquivalenceClassFeatureExtractor<? super T> featureExtractor) {
        this.featureName = featureName;
        this.featureExtractor = featureExtractor;
    }

    public <R extends T> Object extractFrom(final R obj) {
        return this.featureExtractor.extractFrom(obj);
    }

    public <R extends T> String stateValue(final R obj) {
        return new StringBuilder()
                .append(this.featureName)
                .append("=")
                .append(this.extractFrom(obj))
                .toString();
    }
}
