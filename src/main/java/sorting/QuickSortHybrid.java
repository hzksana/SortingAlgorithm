package sorting;

public class QuickSortHybrid {
    
    public static void sort(int a[], int p, int r, int k) {
        sortCutOff(a, p, r, k);
    }

    private static void kSort(int a[], int p, int r, int k) {
        if (r - p <= k) return;
        int q = QuickSort.partition(a, p, r);
        kSort(a, p, q - 1, k);
        kSort(a, q + 1, r, k);
    }
    
    public static void sortCutOff(int a[], int p, int r, int k) {
        kSort(a, p, r, k);
        InsertionSort.sort(a, p, r);  // Insertion Sort for small subarrays
    }
}
