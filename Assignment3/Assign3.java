import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;


public class Assign3 {
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
        //check to see if both command-line arguments are valid

        String[] inputData = readFile(inputFile);
        //read the data from the input file into a string array

        BinarySearchTree tree = new BinarySearchTree();

        for(String item: inputData) {
            checkOperation(item, tree);
        }
    }
    
    /**
     * This method takes a filename and checks the extension on it to ensure it is a valid text file.
     * @param fileName The filename to check.
     * @return The filename if it is valid, otherwise the program will terminate.
     */
    public static String checkTXT(String fileName) {
        String txt = fileName.substring(fileName.length() - 4);
        //create a substring of the last 4 characters of the string

        if(txt.equals(".txt") == false) {
            System.out.println("Invlid text file name. Program terminating.");
            System.exit(1);
        }

        return fileName;
    }

    /**
     * This method takes a filename and reads the data into a String array where 
     * each line in the text file is an element of the array.
     * @param fileName The file to read the data from.
     * @return The data organized into a String array.
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

    public static void checkOperation(String item, BinarySearchTree tree) {
        Pattern regex = Pattern.compile("([ID]{1})(.{41})");
        Matcher match = regex.matcher(item);
        match.find();
        
        if(match.group(1).equals("I")) {
            System.out.println("Inserting element...");
            tree.insert(match.group(2));
            //use insert to create a new node with the data


        }
        else if(match.group(1).equals("D")) {
            System.out.println("Deleting element...");
            String key = Data.findLastName(match.group(2));
        }
        else {
            System.out.println("Invalid operation code. Program terminating.");
            System.exit(1);
            //if the operation code is not I or D stop the program
        }
    }

}
