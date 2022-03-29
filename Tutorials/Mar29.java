import java.util.*;

public class Mar29 {
    //djikstra's algorithm

    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
            {0,2,0,5,4,0,0,0,0},
            {0,0,0,0,1,0,0,0,0},
            {0,3,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,2,0,0},
            {0,0,0,0,0,3,0,6,0},
            {0,0,4,0,0,0,0,3,0},
            {0,0,0,0,0,0,0,1,0},
            {0,0,0,0,0,0,0,0,1},
            {0,0,0,0,0,1,0,0,0}
        };

        int[] vs = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        djikstra(adjacencyMatrix, vs, 0);
        djikstraPriority(adjacencyMatrix, vs, 0);
    }

    public static void djikstra(int[][] mat, int[] vs, int first) {
        int[] currDist = new int[vs.length];
        boolean[] toBeChecked = new boolean[vs.length];
        int[] pred = new int[vs.length];

        for(int i = 0; i < vs.length; i++) {
            currDist[i] = Integer.MAX_VALUE;
            toBeChecked[i] = true;
            pred[i] = -1;
            //initialize all values in arrays
        }

        currDist[first] = 0;
        int numChecked = 0;
        while(numChecked < vs.length) {
            int min = Integer.MAX_VALUE;
            int v = -1;
            for(int i = 0; i < vs.length; i++) {
                if(currDist[i] < min && toBeChecked[i]) {
                    min = currDist[i];
                    v = i;
                }
            }

            toBeChecked[v] = false;
            numChecked++;

            for(int u = 0; u< vs.length; u++) {
                if(mat[v][u] > 0 && toBeChecked[u] == true) {
                    if(currDist[u] > currDist[v] + mat[v][u]) {
                        currDist[u] = currDist[v] + mat[v][u];
                        pred[u] = v;
                    }
                }
            }
        }

        for(int i = 0; i < vs.length; i++) {
            System.out.println("Shortest distance from " + vs[first] + " to " + vs[i] + " is " + currDist[i]);
        }
    }

    public static void djikstraPriority(int[][]mat, int[]vs, int first) {
        int[] currDist = new int[vs.length];
        boolean[] toBeChecked = new boolean[vs.length];
        int[] pred = new int[vs.length];

        for(int i = 0; i < vs.length; i++) {
            currDist[i] = Integer.MAX_VALUE;
            toBeChecked[i] = true;
            pred[i] = -1;
            //initialize all values in arrays
        }

        currDist[first] = 0;
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return currDist[a] - currDist[b];
            }
        };
        PriorityQueue<Integer> pq = new PriorityQueue<>(comparator);
        pq.add(first);

        while(!pq.isEmpty()) {
            int v = pq.poll();
            toBeChecked[v] = false;

            for(int u = 0; u< vs.length; u++) {
                if(mat[v][u] > 0 && toBeChecked[u] == true) {
                    if(currDist[u] > currDist[v] + mat[v][u]) {
                        currDist[u] = currDist[v] + mat[v][u];
                        pred[u] = v;
                        pq.add(u);
                    }
                }
            }
        }

        for(int i = 0; i < vs.length; i++) {
            System.out.println("Shortest distance from " + vs[first] + " to " + vs[i] + " is " + currDist[i]);
        }
    }
}