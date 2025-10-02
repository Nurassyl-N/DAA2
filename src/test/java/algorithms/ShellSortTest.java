package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShellSortTest {

    @Test
    void testShellSort() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        PerformanceTracker tracker = new PerformanceTracker();
        ShellSort.sort(arr, tracker, "shell");
        assertArrayEquals(new int[]{1, 2, 5, 5, 6, 9}, arr);
    }

    @Test
    void testKnuthSort() {
        int[] arr = {22, 11, 99, 88, 9, 7, 42};
        PerformanceTracker tracker = new PerformanceTracker();
        ShellSort.sort(arr, tracker, "knuth");
        assertArrayEquals(new int[]{7, 9, 11, 22, 42, 88, 99}, arr);
    }

    @Test
    void testSedgewickSort() {
        int[] arr = {3, 44, 38, 5, 47, 15};
        PerformanceTracker tracker = new PerformanceTracker();
        ShellSort.sort(arr, tracker, "sedgewick");
        assertArrayEquals(new int[]{3, 5, 15, 38, 44, 47}, arr);
    }
}