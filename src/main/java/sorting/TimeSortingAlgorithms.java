package sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeSortingAlgorithms {
    // Define a method to time the sorting algorithms with a cutoff of 1 minute (60 seconds)
    private static final long CUTOFF_TIME_NANOSECONDS = 10L * 1_000_000_000; // 1 minute in nanoseconds
    
    public static long timeSort(int[] array, String algorithm) {
        long startTime = System.nanoTime();
        long elapsedTime = 0;

        switch (algorithm) {
            case "InsertionSort":
                InsertionSort.sort(array, 0, array.length - 1);
                break;
            case "SelectionSort":
                SelectionSort.sort(array);
                break;
            case "ShellSort":
                ShellSort.sort(array);
                break;
            case "MergeSort":
                MergeSort.sort(array, 0, array.length - 1);
                break;
            case "MergeSortBottomUp":
                MergeSortBottomUp.sort(array, 0, array.length - 1);
                break;
            case "MergeSortHybrid":
                MergeSortHybrid.sortCutOff(array, 0, array.length - 1, 10); // Example cutoff value
                break;
            case "QuickSort":
                QuickSort.sort(array, 0, array.length - 1);
                break;
            case "QuickSortDutchFlag":
                QuickSortDutchFlag.sort(array, 0, array.length - 1);
                break;
            case "QuickSortHybrid":
                QuickSortHybrid.sort(array, 0, array.length - 1, 10); // Example cutoff value
                break;
            case "QuickSortMedian":
                QuickSortMedian.sortMedian3(array, 0, array.length - 1);
                break;
            default:
                throw new IllegalArgumentException("Unknown sorting algorithm");
        }
        
        elapsedTime = System.nanoTime() - startTime;

        // Check if elapsed time exceeds cutoff time (1 minute)
        if (elapsedTime > CUTOFF_TIME_NANOSECONDS) {
            System.out.println("⚠️ " + algorithm + " exceeded 1 minute and was stopped.");
            return CUTOFF_TIME_NANOSECONDS;
        }

        return elapsedTime;  // Return time in nanoseconds
    }

    // Load dataset from file (just a helper method)
    public static int[] loadDataset(String filepath) throws IOException {
        List<Integer> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        String line;
        while ((line = br.readLine()) != null) {
            list.add(Integer.parseInt(line));
        }
        br.close();
        return list.stream().mapToInt(i -> i).toArray();
    }

    // Main method to run the experiments
    public static void main(String[] args) {
        String[] algorithms = {"InsertionSort", "SelectionSort", "ShellSort", "MergeSort", "MergeSortBottomUp", 
                                "MergeSortHybrid", "QuickSort", "QuickSortDutchFlag", "QuickSortHybrid", "QuickSortMedian"};
        
        String[] datasets = {"bad.txt", "dutch.txt", "int10.txt", "int20k.txt", "int50.txt", "int100.txt", "int500k.txt", "int1000.txt", "intBig.txt"};

        try {
            // Time each algorithm on each dataset
            for (String dataset : datasets) {
                System.out.println("Running experiments for " + dataset);
                int[] data = loadDataset("C:/Users/mito/Desktop/Y2/sem2/alg/AE1/src/main/java/dataset/" + dataset);

                for (String algorithm : algorithms) {
                    long totalTime = 0;
                    // Perform each experiment 10 times and calculate the average time
                    for (int i = 0; i < 1; i++) {
                        int[] copy = Arrays.copyOf(data, data.length); // Make a copy of the dataset
                        totalTime += timeSort(copy, algorithm);
                    }
                    long averageTime = totalTime / 1; // Calculate average time
                    System.out.println(algorithm + " on " + dataset + ": " + averageTime + " nanoseconds");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
