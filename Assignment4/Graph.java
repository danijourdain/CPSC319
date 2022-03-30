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
    public String depthFirstSearch(int start, int end) {
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
            if(this.adjacencyMatrix[start][j] > 0 && visited[j] == false) {
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
    public String breadthFirstSearch(int start, int end) {
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
                if(this.adjacencyMatrix[v][u] > 0 && !(visited[u])) {
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

    /**
     * This method searches for the fastest path through a directed graph
     * @param start The node to begin the search at.
     * @param end The node to end the search.
     * @return A string containing the path, if one was found, otherwise returns the start and end nodes separated by -1.
     */
    public String dijkstraSearch(int start, int end) {
        /* Dijkstra's algorithm adapted from March 29 tutorial */

        int[] currDist = new int[this.numVertices];
        boolean[] toBeChekced = new boolean[this.numVertices];
        int[] previous = new int[this.numVertices];

        for(int i = 0; i < this.numVertices; i++) {
            currDist[i] = Integer.MAX_VALUE;
            toBeChekced[i] = true;
            previous[i] = -1;
            //initialize the values in all the arrays
        }

        currDist[start] = 0;
        int numChecked = 0;
        //set the distance to the starting vertex to 0

        while(numChecked < this.numVertices) {
            int min = Integer.MAX_VALUE;
            int v = -1;
            for(int i = 0; i < this.numVertices; i++) {
                if(currDist[i] < min && toBeChekced[i]) {
                    min = currDist[i];
                    v = i;
                    //find the unchecked node with the smallest distance 
                }
            }
            
            if(v == -1) {
                break;
                //if no minimum was found, there is no path between the nodes, so break the loop
            }

            toBeChekced[v] = false;
            numChecked++;

            for(int u = 0; u < this.numVertices; u++) {
                if(this.adjacencyMatrix[v][u] > 0 && toBeChekced[u]) {
                    if(currDist[u] > (currDist[v] + this.adjacencyMatrix[v][u])) {
                        //if a shorter path has been found, update that path
                        currDist[u] = currDist[v] + this.adjacencyMatrix[v][u];
                        previous[u] =v;
                    }
                }
            }
        }
        
        return traceRoute(previous, start, end);
    }
}
