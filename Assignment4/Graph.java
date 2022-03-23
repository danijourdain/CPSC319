public class Graph {
    private int[][] adjacencyMatrix;
    private int numVertices;

    public Graph(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.numVertices = adjacencyMatrix.length;
    }

    public int[] DepthFirstSearch(int start, int end) {
        boolean[] visited = new boolean[this.numVertices];
        visited[start] = true;

        
    }
}
