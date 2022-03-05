import java.util.*;
import java.io.*;

public class TestA1 {
    public static void main(String[] args) {
        int size = 10;
        int iterations = 1000;
        int[] array;
        double[] timeArray = new double[iterations];
        double startTime, endTime, totalTime;

        for(int i = 0; i < iterations; i++) {
            array = populateAscending(size);
            startTime = System.nanoTime();
            Assign1.selectionSort(array);
            endTime = System.nanoTime();
            timeArray[i] = endTime - startTime;
        }

        writeToFile(timeArray, "selection.txt");

        /*
        for(int i = 1; i <= 6; i++) {
            size = (int)Math.pow(10, i);

            array = populateAscending(size);
            startTime = System.nanoTime();
            Assign1.selectionSort(array);
            endTime = System.nanoTime();
            totalTime = (endTime - startTime) / Math.pow(10, 9);
            System.out.println("Time for selection sort with " + size + " elements in ascending order is " + totalTime + " seconds.");

            array = populateAscending(size);
            startTime = System.nanoTime();
            Assign1.insertionSort(array);
            endTime = System.nanoTime();
            totalTime = (endTime - startTime) / Math.pow(10, 9);
            System.out.println("Time for insertion sort with " + size + " elements in ascending order is " + totalTime + " seconds.");

            array = populateAscending(size);
            startTime = System.nanoTime();
            Assign1.mergeSort(array, 0, size - 1);
            endTime = System.nanoTime();
            totalTime = (endTime - startTime) / Math.pow(10, 9);
            System.out.println("Time for merge sort with " + size + " elements in ascending order is " + totalTime + " seconds.");

            array = populateAscending(size);
            startTime = System.nanoTime();
            Assign1.quickSort(array);
            endTime = System.nanoTime();
            totalTime = (endTime - startTime) / Math.pow(10, 9);
            System.out.println("Time for quick sort with " + size + " elements in ascending order is " + totalTime + " seconds.");

            System.out.println("\n");
        }

        System.out.println("\n");

        for(int i = 1; i <= 6; i++) {
            size = (int)Math.pow(10, i);

            array = populateDescending(size);
            startTime = System.nanoTime();
            Assign1.selectionSort(array);
            endTime = System.nanoTime();
            totalTime = (endTime - startTime) / Math.pow(10, 9);
            System.out.println("Time for selection sort with " + size + " elements in descending order is " + totalTime + " seconds.");

            array = populateDescending(size);
            startTime = System.nanoTime();
            Assign1.insertionSort(array);
            endTime = System.nanoTime();
            totalTime = (endTime - startTime) / Math.pow(10, 9);
            System.out.println("Time for insertion sort with " + size + " elements in descending order is " + totalTime + " seconds.");

            array = populateDescending(size);
            startTime = System.nanoTime();
            Assign1.mergeSort(array, 0, array.length - 1);
            endTime = System.nanoTime();
            totalTime = (endTime - startTime) / Math.pow(10, 9);
            System.out.println("Time for merge sort with " + size + " elements in descending order is " + totalTime + " seconds.");

            array = populateDescending(size);
            startTime = System.nanoTime();
            Assign1.quickSort(array);
            endTime = System.nanoTime();
            totalTime = (endTime - startTime) / Math.pow(10, 9);
            System.out.println("Time for quick sort with " + size + " elements in descending order is " + totalTime + " seconds.");

            System.out.println("\n");
        }

        System.out.println("\n");

        for(int i = 1; i <= 6; i++) {
            size = (int)Math.pow(10, i);

            array = populateRandom(size);
            startTime = System.nanoTime();
            Assign1.selectionSort(array);
            endTime = System.nanoTime();
            totalTime = (endTime - startTime) / Math.pow(10, 9);
            System.out.println("Time for selection sort with " + size + " elements in random order is " + totalTime + " seconds.");

            array = populateRandom(size);
            startTime = System.nanoTime();
            Assign1.insertionSort(array);
            endTime = System.nanoTime();
            totalTime = (endTime - startTime) / Math.pow(10, 9);
            System.out.println("Time for insertion sort with " + size + " elements in radnom order is " + totalTime + " seconds.");

            array = populateRandom(size);
            startTime = System.nanoTime();
            Assign1.mergeSort(array, 0, array.length - 1);
            endTime = System.nanoTime();
            totalTime = (endTime - startTime) / Math.pow(10, 9);
            System.out.println("Time for merge sort with " + size + " elements in random order is " + totalTime + " seconds.");

            array = populateRandom(size);
            startTime = System.nanoTime();
            Assign1.quickSort(array);
            endTime = System.nanoTime();
            totalTime = (endTime - startTime) / Math.pow(10, 9);
            System.out.println("Time for quick sort with " + size + " elements in random order is " + totalTime + " seconds.");

            System.out.println("\n");
        }

        System.out.println("\n");
        */

    }
    
    public static int[] populateAscending(int size) {
        int[] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = i + 1;
            //each index is initialized as i^2 + random where i is the current index of the array
        }

        return array;
    }

    public static int[] populateDescending(int size) {
        int[] array = new int[size];
        int k = size;
        for(int i = 0; i < size; i++) {
            array[i] = k;
        }

        return array;
    }

    public static int[] populateRandom(int size) {
        int[] array = new int[size];
        Random rand = new Random();
        for(int i = 0; i < size; i++) {
            array[i] = rand.nextInt(size);
        }

        return array;
    }

    public static void writeToFile(double[] array, String outputFile) {
        try {
            FileWriter writer = new FileWriter(outputFile);
            for(double value: array) {
            writer.write(Double.toString(value) + "\r\n");
            }
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    } 
}
