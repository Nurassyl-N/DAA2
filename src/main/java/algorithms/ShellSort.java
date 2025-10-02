package algorithms;

import metrics.PerformanceTracker;

public class ShellSort {

    public static void sort(int[] arr, PerformanceTracker tracker, String sequence) {
        int n = arr.length;
        int[] gaps;

        switch (sequence.toLowerCase()) {
            case "knuth":
                gaps = knuthSequence(n);
                break;
            case "sedgewick":
                gaps = sedgewickSequence(n);
                break;
            default:
                gaps = shellSequence(n);
        }

        for (int gap : gaps) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                tracker.countAccess();
                int j = i;
                while (j >= gap && tracker.compare(arr[j - gap], temp) > 0) {
                    arr[j] = arr[j - gap];
                    tracker.countAccess();
                    tracker.countSwap();
                    j -= gap;
                }
                arr[j] = temp;
                tracker.countAccess();
            }
        }
    }

    private static int[] shellSequence(int n) {
        int[] gaps = new int[(int)(Math.log(n) / Math.log(2))];
        int k = 0;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            gaps[k++] = gap;
        }
        return java.util.Arrays.copyOf(gaps, k);
    }

    private static int[] knuthSequence(int n) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        int h = 1;
        while (h < n) {
            list.add(0, h);
            h = 3 * h + 1;
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    private static int[] sedgewickSequence(int n) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        int k = 0;
        int gap;
        while (true) {
            if (k % 2 == 0) {
                gap = (int)(9 * (Math.pow(2, k) - Math.pow(2, k / 2)) + 1);
            } else {
                gap = (int)(8 * Math.pow(2, k) - 6 * (int)Math.pow(2, (k + 1) / 2) + 1);
            }
            if (gap > n) break;
            list.add(0, gap);
            k++;
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}