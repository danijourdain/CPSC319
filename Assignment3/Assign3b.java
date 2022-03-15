import java.io.*;
import java.util.regex.*;

public class Assign3b {
    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("Invalid number of command-line arguments! Program terminating");
            System.exit(1);
            //check if the user entered the right number of command-line arguments
        }

        String inputFile = checkTXT(args[0]);
        String outputFile1 = checkTXT(args[1]);
        String outputFile2 = checkTXT(args[2]);
        //check to see all both command-line arguments are valid

        String[] inputData = readFile(inputFile);
        //read the data into a String array

        AVLTree tree = new AVLTree();

        System.out.println("Inserting elements...");
        for(String item: inputData) {
            checkOperation(item, tree);
        }
        //insert all elements into the AVL tree

        System.out.println("\nTraversing tree in order...");
        String result = tree.inOrder(tree.getRoot());
        writeFile(outputFile1, result);
        //perform a depth-first, in order traversal, then print those results and write them to a text file

        System.out.println("\nTraversing tree breadth first...");
        result = tree.breadthFirst(inputData.length);
        writeFile(outputFile2, result);
        //perform a breadth-first traversal, then print those results and write them to a text file
    }

    /**
     * This method takes a filename as an input and checks if it is a valid text file by checking the extension on the file.
     * @param filename The filename to check.
     * @return The filename if it is valid, otherwise the program will encounter an error and terminate.
     */
    public static String checkTXT(String filename) {
        String txt = filename.substring(filename.length() - 4);
        //create a substring of the last 4 characters of the string

        if(txt.equals(".txt") == false) {
            System.out.println("Invlid text file name. Program terminating.");
            System.exit(1);
        }

        return filename;
    }

    /**
     * This method takes a filename and reads the data into a string array, where each line of the text file is an element in the array.
     * @param fileName The input filename to read from.
     * @return A string array containing each line of the file as an element.
     */
    public static String[] readFile(String fileName) {
        /* code for reading from a text file adapted 
         * from Week 4 tutorial slides, slide 12 */

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch(FileNotFoundException e) {
            System.out.println("File not found. Program terminating");
            System.exit(1);
            return null;
        }

        String s;
        String dataList = new String();
        //s is the string for the current line of the file and wordList
            //is a multi-line string holding all of the words in the list separated by a newLine character

        try {
            while((s = reader.readLine()) != null) {
                dataList = dataList.concat(s + "\n");
                //add each word to the long string line by line
            }
            reader.close();
        } catch(IOException e) {
            System.out.println("Unexpected error. Program terminating");
            System.exit(1);
        }

        String[] dataArray = dataList.split("\n");
        //use the split String method to turn the long String 
         //into a String array with one word per element

        return dataArray;
    }

    /**
     * This function takes a string that contains an opcode, then some student data and performs the appropriate operation on that data.
     * @param item The String containing an opcode and data.
     * @param tree The binary search tree that the data will be added to or removed from
     */
    public static void checkOperation(String item, AVLTree tree) {
        Pattern regex = Pattern.compile("([ID]{1})(.{41})");
        Matcher match = regex.matcher(item);
        match.find();
        //separate the text file into two parts: the operation code and the data
        
        if(match.group(1).equals("I")) {
            tree.insert(match.group(2));
            //use insert to create a new node with the data
        }
        else {
            System.out.println("Invalid operation code. Program terminating.");
            System.exit(1);
            //if the operation code is not I or D stop the program
        }
    }

    /**
     * This method writes a String to a text file.
     * @param fileName The output text file.
     * @param result The String to be written to the file.
     */
    public static void writeFile(String fileName, String result) {
        System.out.println(result);
        //print out the result string first

        try{
            FileWriter writer = new FileWriter(fileName);
            writer.write(result);
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
