package sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeSortingAlgorithms {

    private static final long CUTOFF_TIME_NANOSECONDS = 5L * 60 * 1_000_000_000; // 30 minutes in nanoseconds

    // Time a sorting algorithm
    public static long timeSort(int[] array, String algorithm) {
        long startTime = System.nanoTime();
        long elapsedTime;

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
                MergeSortHybrid.sortCutOff(array, 0, array.length - 1, 10);
                break;
            case "QuickSort":
                QuickSort.sort(array, 0, array.length - 1);
                break;
            case "QuickSortDutchFlag":
                QuickSortDutchFlag.sort(array, 0, array.length - 1);
                break;
            case "QuickSortHybrid":
                QuickSortHybrid.sort(array, 0, array.length - 1, 10);
                break;
            case "QuickSortMedian":
                QuickSortMedian.sortMedian3(array, 0, array.length - 1);
                break;
            default:
                throw new IllegalArgumentException("Unknown sorting algorithm");
        }

        elapsedTime = System.nanoTime() - startTime;

        if (elapsedTime > CUTOFF_TIME_NANOSECONDS) {
            System.out.println("⚠️ " + algorithm + " exceeded 5 minutes and was stopped.");
            return CUTOFF_TIME_NANOSECONDS;
        }

        return elapsedTime;
    }

    // Load dataset from file
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


    // Main method to run experiments
    public static void main(String[] args) {
        String[] algorithms = {
            "InsertionSort", "SelectionSort", "ShellSort", "MergeSort", "MergeSortBottomUp",
            "MergeSortHybrid", "QuickSort", "QuickSortDutchFlag", "QuickSortHybrid", "QuickSortMedian"
        };
        
        String[] datasets = {
            "bad.txt", "dutch.txt", "int10.txt", "int20k.txt", "int50.txt", 
            "int100.txt", "int500k.txt", "int1000.txt", "intBig.txt"
        };
    
        try {
            // Create a 2D array to store average times: rows for datasets, columns for algorithms
            long[][] averageTimes = new long[datasets.length][algorithms.length];
    
            // Loop through each dataset
            for (int datasetIndex = 0; datasetIndex < datasets.length; datasetIndex++) {
                String dataset = datasets[datasetIndex];
                System.out.println("Running experiments for " + dataset);
                int[] data = loadDataset("C:/Users/mito/Desktop/Y2/sem2/alg/AE1/src/main/java/dataset/" + dataset);
    
                // Loop through each sorting algorithm
                for (int algorithmIndex = 0; algorithmIndex < algorithms.length; algorithmIndex++) {
                    String algorithm = algorithms[algorithmIndex];
                    long totalElapsedTime = 0;
    
                    // Perform the experiment 10 times
                    for (int i = 0; i < 1; i++) {
                        // Clone the data so each run has a fresh copy of the dataset
                        int[] dataCopy = Arrays.copyOf(data, data.length);
                        long elapsedTime = timeSort(dataCopy, algorithm);
                        totalElapsedTime += elapsedTime;
                    }
    
                    // Calculate the average time over 10 runs
                    long averageElapsedTime = totalElapsedTime / 1;
    
                    // Store the average time in the corresponding row and column
                    averageTimes[datasetIndex][algorithmIndex] = averageElapsedTime;
    
                    // Optionally, report the average time
                    System.out.println("Average time for " + algorithm + " on " + dataset + ": " + averageElapsedTime + " ns");
                }
            }
    
            // Optionally: Print the stored results in a tabular form
            System.out.println("\nAverage times (in ns) for each sorting algorithm on each dataset:");
            System.out.printf("%-20s", "Dataset");
            for (String algorithm : algorithms) {
                System.out.printf("%-20s", algorithm);
            }
            System.out.println();
            
            for (int datasetIndex = 0; datasetIndex < datasets.length; datasetIndex++) {
                System.out.printf("%-20s", datasets[datasetIndex]);
                for (int algorithmIndex = 0; algorithmIndex < algorithms.length; algorithmIndex++) {
                    System.out.printf("%-20d", averageTimes[datasetIndex][algorithmIndex]);
                }
                System.out.println();
            }
    






            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    