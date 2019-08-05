package test;

import main.EquivalenceClassFactory;
import main.EquivalenceClassFeature;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Identifying an Integer with its intValue should be equivalent to an identity map.
 * Assert that Maps and Sets behave the same.
 */

class ValueOfBoxedInteger {
    static final int NUM_ITERATIONS = 2000;

    @Test
    public void testMap() {
        final EquivalenceClassFactory<Integer> equivalenceClassFactory =
                new EquivalenceClassFactory<>(new EquivalenceClassFeature<>(
                        "intValue",
                        (i) -> i.intValue()));

        final Map<Integer, Integer> normalMap = new HashMap<>();
        // You'd think that the next line should declare type
        //      Map<equivalenceClassFactory.EquivalenceClass, Integer>
        // but alas, no...
        final Map<EquivalenceClassFactory<Integer>.EquivalenceClass, Integer> weirdMap
                = equivalenceClassFactory.new Map<Integer>();
        final Random random = new Random(0);


        for (int i = 0; i < NUM_ITERATIONS; i++) {
            // small bound -> we will overwrite
            final int key = random.nextInt(100);
            final int value = random.nextInt();

            normalMap.put(key, value);
            weirdMap.put(equivalenceClassFactory.buildFrom(key), value);
        }

        for (final Object o : normalMap.keySet()) {
            assertEquals(normalMap.get(o), weirdMap.get(equivalenceClassFactory.buildFrom((Integer) o)));
        }
    }

}