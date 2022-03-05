import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Assign1 {
    /**
     * This main uses command line arguments to indicate which algorithm to run with certain parameters
     * @param args Command-line argument. Should be 4 elements long. Used to determine, size, order, algorithm, and text file
     * must be in the order: order size algortihm outputfile
     */
    public static void main(String[] args) {
        if (args.length != 4) {
            //this checks if the correct number of command line arguments were entered
            System.out.println("Invalid number of arguments! Program Terminating!");
            System.exit(0);
        }

        String order = args[0].toLowerCase();
        order = order.strip();

        int size = Integer.parseInt(args[1]);
        //size has to be converted from a String to an int

        String algorithm = args[2].toLowerCase();
        algorithm = algorithm.strip();

        String outputFile = args[3].toLowerCase();
        outputFile = outputFile.strip();
        //all string arguments are set to all lowercase with all leading & trailing whitespace removed

        if(size <= 0) {
            //this ensures the size entered is valid before continuing
            System.out.println("Invalid size. Size of the array must be greater than 0. Program Terminating.");
            System.exit(0);
        }

        int[] array = new int[size];
        System.out.println("Populating array...");
        populateArray(array, size, order);
        //use the populateArray method to fill the array

        if(algorithm.compareTo("selection") == 0) {
            System.out.println("Sorting array...");

            long startTime = System.nanoTime();
            selectionSort(array);
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / Math.pow(10, 9);
            //record only the time for the algorithm to run, then convert from nanosecond to seconds

            System.out.println("Time taken for Selection Sort algorithm with " + size + " inputs in " 
                    + order + " order is " + time + " seconds.");
        }
        else if(algorithm.compareTo("insertion") == 0) {
            System.out.println("Sorting array...");

            long startTime = System.nanoTime();
            insertionSort(array);
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / Math.pow(10, 9);
            //record only the time for the algorithm to run, then convert from nanosecond to seconds

            System.out.println("Time taken for Insertion Sort algorithm with " + size + " inputs in " 
                    + order + " order is " + time + " seconds.");
        }
        else if(algorithm.compareTo("merge") == 0) {
            System.out.println("Sorting array...");

            long startTime = System.nanoTime();
            mergeSort(array, 0 , size - 1);
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / Math.pow(10, 9);
            //record only the time for the algorithm to run, then convert from nanosecond to seconds

            System.out.println("Time taken for Merge Sort algorithm with " + size + " inputs in " 
                    + order + " order is " + time + " seconds.");
        }
        else if(algorithm.compareTo("quick") == 0) {
            System.out.println("Sorting array...");

            long startTime = System.nanoTime();
            quickSort(array);
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / Math.pow(10, 9);
            //record only the time for the algorithm to run, then convert from nanosecond to seconds

            System.out.println("Time taken for Quick Sort algorithm with " + size + " inputs in " 
                    + order + " order is " + time + " seconds.");
        }
        else {
            System.out.println("Invalid algorithm. Program Terminating");
            System.exit(0);
            //if the user doesn't enter one of the valid algorithms, program prints an error message then terminates
        }

        System.out.println("Writing to txt file...");
        writeToFile(array, outputFile);
    }

    /**
     * This method will populate the array according to the user's specification
     * @param array The array to be populated
     * @param size The size of the array
     * @param order The order to be used (can be ascending, descending, or random)
     */
    public static void populateArray(int[] array, int size, String order) {
        if(order.compareTo("ascending") == 0) {
            for(int i = 0; i < size; i++) {
                array[i] = i + 1;
                //each index is initialized as i + 1
            }
        }
        else if(order.compareTo("descending") == 0) {
            int k = size;
            for(int i = 0; i < size; i++) {
                array[i] = k;
                k--;
                //each index is initialized as k
            }
        }
        else if(order.compareTo("random") == 0) {
            //if the user wants a random order array, the program will generate a random integer for each index
            Random rand = new Random();
            for(int i = 0; i < size; i++) {
                array[i] = rand.nextInt(size);
                //the integer will be between 0 and size for each index
            }
        }
        else {
            System.out.println("Invalid order. Program Terminating");
            System.exit(0);
            //if the user doesn't enter one of the valid algorithms, program prints an error message then terminates
        }
    }

    /**
     * Writes the content of the array into a txt file
     * @param array The array to be copied to a txt file
     * @param outputFile The file name of the txt file
     */
    public static void writeToFile(int[] array, String outputFile) {
        String txt = outputFile.substring(outputFile.length() - 4, outputFile.length());
        //create a sub-string with just the last 4 characters of the output filename
        //if it's a valid name, txt will contain the string ".txt"

        if(txt.compareTo(".txt") != 0) {
            System.out.println("Invalid text file name. Program Terminating.");
            System.exit(0);
            //if the user doesn't enter a valid .txt file name, program prints an error message then terminates
        }
        try {
            FileWriter writer = new FileWriter(outputFile);
            for(int value: array) {
            writer.write(Integer.toString(value) + "\r\n");
            }
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    } 

    /**
     * Selection sort algorithm
     * @param array The array to be sorted
     */
    public static void selectionSort(int[] array) {
        /* code from January 24 lecture notes, slide 26 */

        for(int i = 0; i < array.length - 1; i++) {
            int min = i;
            for(int j = i + 1; j < array.length; j++) {
                //this for loop finds the smallest element to the right of index i
                //the selection sort algorithm means that all elements < i will already be sorted
                if(array[j] <= array[min]) {
                    min = j;
                }
            }

            //after finding the new smallest element, swap the smallest element with index i
            int temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }

        /* Example iteration of selection sort
         * Before sorting : 5, 4, 1, 2, 3
         * Iteration 1:     5, 4, 1, 2, 3
         *                  ^     ^
         *                  i    min
         * Swap:            1, 4, 5, 2, 3
         * Then continue until i is at the end of the array
         *  and the array looks like: 1, 2, 3, 4, 5
         */
    }

    /**
     * The Insertion sort algorithm
     * @param array The array to be sorted
     */
    public static void insertionSort(int[] array) {
        /* code from January 26 lecture notes, slide 30 */

        int temp, j;

        for(int i = 0; i < array.length; i++) {
            temp = array[i];
            for(j = i; j > 0 && temp < array[j-1]; j--) {
                array[j] = array[j - 1];
                //while temp is less than the element to its left, elements are shifted one place to the right
            }
            array[j] = temp;
            //once all of the shifting is done, temp is inserted back into the array
        }

        /* Example iteration of insertion sort
         * Before sorting : 5, 4, 1, 2, 3
         * Iteration:       4, 5, 1, 2, 3
         *                        ^
         *                      temp
         * Shift elements:  4, 4, 5, 2, 3
         * Re-insert temp:  1, 4, 5, 2, 3
         * Continue until array looks like: 1, 2, 3, 4, 5
         */
    }

    /**
     * The merge sort algorithm
     * @param array The array to be sorted
     * @param left the left bound of the array
     * @param right the right bound of the array
     */
    public static void mergeSort(int[] array, int first, int last) {
        /* code from Jan 26 lecutre notes, slide 36 */
        
        if(first < last) {
            int middle = (first + last) / 2;
            mergeSort(array, first, middle);
            //sorts the left half of array
            mergeSort(array, middle + 1, last);
            //sorts the right half of array
            merge(array, first, middle, middle + 1, last);
            //merges the two halves of the array together
        }

        /* Example sort: 3, 2, 1
         *                  /\
         *                 /  \
         *              3, 2   1
         *               /\    |
         *              /  \   |
         *             3    2  |
         *              \  /   |
         *              2, 3   |
         *                \    /
         *                 \  /
         *               1, 2, 3
         */
    }

    /**
     * This method will merge two sub-arrays into a sorted array
     * @param array The array to be merged
     * @param leftPosition The position in the left sub-array
     * @param rightPosition The position in the right sub-array
     * @param rightEnd The position of the end of the right sub-array
     */
    private static void merge(int[] array, int leftPosition, int leftEnd, int rightPosition, int rightEnd) {
        /* code from course textbook page 286 */

        int[] tempArray = new int[array.length];
        int tempPosition = leftPosition;
        //create a temporary array to store the sorted elements

        int numElements = rightEnd - leftPosition + 1;
        //find the number of elements that will be merged in this method

        /* leftPostion: current position in the left sub-array
         * rightPostion: current position in the right sub-array
         * rightEnd: end of the right sub-array (also the end of the whole array)
         * leftEnd: end of the left sub-array
         * tempPosition: current position in the temporary array
         */

        while(leftPosition <= leftEnd && rightPosition <= rightEnd) {
            //this loop is used when both the left and right sub-arrays have elemnts left to add
            if(array[leftPosition] < array[rightPosition]) {
                tempArray[tempPosition] = array[leftPosition];
                tempPosition++;
                leftPosition++;
            }
            else {
                tempArray[tempPosition] = array[rightPosition];
                tempPosition++;
                rightPosition++;
            }
        }

        while(leftPosition <= leftEnd) {
            //this loop is used when all elements have already been merged from the right sub-array
                //and there are only elements left in the left sub-array to merge
            tempArray[tempPosition] = array[leftPosition];
            tempPosition++;
            leftPosition++;
        }

        while(rightPosition <= rightEnd) {
            //this loop is used when all elements have already been merged from the left sub-array
                //and there are only elements left in the right sub-array to merge
            tempArray[tempPosition] = array[rightPosition];
            tempPosition++;
            rightPosition++;
        }

        for(int i = 0; i < numElements; i++, rightEnd--) {
            //this final loop copies the sorted contents of tempArray into array
            array[rightEnd] = tempArray[rightEnd];
        }
    }

    /**
     * Will take an array and two indices and swap the two 
     * @param array The array with elements to be swapped
     * @param i The first element to be swapped
     * @param j The second element to be swapped
     */
    public static void swap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Driver method for the Quick sort algorithm
     * @param array The array to be sorted
     */
    public static void quickSort(int[] array) {
        /* code is from Janurary 26 lecture notes, slide 44 */

        if(array.length < 2) {
            return;
            //if the array is only one element long, it's already sorted
        }

        int max = 0;
        for(int i = 0; i < array.length; i++) {
            if(array[i] > array[max]) {
                max = i;
            }
        }

        swap(array, array.length - 1, max);
        //put the largest element at the end of the array

        quickSort(array, 0 , array.length - 1);
    }

    /**
     * The quick sort algorithm
     * @param array The array to be sorted
     * @param first The left-most position in the array
     * @param last The right-most position in the array
     */
    public static void quickSort(int[] array, int first, int last) {
        /* code is from Janurary 26 lecture notes, slides 45-46 */
        int lower = first + 1;
        int upper = last;
        //lower and upper are variables that will search the array for values above/below the bound respectively

        swap(array, first, (first + last) / 2);
        int bound = array[first];
        //take the middle element of the array as a bound and move it to the beginning of the array

        while(lower <= upper) {
            while(array[lower] < bound) {
                lower++;
            }

            while(array[upper] > bound) {
                upper--;
            }
            //keep moving through the array until lower finds an element >= bound and 
            //upper finds an element <= bound, then move onto the next section

            if(lower < upper) {
                //as long as lower is below upper, swap the two indices
                swap(array, lower++, upper--);
            }
            else {
                lower++;
            }
        }

        swap(array, upper, first);
        //then swap the bound and upper

        if(first < upper - 1) {
            quickSort(array, first, upper - 1);
        }
        if((upper + 1) < last) {
            quickSort(array, upper + 1, last);
        }
        //recursively sort through the rest of the array
    }
}