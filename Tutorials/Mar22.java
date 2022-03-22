public class Mar22 {
    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
            {0,0,0,1,0},
            {0,0,1,0,1},
            {1,0,0,0,0},
            {0,1,0,0,1},
            {0,0,0,0,0}
        };

        int[] vertices = {0, 1, 2, 3, 4};
        boolean[] visited = new boolean[vertices.length];
        dfsTraversal(0, adjacencyMatrix, visited, vertices);
    }

    static void dfsTraversal(int start, int mat[][], boolean[] visited, int[] vs) {
        System.out.println("visited node " + vs[start]);
        visited[start] = true;

        for(int j = 0; j < vs.length; j++) {
            if(mat[start][j] == 1 && visited[j] == false) {
                //j is connected to start
                dfsTraversal(j, mat, visited, vs);

            }
        }
    }

    static void bfsTraveral() {}
}

