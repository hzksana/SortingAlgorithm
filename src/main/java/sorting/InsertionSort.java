package sorting;

public class InsertionSort {

    public static void sort(int[] a, int p, int r) {
        for (int j = p + 1; j <= r; j++) {
            int key = a[j];
            int i = j - 1;
            while (i >= p && a[i] > key) {
                a[i + 1] = a[i];
                i--;
            }
            a[i + 1] = key;
        }
    }
}
