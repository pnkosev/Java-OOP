package p04_BubbleSortTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class BubbleTest {

    @Test
    public void shouldSortCorrectly() {
        int ARR_LENGTH = 9;
        int[] arrOne = generateRandomArr(ARR_LENGTH);
        int[] arrTwo = Arrays.copyOf(arrOne, ARR_LENGTH);

        Arrays.sort(arrOne);
        Bubble.sort(arrTwo);

        assertArrayEquals(arrOne, arrTwo);
    }

    private int[] generateRandomArr(int length) {
        int[] arr = new int[length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt();
        }

        return arr;
    }
}