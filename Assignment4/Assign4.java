import java.io.*;

public class Assign4 {
    public static void main(String[] args) {
        if(args.length != 4) {
            System.out.println("Invalid number of command-line arguments. Program terminating.");
            System.exit(1);
        }

        if(checkTXT(args) == false) {
            System.out.println("One or more of the entered filenamesa are invalid. Filenames must be" +  
                "entered with the .txt extension. Program terminating.");
                System.exit(1);
        }
        
        String adjacencyFile = args[0];
        String queryFile = args[1];
        String depthFirstFile = args[2];
        String breadthFirstFile = args[3];
        //extract all the text file names if they are all file

        int[][] adjacencyMatrix = readAdjacencyFile(adjacencyFile);
        int[][] query = readQuery(queryFile);
        //use helper methods to read the two input filess

        for(int i = 0; i < adjacencyMatrix.length; i++) {
            for(int j = 0; j < adjacencyMatrix[i].length; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\n");

        for(int i = 0; i < query.length; i++) {
            for(int j = 0; j < query[i].length; j++) {
                System.out.print(query[i][j] + " ");
            }
            System.out.println();
        }
    }
    
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
}