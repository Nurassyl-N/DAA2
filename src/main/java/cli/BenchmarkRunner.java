package cli;

import algorithms.ShellSort;
import metrics.PerformanceTracker;

import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    private static final int[] SIZES = {100, 1000, 10000, 100000};

    public static void main(String[] args) {
        String[] sequences = {"shell", "knuth", "sedgewick"};

        for (String seq : sequences) {
            System.out.println("=== Benchmarking Shell Sort (" + seq + " sequence) ===");
            for (int n : SIZES) {
                int[] arr = randomArray(n);

                PerformanceTracker tracker = new PerformanceTracker();

                long start = System.nanoTime();
                ShellSort.sort(arr, tracker, seq);
                long end = System.nanoTime();

                long duration = end - start;

                if (!isSorted(arr)) {
                    throw new RuntimeException("Sort failed for sequence " + seq + " on size " + n);
                }

                System.out.printf("n=%d | time=%.3f ms | comps=%d | swaps=%d | accesses=%d%n",
                        n,
                        duration / 1_000_000.0,
                        tracker.getComparisons(),
                        tracker.getSwaps(),
                        tracker.getArrayAccesses()
                );
            }
            System.out.println();
        }
    }

    private static int[] randomArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(10000);
        return arr;
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }
}