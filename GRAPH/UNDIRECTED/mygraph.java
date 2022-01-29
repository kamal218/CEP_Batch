import java.util.*;

public class mygraph {
    public static class Edge {
        int v = 0;
        int wt = 0;

        public Edge(int v, int wt) {
            this.v = v;
            this.wt = wt;
        }

        @Override

        public String toString() {
            return "(" + v + "," + wt + ") ";
        }
    }

    static ArrayList<Edge>[] graph;// graph ds
    static int n = 9;

    public static void main(String[] args) {
        init();
        addAll();
        display();
    }

    public static void addAll() {
        addEdge(1, 2, 10);
        addEdge(1, 4, 10);
        addEdge(2, 3, 2);
        addEdge(3, 4, 8);
        addEdge(3, 8, 3);
        addEdge(3, 9, 2);
        addEdge(8, 9, 5);
        addEdge(4, 5, 5);
        addEdge(5, 6, 7);
        addEdge(5, 7, 6);
        addEdge(6, 7, 6);
        // removeEdge(4, 5);
        removeVertex(5);

    }

    public static void addEdge(int u, int v, int wt) {
        graph[u].add(new Edge(v, wt));
        graph[v].add(new Edge(u, wt));
    }

    public static void display() {
        for (int i = 1; i <= n; i++) {
            System.out.print(i + "-> ");
            for (Edge e : graph[i]) {
                // System.out.print("(" + e.v + "," + e.wt + ") ");
                System.out.print(e);
            }
            System.out.println();
        }
    }

    public static int findEdge(int u, int v) {
        for (int i = 0; i < graph[u].size(); i++) {
            Edge e = graph[u].get(i);
            if (e.v == v) {
                return i;
            }
        }
        return -1;
    }

    public static void removeEdge(int u, int v) {
        // O(2v)
        int idx1 = findEdge(u, v);
        graph[u].remove(idx1);

        int idx2 = findEdge(v, u);
        graph[v].remove(idx2);

    }

    public static void removeVertex(int vtx) {
        ArrayList<Edge> list = graph[vtx];
        for (int i = list.size() - 1; i >= 0; i--) {
            int nbr = list.get(i).v;
            removeEdge(vtx, nbr);
        }

        // while (graph[vtx].size() > 0) {
        // Edge e = graph[vtx].get(0);
        // removeEdge(vtx, e.v);
        // }
    }

    public static void init() {
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    // QUESTIONS

    public static boolean hasPath(int src, int dest) {

    }

    public static int allPath(int src, int dest, String path, String cost) {

    }

    public class pair {
        int cost = 0;
        String path = "";

        public pair(int cost, int path) {
            this.cost = cost;
            this.path = path;
        }
    }

    public static pair heaviestPath(int src, int dest) {

    }

    public static int kthSmallestpath(int src, int dest) {

    }

}