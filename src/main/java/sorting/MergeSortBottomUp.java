package sorting;

public class MergeSortBottomUp {

    public static void sort(int a[], int p, int r) {
        int n = r - p + 1;
        for (int sz = 1; sz < n; sz = sz + sz) {
            for (int i = p; i + sz <= r; i += sz + sz) {
                MergeSort.merge(a, i, i + sz - 1, Math.min(i + sz + sz - 1, r));
            }
        }
    }
}
