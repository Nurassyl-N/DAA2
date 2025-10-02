package metrics;

public class PerformanceTracker {
    private long comparisons = 0;
    private long swaps = 0;
    private long arrayAccesses = 0;

    public int compare(int a, int b) {
        comparisons++;
        return Integer.compare(a, b);
    }

    public void countSwap() {
        swaps++;
    }

    public void countAccess() {
        arrayAccesses++;
    }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayAccesses() { return arrayAccesses; }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
    }
}