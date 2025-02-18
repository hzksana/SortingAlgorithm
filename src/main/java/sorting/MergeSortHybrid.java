package sorting;

public class MergeSortHybrid {

    public static void sortCutOff(int a[], int p, int r, int n) {
        if (r - p + 1 <= n) { // Fix base case
            InsertionSort.sort(a, p, r);
            return;
        }
        int q = p + (r - p) / 2;
        sortCutOff(a, p, q, n); // Fix recursive calls
        sortCutOff(a, q + 1, r, n);
        MergeSort.merge(a, p, q, r);
    }

    // Remove this if it's unused
    public static void sort(int a[], int p, int r) {
        InsertionSort.sort(a, p, r);
    }
}
