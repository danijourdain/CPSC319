import java.util.Arrays;

public class Graph {
    private int[][] adjacencyMatrix;
    private int numVertices;

    /**
     * Constructor for the Graph class.
     * @param adjacencyMatrix The adjacency matrix representing the graph's relationships
     */
    public Graph(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.numVertices = adjacencyMatrix.length;
    }

    /**
     * Driver code for the depth-first search. Will traverse the graph depth-first until the desired node is found.
     * @param start The node to begin the search from.
     * @param end The desired end node.
     * @return A String containing a list of nodes to visit to reach the end one. If no path exists, will return -1 instead.
     */
    public String DepthFirstSearch(int start, int end) {
        if(start == end) {
            return String.valueOf(start);
        }
        
        boolean[] visited = new boolean[this.numVertices];
        //this keeps track of which nodes have already been checked

        String result = dfs(start, end, visited);

        if(result == null) {
            return (start + ", -1, " + end);
            //if the search was unsuccessful, return the start and end nodes separated by -1
        }
        else {
            result = start + "\t" + result;
            result = result.replaceAll("\t", ", ");
            result = result.substring(0, result.length() - 4);

            return result;
            //otherwise format the string and return that result
        }
    }

    /**
     * the main code used for the depth-first search.
     * @param start The starting node
     * @param end the desired end node
     * @param visited a boolean array indicating what nodes have already been visited.
     * @return The current path to the end node.
     */
    private String dfs(int start, int end, boolean[] visited) {
        /* depth first search alorithm adapted from March 22 Tutorial */

        if(start == end) {
            return "\t";
            //if at the desired node, return
        }

        visited[start] = true;

        for(int j = 0; j < this.numVertices; j++) {
            if(this.adjacencyMatrix[start][j] != 0 && visited[j] == false) {
                //if there is a path between start and j, and j has not been visited, visit it
                String result = dfs(j, end, visited);

                if(result != null) {
                    return (j + "\t" + result);
                    //if there is a result, add it to the current node, and return
                }
            }
        }

        //if no path was found, return null
        return null;
    }

    /**
     * Breadth-first search method.
     * @param start Starting node for the search
     * @param end desired end node.
     * @return A String representing a list of nodes to visit to reach the end if a path is found. Otherwise returns the start and end nodes separated by a -1.
     */
    public String BreadthFirstSearch(int start, int end) {
        /* breadth-first search adapted from March 23 tutorial */

        Queue queue = new Queue(this.numVertices);
        boolean[] visited = new boolean[this.numVertices];
        //set up a queue and boolean array to keep track of the vertices

        int[] prevVertex = new int[this.numVertices];
        Arrays.fill(prevVertex, -1);
        //create an integer array to keep track of each node's predecessor 
        //set all values to -1 by default

        queue.enqueue(start);
        visited[start] = true;

        while(!(queue.isEmpty())) {
            int v = queue.dequeue();
            visited[v] = true;

            for(int u = 0; u < this.numVertices; u++) {
                if(this.adjacencyMatrix[v][u] == 1 && !(visited[u])) {
                    //if u is a neighbour of v and it hasn't been visited, visit it an add it to the queue
                    queue.enqueue(u);
                    visited[u] = true;
                    prevVertex[u] = v;

                    if(u == end) {
                        queue.clear();
                        break;
                        //if the endpoint has been reached, empty the queue and exit both loops
                    }
                }
            }
        }

        return traceRoute(prevVertex, start, end);
    }

    /**
     * Helper method to format the result of the breadth-first search
     * @param prev an array containing the predecessor of each node
     * @param start the starting point of the search
     * @param end the end point of the search
     * @return A String representing a list of nodes to visit to reach the end if a path is found. Otherwise returns the start and end nodes separated by a -1.
     */
    private String traceRoute(int[] prev, int start, int end) {
        if(prev[end] == -1) {
            return (start + ", -1, " + end);
            //if the end node has no predecessor, no path was found.
        }

        int node = end;
        int[] reversePath = new int[this.numVertices];
        int i = 0;
        //create an integer array to hold the path, built from end node to start node

        while(node != -1) {
            reversePath[i++] = node;
            node = prev[node];
            //add each node's predecessor to the reversePath
        }

        i--;
        StringBuilder result = new StringBuilder();
        while(i >= 0) {
            result.append(reversePath[i--] + ", ");
            //append each array element to the result string in reverse order
        }

        result.delete(result.length() - 2, result.length());
        return result.toString();
    }
}
