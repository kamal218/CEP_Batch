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
    static int n = 6;

    public static void main(String[] args) {
        init();
        addAll();
        boolean[] vis = new boolean[n + 1];
        // topoSort();
        // cycleDetectionDfs();
        // cycleDetectionDfs2();
        kahnsAlgo();
        System.out.println();
        display();
    }

    public static void addAll() {
        addEdge(5, 0, 10);
        addEdge(4, 0, 10);
        addEdge(5, 2, 2);
        addEdge(4, 1, 8);
        addEdge(2, 3, 3);
        addEdge(3, 1, 2);
        // addEdge(3, 0, 10);
    }

    public static void addEdge(int u, int v, int wt) {
        graph[u].add(new Edge(v, wt));
    }

    public static void display() {
        for (int i = 0; i < n; i++) {
            System.out.print(i + "-> ");
            for (Edge e : graph[i]) {
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
        // O(v)
        int idx1 = findEdge(u, v);
        graph[u].remove(idx1);

    }

    public static void removeVertex(int vtx) {
        ArrayList<Edge> list = graph[vtx];
        for (int i = list.size() - 1; i >= 0; i--) {
            int nbr = list.get(i).v;
            removeEdge(vtx, nbr);
        }

    }

    public static void init() {
        graph = new ArrayList[n + 1];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public static void topoSort() {
        boolean[] vis = new boolean[n];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfsTopo(i, vis, st);
            }
        }
        while (st.size() > 0) {
            System.out.print(st.pop() + " ");
        }
    }

    public static void dfsTopo(int src, boolean[] vis, Stack<Integer> order) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                dfsTopo(e.v, vis, order);
            }
        }
        order.push(src);
    }

    // CYCLE DETECTION

    public static boolean cycleDetectionDfs() {
        boolean[] vis = new boolean[n];
        boolean[] currPath = new boolean[n];
        boolean res = false;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                res = res || dfsCycle(i, vis, currPath);
            }
        }
        System.out.println(res);
        return res;
    }

    public static boolean dfsCycle(int src, boolean[] vis, boolean[] currPath) {
        vis[src] = true;
        currPath[src] = true;
        boolean res = false;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                res = res || dfsCycle(e.v, vis, currPath);
            } else if (currPath[e.v]) {
                return true;
            }
        }
        currPath[src] = false;
        return res;
    }

    // METHOD 2
    public static boolean cycleDetectionDfs2() {
        int[] vis = new int[n];
        // 0-> unvisited
        // 1-> currparh
        // 2-> prevPath
        boolean res = false;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                res = res || dfsCycle(i, vis, st);
            }
        }
        if (res) {
            System.out.println("No valid topo sort");
        } else {
            while (st.size() > 0) {
                System.out.print(st.pop() + " ");
            }
        }
        return res;
    }

    public static boolean dfsCycle(int src, int[] vis, Stack<Integer> st) {
        vis[src] = 1;
        boolean res = false;
        for (Edge e : graph[src]) {
            if (vis[e.v] == 0) {
                res = res || dfsCycle(e.v, vis, st);
            } else if (vis[e.v] == 1) {
                return true;
            }
        }
        vis[src] = 2;
        st.push(src);
        return res;
    }

    // KAHNS ALGO

    public static void kahnsAlgo() {
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (Edge e : graph[i]) {
                indegree[e.v]++;
            }
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                que.add(i);
            }
        }
        List<Integer> order = new ArrayList<>();
        while (que.size() > 0) {
            int top = que.poll();
            order.add(top);
            for (Edge e : graph[top]) {
                indegree[e.v]--;
                if (indegree[e.v] == 0) {
                    que.add(e.v);
                }
            }
        }
        if (order.size() == n) {
            System.out.println(order);
        } else {
            System.out.println("Cyclic Graph");
        }
    }

    public static int[] findOrder(int n, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : prerequisites) {
            int u = e[1];
            int v = e[0];
            graph[u].add(v);
        }
        List<Integer> ans = topoSort(graph);
        if (ans.size() < n) {
            return new ArrayList<>();
        }
        return ans;
    }

    public static List<Integer> topoSort(ArrayList<Integer>[] graph) {
        int n = graph.length;
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int e : graph[i]) {
                indegree[e]++;
            }
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                que.add(i);
            }
        }
        List<Integer> order = new ArrayList<>();
        while (que.size() > 0) {
            int top = que.poll();
            order.add(top);
            for (int e : graph[top]) {
                indegree[e]--;
                if (indegree[e] == 0) {
                    que.add(e);
                }
            }
        }
        return order;
    }

    
}