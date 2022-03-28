import java.io.*;

public class Assign4 {
    /**
     * Main method for Assignment 4.
     * @param args Command-line arguments used to indicate input and output files
     */
    public static void main(String[] args) {
        if(args.length != 4) {
            System.out.println("Invalid number of command-line arguments. Program terminating.");
            System.exit(1);
        }
        //check the number of command-line arguments to see if they are valid

        if(checkTXT(args) == false) {
            System.out.println("One or more of the entered filenames are invalid. Filenames must be" +  
                "entered with the .txt extension. Program terminating.");
                System.exit(1);
        }
        //check each of the command-line arguments to see if they are valid filenames
        
        String adjacencyFile = args[0];
        String queryFile = args[1];
        String depthFirstFile = args[2];
        String breadthFirstFile = args[3];
        //extract all the text file names if they are all valid

        int[][] adjacencyMatrix = readAdjacencyFile(adjacencyFile);
        int[][] query = readQuery(queryFile);
        //use helper methods to read the two input files

        Graph graph = new Graph(adjacencyMatrix);

        StringBuilder dfsResult = new StringBuilder();
        StringBuilder bfsResult = new StringBuilder();

        for(int i = 0; i < query.length; i++) {
            dfsResult.append(graph.DepthFirstSearch(query[i][0], query[i][1]) + "\n");
            bfsResult.append(graph.BreadthFirstSearch(query[i][0], query[i][1]) + "\n");
            //traverse the graph using both depth-first and breadth-first searches for each query
        }

        writeFile(depthFirstFile, dfsResult.toString());
        writeFile(breadthFirstFile, bfsResult.toString());
        //save the results to two text files
    }
    
    /**
     * A helper method to make sure all filenames are valid.
     * @param filenames An array of strings containing potential filenames.
     * @return True if all elements in the String array end with the ".txt" extension, false otherwise.
     */
    public static boolean checkTXT(String[] filenames) {
        for(String name: filenames) {
            String txt = name.substring(name.length() - 4);
            //create a substring of the last 4 characters of the string
    
            if(txt.equals(".txt") == false) {
                return false;
            }
        }
        //check all of the given filenames
        
        return true;
    }

    /**
     * Helper method to read an adjacency matrix from a text file.
     * @param fileName The file to be read. 
     * @return A 2D array containing the adjacency matrix for the graph.
     */
    public static int[][] readAdjacencyFile(String fileName) {
        /* code for reading from a text file adapted from Week 4 tutorial slides, slide 12 */
        
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch(FileNotFoundException e) {
            System.out.println("File not found. Program terminating");
            System.exit(1);
            return null;
        }

        String s;
        int lines = countLines(fileName), i = 0;
        int[][] matrix = new int[lines][lines];
        //setup all the needed variables

        try {
            while((s = reader.readLine()) != null) {
                String[] line = s.split("\t");
                for(int j = 0; j < lines; j++) {
                    matrix[i][j] = Integer.parseInt(line[j]);
                    //read the contents of each line of the text file into one of the inner arrays
                }
                i++;
            }
        } catch (IOException e) {
            System.out.println("Unexpected error. Program terminating");
            System.exit(1);
        }

        return matrix;
    }

    /**
     * Helper method to read a query in from a text file.
     * @param fileName The file to be used.
     * @return a 2D array with the queries to be used.
     */
    public static int[][] readQuery(String fileName) {
        /* code for reading from a text file adapted from Week 4 tutorial slides, slide 12 */

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch(FileNotFoundException e) {
            System.out.println("File not found. Program terminating");
            System.exit(1);
            return null;
        }

        String s;
        int lines = countLines(fileName), i = 0;
        int[][] matrix = new int[lines][2];
        //setup all the needed variables. The inner array for the matrix can be of size 2 since the query will only have 2 columns

        try {
            while((s = reader.readLine()) != null) {
                String[] line = s.split("\t");
                matrix[i][0] = Integer.parseInt(line[0]);
                matrix[i][1] = Integer.parseInt(line[1]);
                i++;
                //add the text file values to the arrays 
            }
        } catch (IOException e) {
            System.out.println("Unexpected error. Program terminating");
            System.exit(1);
        }

        return matrix;
    }

    /**
     * Helper method to count the number of lines in a text file.
     * @param fileName The file with an unknown number of lines.
     * @return The number of lines in the file.
     */
    private static int countLines(String fileName) {
        /* code for counting number of lines from text file adapted from https://www.tutorialspoint.com/How-to-read-a-2d-array-from-a-file-in-java */
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Program terminating");
            System.exit(1);
            return 0;
        }

        int i = 0;

        try {
            while(reader.readLine() != null) {
                i++;
            }
            //iterate through the files to count the number of lines
            reader.close();
        } catch(IOException e) {
            System.out.println("Unexpected error. Program terminating.");
            System.exit(1);
        }

        return i;
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