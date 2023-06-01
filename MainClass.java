import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class MainClass {
    private static long[] readIntegersFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        ArrayList<Long> numbers = new ArrayList<>();
        int lineNumber = 0;
        //numbers = new long[8000];

        while (lineNumber < 8000) {
            line = reader.readLine();
            String[] parts = line.split("\t");
            long number = Long.parseLong(parts[1]);
            numbers.add(number);
            lineNumber++;
        }

        reader.close();
        return numbers.stream().mapToLong(Long::valueOf).toArray();
    }

    public static long[] generateRandomArray(int length, int minValue, int maxValue) {
        long[] arr = new long[length];
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(maxValue - minValue + 1) + minValue;
        }

        return arr;
    }

    // integers
    private static void quickSortIntegersMethod(long[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotIndex = partition(array, left, right);

        quickSortIntegersMethod(array, left, pivotIndex - 1);
        quickSortIntegersMethod(array, pivotIndex + 1, right);
    }

    private static int partition(long[] arr, int low, int high) {
        long pivot = arr[high];

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller than the pivot
            if (arr[j] < pivot) {

                // Increment index of smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    private static void swap(long[] array, int i, int j) {
        long temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static final int INSERTION_SORT_THRESHOLD = 10;
    public static void quickSortInsertionIntegersMethod(long[] arr, int low, int high) {
        if (low < high) {
            if (high - low + 1 <= 10) {
                insertionSort(arr, low, high);
            } else {
                int pivotIndex = partition2(arr, low, high);
                quickSortInsertionIntegersMethod(arr, low, pivotIndex - 1);
                quickSortInsertionIntegersMethod(arr, pivotIndex + 1, high);
            }
        }
        return;
    }

    private static int partition2(long[] arr, int low, int high) {
        long pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void insertionSort(long[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            long key = arr[i];
            int j = i - 1;

            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    public static void shellSortIntegersMethod(long[] arr) {
        int n = arr.length;

        // Start with a large gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Perform insertion sort on elements gapped by 'gap'
            for (int i = gap; i < n; i++) {
                long temp = arr[i];
                int j = i;

                // Shift elements until the correct position for temp is found
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }

                arr[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        MainClass main = new MainClass();
        String filePath = "./count_1w.txt";
        long[] originalNumbers;
        long[] numbers;

        
        originalNumbers = main.generateRandomArray(128000, 500000, 100000000);
        

        System.out.println("INTEGERS-----------");
        System.out.println("QUICK SORT :");
        
        numbers = Arrays.copyOf(originalNumbers, originalNumbers.length);
        long startTime = System.currentTimeMillis();
        quickSortIntegersMethod(numbers, 0, numbers.length - 1);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("QUICK SORT took " + duration + " milliseconds to execute.");

        System.out.println("QUICK SORT INSERTION");
        numbers = Arrays.copyOf(originalNumbers, originalNumbers.length);
        startTime = System.currentTimeMillis();
        quickSortInsertionIntegersMethod(numbers, 0, numbers.length - 1);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("QUICK SORT took " + duration + " milliseconds to execute.");

        System.out.println("SHELL SORT :");
        numbers = Arrays.copyOf(originalNumbers, originalNumbers.length);
        startTime = System.currentTimeMillis();
        shellSortIntegersMethod(numbers);
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("SHELL SORT took " + duration + " milliseconds to execute.");

    }
}