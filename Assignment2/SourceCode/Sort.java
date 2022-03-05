public class Sort {
    /**
     * This is the driver code for an iterative implementation of QuickSort 
     * that will be used to sort words into alphabetical order.
     * EX. The word "cat" would be sorted into "act".
     * @param word The word to be sorted into alphabetical order.
     * @return The String of the sorted word.
     */
    public static String sortWord(String word) {
        /* driver code adapted from January 26 notes on QuickSort */

        char[] array = word.toCharArray();
        //convert the word to a char array to simplify the sorting process

        if(array.length < 2) {
            return word;
            //if the word is 1 letter long, it is already sorted
        }

        return quickChar(array, 0, array.length - 1);
    }
 
    /**
     * This is the iterative implementation of QuickSort. An iterative version was used, since
     * issues were encountered when a recursive implementation was used.
     * @param array The word to be sorted, in a character array form.
     * @param low The starting index.
     * @param high The ending index.
     * @return The word sorted into alphabetical order, in String form.
     */
    private static String quickChar(char array[], int low, int high) {
        /* code for iterative quickSort from https://www.geeksforgeeks.org/iterative-quick-sort/
         * iterative quickSort was used since there were issues with large input
         * when using a recursive implementation of quickSort */

        int[] stack = new int[high - low + 1];
        //Create a stack
 
        int top = -1;
        //initialize top of stack
 
        stack[++top] = low;
        stack[++top] = high;
        //push initial values of l and h to stack
 
        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];
            //pop low and high from the stack
 
            int p = partition(array, low, high);
            ///use partition to find the value to use as a bound

            if (p - 1 > low) {
                stack[++top] = low;
                stack[++top] = p - 1;
                //if there are elements on the left side of pivot, push left side to stack
            }

            if (p + 1 < high) {
                stack[++top] = p + 1;
                stack[++top] = high;
                //if there are elements on the right side of pivot, push right side to stack
            }
        }

        return String.valueOf(array);
        //convert array back to a String then return
    }

    /**
     * This method swaps the two given indices of a character array
     * @param array The array with elements to be swapped.
     * @param i The first element to be swapped.
     * @param j The second element to be swapped.
     */
    private static void swapChar(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * This method partitions the char array for the QuickSort algorithm.
     * It takes the last element of the array as the pivot.
     * @param array The array to be sorted.
     * @param low The starting index.
     * @param high The ending index.
     * @return The index of the partition.
     */
    private static int partition(char array[], int low, int high) {
        char pivot = array[high];
        //take pivot as last character
 
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (Character.compare(array[j], pivot) <= 0) {
                i++;
                swapChar(array, i, j);
                //if current element is <= pivot, swap indices i and j
            }
        }
 
        //swap arr[i+1] and arr[high] (or pivot)
        swapChar(array, i + 1, high);
 
        return i + 1;
    }

    /**
     * This is the driver code for a recursive implementation of QuickSort, used to sort an 
     * array of LinkedLists into the correct order.
     * @param array The array of LinkedLists to be sorted.
     */
    public static void quick(LinkedList[] array) {
        /* code adapted from January 26th lecture notes */

        if(array.length < 2) {
            return;
            //if array is 1 element long, it is already sorted
        }

        int max = 0;
        for(int i = 0; i < array.length; i++) {
            String maxData = array[max].getHead().getData();
            String iData = array[i].getHead().getData();
            //use temporary variables to get the string data from each node

            if(iData.compareTo(maxData) > 0) {
                max = i;
            }
        }

        swap(array, array.length - 1, max);
        //put the largest element at the end of the array

        quickSort(array, 0, array.length - 1);
    }

    /**
     * This is the actual recursive algorithm for using QuickSort on the array
     * @param array The array to be sorted.
     * @param first The starting index of the sort.
     * @param last The ending index of the sort.
     */
    private static void quickSort(LinkedList[] array, int first, int last) {
        int lower = first + 1;
        int upper = last;
        //lower and upper are the variables that will search the array for values

        swap(array, first, (first + last) / 2);
        String bound = array[first].getHead().getData();
        //take the data of the middle element to be the bound and move it to the start of the array
        
        while(lower <= upper) {
            while(array[lower].getHead().getData().compareTo(bound) < 0) {
                lower++;
            }

            while(array[upper].getHead().getData().compareTo(bound) > 0) {
                upper--;
            }
            //keep moving through the array until upper <= bound and lower >= bound

            if(lower < upper) {
                swap(array, lower++, upper--);
                //as long as lower is below upper, swap the two indices
            }
            else{
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

    /**
     * This method swaps two indices of a LinkedList array.
     * @param array The array with elements to be swapped.
     * @param i The first element to be swapped.
     * @param j The second element to be swapped.
     */
    private static void swap(LinkedList[] array, int i, int j) {
        LinkedList temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
