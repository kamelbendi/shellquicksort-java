import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MainClass {
    private static long[] readIntegersFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        long[] numbers;
        int lineNumber = 0;
        numbers = new long[2001];

        while (lineNumber != 2000) {
            line = reader.readLine();
            String[] parts = line.split("\t");
            long number = Long.parseLong(parts[1]);
            numbers[lineNumber] = number;
            lineNumber++;
        }

        reader.close();
        return numbers;
    }

    // integers
    private void quickSortIntegersMethod(long[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotIndex = partition(array, left, right);

        quickSortIntegersMethod(array, left, pivotIndex - 1);
        quickSortIntegersMethod(array, pivotIndex + 1, right);
    }

    private static int partition(long[] array, int left, int right) {
        long pivot = array[left];
        int i = left + 1;
        int j = right;

        while (i <= j) {
            while (i <= j && array[i] <= pivot) {
                i++;
            }
            while (i <= j && array[j] > pivot) {
                j--;
            }
            if (i < j) {
                swap(array, i, j);
            }
        }

        swap(array, left, j);

        return j;
    }

    private static void swap(long[] array, int i, int j) {
        long temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void quickSortInsertionIntegersMethod() {

    }

    public void shellSortIntegersMethod() {

    }

    public static void main(String[] args) {
        MainClass main = new MainClass();
        String filePath = "./count_1w.txt";
        long[] numbers;

        try {
            numbers = main.readIntegersFromFile(filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            return;
        }

        System.out.println("INTEGERS-----------");
        System.out.println("QUICK SORT :");
        long startTime = System.nanoTime();
        main.quickSortIntegersMethod(numbers, 0, numbers.length - 1);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("QUICK SORT took " + duration + " nanoseconds to execute.");

        System.out.println("QUICK SORT INSERTION");
        startTime = System.nanoTime();
        main.quickSortInsertionIntegersMethod();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("QUICK SORT took " + duration + " nanoseconds to execute.");

        System.out.println("SHELL SORT :");
        startTime = System.nanoTime();
        main.shellSortIntegersMethod();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("SHELL SORT took " + duration + " nanoseconds to execute.");

    }
}