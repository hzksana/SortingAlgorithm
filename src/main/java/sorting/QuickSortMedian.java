package sorting;

public class QuickSortMedian {
    public static void sortMedian3(int a[], int p, int r){
        if (r <= p) return;
        Utils.swap(a, (p+r)/2, r-1);
        if (a[r-1] < a[p]) Utils.swap (a, p, r-1);
        if (a[r] < a[p]) Utils.swap (a, p, r);
        if (a[r] < a[r-1]) Utils.swap (a, r-1, r);
        int q = QuickSort.partition(a, p+1, r-1);
        sortMedian3(a, p, q-1);
        sortMedian3(a, q+1, r);
    }

    public static void sort(int a[], int p, int r) {
        sortMedian3(a, p, r);
    }

}
