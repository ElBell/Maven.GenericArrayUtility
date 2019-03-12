package com.zipcodewilmington.arrayutility;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<E> {
    private E[] array;
    public ArrayUtility(E[] inputArray) {
        array = inputArray;
    }

    public E[] merge(E[] arrayToMerge) {
        Object[] merged =  Stream.of(array, arrayToMerge).flatMap(Stream::of).toArray(Object[]::new);
        return convertArray(merged);
    }

    public Integer countDuplicatesInMerge(E[] arrayToMerge, E valueToEvaluate) {
        E[] merged = merge(arrayToMerge);
        return getNumberOfOccurrences(merged, valueToEvaluate);
    }

    public Integer getNumberOfOccurrences(E valueToEvaluate) {
        return getNumberOfOccurrences(array, valueToEvaluate);
    }

    private Integer getNumberOfOccurrences(E[] merged, E valueToEvaluate) {
        int count = 0;
        for (E e : merged) {
            if (e.equals(valueToEvaluate)) {
                count++;
            }
        }
        return count;
    }

    public E getMostCommonFromMerge(E[] arrayToMerge) {
        E[] merged = merge(arrayToMerge);
        E mostCommon = merged[0];
        int mostCommonCount = 0;
        for (E e : merged) {
            int currentCount = getNumberOfOccurrences(merged, e);
            if(currentCount > mostCommonCount) {
                mostCommon = e;
                mostCommonCount = currentCount;
            }
        }
        return mostCommon;
    }

    public E[] removeValue(E valueToRemove) {
        Object[] removed =  Arrays.stream(array).filter(val -> !val.equals(valueToRemove)).toArray();
        return  convertArray(removed);
    }

    private E[] convertArray(Object[] removed) {
        E[] finalArray =  Arrays.copyOfRange(array, 0, removed.length);
        for (int i = 0; i < removed.length; i++) {
            finalArray[i] = (E)removed[i];
        }
        return finalArray;
    }
}
