package test;

import main.EquivalenceClassFactory;
import main.EquivalenceClassFeature;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CaseInsensitiveStrings {
    @Test
    public void testSet() {
        final EquivalenceClassFactory<String> ecf =
                new EquivalenceClassFactory<>(new EquivalenceClassFeature<>(
                        "lowercase",
                        (s) -> s.toLowerCase()));

        final Set<EquivalenceClassFactory<String>.EquivalenceClass> set = ecf.new Set();

        assertFalse(set.contains(ecf.buildFrom("quick brown fox")));
        set.add(ecf.buildFrom("Quick brown fox"));
        assertTrue(set.contains(ecf.buildFrom("quick brown fox")));

        set.remove(ecf.buildFrom("Quick BROWN fox"));
        assertTrue(set.isEmpty());
    }
}