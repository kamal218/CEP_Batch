import java.util.*;

public class mygraph {
    public static class Edge {
        int v = 0;
        int wt = 0;
    }

    static ArrayList<Edge>[] graph;// graph ds
    static int v = 9;

    public static void main(String[] args) {
        init();

    }

    public static void addEdge(int u, int v, int wt) {

    }

    public static void display() {

    }

    public static void removeEdge(int u, int v) {

    }

    public static void removeVertex(int vtx) {

    }

    public static void init() {
        graph = new ArrayList[n + 1];
        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }
    }

}