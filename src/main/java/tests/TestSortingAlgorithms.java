package tests;


public class TestSortingAlgorithms{

    public static boolean isSorted(int a[]){
        int n = a.length;
        for (int i = 0; i < n-1; i++){
            if (a[i] > a[i+1]){
                return false;
            }
        }
        return true;
    }
}
   