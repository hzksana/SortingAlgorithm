package sorting;

public class QuickSortDutchFlag {

    public static void sort(int a[], int l, int r) {
        sort3Way(a, l, r);
    }

    private static void sort3Way(int a[], int l, int r) {
        if (r <= l) return;

        int v = a[r];
        int i = l - 1, j = r, p = l - 1, q = r, k;

        while (true) {
            while (++i <= r && a[i] < v);  
            while (--j >= l && v < a[j]);  
            if (i >= j) break;

            Utils.swap(a, i, j);
            if (a[i] == v) { p++; Utils.swap(a, p, i); }
            if (v == a[j]) { q--; Utils.swap(a, q, j); }
        }

        Utils.swap(a, i, r);
        j = i - 1;
        i = i + 1;

        for (k = l; k <= p; k++, j--) Utils.swap(a, k, j);
        for (k = r - 1; k >= q; k--, i++) Utils.swap(a, k, i);

        sort3Way(a, l, j);
        sort3Way(a, i, r);
    }
}
