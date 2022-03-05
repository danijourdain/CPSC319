/**
 * @author Danielle Jourdain
 * @version 1.17
 * @since 1.0
 */

import java.io.*;

public class Assign2 {
    /**
     * This main uses command-line arguments from the user to dictate how the program will run.
     * @param args  Command-line arguments. The user should enter two arguments, where both 
     *              are text file names otherwise the program will encounter an error and terminate.
     */
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Invalid number of command-line arguments. Program terminating");
            System.exit(0);
            //if the user doesn't enter 2 command-line arguments, program will terminate
        }

        String inputFile = checkTXT(args[0]);
        String outputFile = checkTXT(args[1]);
        //assign the command-line args to variables after checking the .txt extension
   
        System.out.println("Reading from input file " + inputFile + "...");
        String[] tempArray = readFile(inputFile);
        //read the values in the input file and assign them to a temporary String array

        System.out.println("Creating Linked Lists...");
        double startTime = System.nanoTime();

        LinkedList[] list = createList(tempArray);
        //use the String array to create the linked list

        Sort.quick(list);
        //use quickSort on the linked list to get it in the appropriate order
        double endTime = System.nanoTime();

        System.out.println("Writing to output file...");
        writeToFile(list, outputFile);
        //use the writeToFile funcition to write the contents of the linked list to a text file

        double time = (endTime - startTime) / Math.pow(10, 9);
        //calculate the amount of time to create and sort the linked list, then print this value
        System.out.println("Time to turn input file to a sorted linked list is " + time + " seconds.");
    }

    /**
     * This method takes a filename as an input and checks if it has the appropriate ".txt" extension at the end
     * @param filename The file name to be checked.
     * @return The filename as a String IF it has the correct extension. Otherwise the program will terminate.
     */
    public static String checkTXT(String filename) {
        String txt = filename.substring(filename.length() - 4);
        //create a substring using the last 4 characters of the filename

        if(txt.equals(".txt") == false) {
            //if the last 4 characters are not ".txt" print an error message then terminate the program.
            System.out.println("Invalid text file name. Program terminating");
            System.exit(0);
        }

        return filename;
        //otherwise return the filename
    }

    /**
     * This method will take a filename as input and return a String array where 
     * each element is the contents of one line of the file.
     * @param filename The name of the file to be read from.
     * @return A String array containing one line of the text file per element.
     */
    public static String[] readFile(String filename) {
        /* code for reading from a text file adapted 
         * from Week 4 tutorial slides, slide 12 */

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch(FileNotFoundException e) {
            System.out.println("File not found. Program terminating");
            System.exit(1);
            return null;
        }

        String s;
        String wordList = new String();
        //s is the string for the current line of the file and wordList
            //is a multi-line string holding all of the words in the list separated by a newLine character

        try {
            while((s = reader.readLine()) != null) {
                wordList = wordList.concat(s + "\n");
                //add each word to the long string line by line
            }
            reader.close();
        } catch(IOException e) {
            System.out.println("Unexpected error. Program terminating");
            System.exit(1);
        }

        String[] wordArray = wordList.split("\n");
        //use the split String method to turn the long String 
         //into a String array with one word per element

        return wordArray;
    }

    /**
     * This method will take an array of Strings and convert it to an array of references to a Linked List, 
     * where each reference points to a Linked List containing anagrams of a word.
     * @param array A String array containing a number of words.
     * @return An array of Linked Lists where each element is a Linked List of anagrams formed from the String array.
     */
    public static LinkedList[] createList(String[] array) {
        LinkedList[] list = new LinkedList[array.length];
        //create a new array of Linked Lists

        list[0] = new LinkedList(array[0]);
        //set the first element of the first Linked List manually

        for(int i = 1; i < array.length; i++) {
            for(int j = 0; j < list.length; j++) {
                if(list[j] != null) {
                    if(checkWord(array[i], list[j].getHead().getData()) == true) {
                        //if the current list element is not null, compare the current array element to the 
                            //current list element using the checkWord method. 
                        list[j].insertNode(array[i]);
                        break;
                        //if the checkWord method returns tru, insert the array element into the list,
                            //and break the inner for loop
                    }
                }
                else {
                    list[j] = new LinkedList(array[i]);
                    break;
                    //if a null list element is found, insert the current array element, then break the inner for loop
                }
            }
        }

        return shortenList(list);
        //use the shortenList method to remove all null elements from list before returning
    }

    /**
     * This fucntion takes two words, sorts them into alphabetical order, then compares the sorted words.
     * This method will return true if the sorted words are true and false otherwise.
     * @param word1 The first word to be compared.
     * @param word2 The second word to be compared.
     * @return A boolean value indicating if the two words are anagrams or not.
     */
    public static boolean checkWord(String word1, String word2) {
        if(word1.length() != word2.length()) {
            return false;
            //if the two words have different lengths, they are not anagrams so return false
        } 

        String sorted1 = Sort.sortWord(word1);
        String sorted2 = Sort.sortWord(word2);
        //sort the two words into alphabetical order to compare
        //EX. "cat" would be sorted into "act"

        return sorted1.equals(sorted2);
        //since the equals method returns a boolean value, return its value
    }

    /**
     * This takes an array of LinkedLists and removes any potential null elements at the end.
     * @param list An un-trimmed LinkedList array.
     * @return The same LinkedList with any null elements at the end removed.
     */
    public static LinkedList[] shortenList(LinkedList[] list) {
        int i= 0;
        for(LinkedList item: list) {
            if(item == null) {
                break;
            }
            i++;
            //find the actual number of elements in the array
        }

        if(i == list.length) {
            return list;
            //if i equals the length of the list, there is no null space to remove
        }

        LinkedList[] shortList = new LinkedList[i];
        //create a new array of references that is the correct size

        for(int j = 0; j < i; j++) {
            shortList[j] = list[j];
            //copy elements from the long list to the shorter list
        }

        return shortList;
    }

    /**
     * This method takes an array of LinkedLists and writes its contents to a text file.
     * Each LinkedList will take up one line.
     * @param list The array of LinkedLists to be written to a file.
     * @param fileName The name of the output file to be used.
     */
    public static void writeToFile(LinkedList[] list, String fileName) {
        try{
            FileWriter writer = new FileWriter(fileName);
            for(LinkedList item: list) {
                Node temp = item.getHead();
                //use a temporary Node object to get the data from each Linked List

                while(temp != null) {
                    writer.write(temp.getData() + "  ");
                    temp = temp.getNext();
                    //write the word to the text file, followed by some space, then move on to the next element
                }
                writer.write("\n");
                //once the end of the Linked List is reaced, add a newline character
            }
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
