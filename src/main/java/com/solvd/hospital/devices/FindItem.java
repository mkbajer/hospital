package com.solvd.hospital.devices;

import java.util.Collection;
import java.util.function.BiFunction;

public class FindItem<T> {

    // Generic method to count occurrences of an item in any type of collection
    public int search(T item, Collection<T> collection) {
        BiFunction<T, Collection<T>, Integer> ref = (i, coll) -> {
            int count = 0;
            for (T element : coll) {
                if (i != null && i.equals(element)) {
                    count++;
                }
            }
            return count;
        };
        return ref.apply(item, collection);
    }
}