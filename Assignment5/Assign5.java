import java.io.*;

public class Assign5 {
    /**
     * Main method for Assignment 5.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        if(args.length < 2 || args.length > 3) {
            System.out.println("Invalid number of command-line arguments. Promgram terminating.");
            System.exit(1);
        }

        String inputFile = checkTXT(args[0]);
        String outputFile = checkTXT(args[1]);
        //check that the command-line arguments are valid

        String[] fileContents = readFile(inputFile);
        //read the input file into a String array
        HashTable ht = new HashTable(fileContents.length);
        //create a new HashTable object

        for(String item: fileContents) {
            ht.insert(item);
            //insert each item into the HashTable
        }

        String analysis = ht.analyzePerformance();
        //analyze the HashTable
        writeFile(outputFile, analysis);
        //write the result to the output file
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
     * This method takes a string and filename and writes the string to the text file.
     * @param fileName The name of the file to be written to.
     * @param result The String to be written to the file.
     */
    public static void writeFile(String fileName, String result) {
        try{
            FileWriter writer = new FileWriter(fileName);
            writer.write(result);
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
