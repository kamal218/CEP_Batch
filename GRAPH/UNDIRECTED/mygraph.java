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
        boolean[] vis = new boolean[n + 1];
        // DFS
        // System.out.println(hasPath(1, 7, vis));
        // System.out.println(allPath(1, 7, "", 0, vis));
        // pair ans = heaviestPath(1, 7, vis);
        // System.out.println(ans.path + "->" + ans.cost);
        // System.out.println(gcc());
        // hamiltonian(8);
        // System.out.println(cycleDetection());
        // BFS
        // bfs(1, 7);
        bfsOpti(1, 7);
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
        addEdge(8, 7, 1);// for hamiltonian cycle
        removeEdge(2, 3);
        removeEdge(3, 8);
        removeEdge(4, 5);
        removeEdge(6, 7);

        // removeVertex(5);

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

    public static boolean hasPath(int src, int dest, boolean[] vis) {

        if (src == dest)
            return true;

        vis[src] = true;

        boolean res = false;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                res = res || hasPath(e.v, dest, vis);
        }
        return res;

    }

    public static int allPath(int src, int dest, String path, int cost, boolean[] vis) {
        if (src == dest) {
            System.out.println(path + "" + dest + "->" + cost);
            return 1;
        }

        vis[src] = true;

        int ans = 0;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                ans += allPath(e.v, dest, path + "" + src + "", cost + e.wt, vis);
        }
        vis[src] = false;
        return ans;
    }

    public static class pair {
        int cost = 0;
        String path = "";

        public pair(int cost, String path) {
            this.cost = cost;
            this.path = path;
        }
    }

    public static pair heaviestPath(int src, int dest, boolean[] vis) {
        if (src == dest) {
            return new pair(0, dest + "");
        }
        vis[src] = true;
        pair ans = new pair(Integer.MIN_VALUE, "");
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                pair recAns = heaviestPath(e.v, dest, vis);
                if (recAns.cost != Integer.MIN_VALUE && recAns.cost + e.wt > ans.cost) {
                    ans.cost = recAns.cost + e.wt;
                    ans.path = src + "" + recAns.path;
                }
            }
        }
        vis[src] = false;
        return ans;
    }

    // public static int kthSmallestpath(int src, int dest) {

    // }

    // GET CONNECTED COMPONENETS
    // METHOD 1 (MARK AT VISITED POINT)
    public static int gcc() {
        boolean[] vis = new boolean[n + 1];
        int compo = 0;
        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                dfs(i, vis);
                compo++;
            }
        }
        return compo;
    }

    public static void dfs(int src, boolean[] vis) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                dfs(e.v, vis);
            }
        }
    }

    // METHOD 2 MART AT GOING POINT
    public static int gcc2() {
        boolean[] vis = new boolean[n + 1];
        int compo = 0;
        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                vis[i] = true;
                dfs2(i, vis);
                compo++;
            }
        }
        return compo;
    }

    public static void dfs2(int src, boolean[] vis) {
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                vis[e.v] = true;
                dfs2(e.v, vis);
            }
        }
    }

    // LEETCOD 1319

    public static int makeConnected(int n, int[][] connections) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] rel : connections) {
            int u = rel[0];
            int v = rel[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        int compo = 0;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(graph, i, vis);
                compo++;
            }
        }

        return compo - 1;
    }

    public static void dfs(ArrayList<Integer>[] graph, int src, boolean[] vis) {
        vis[src] = true;
        for (int e : graph[src]) {
            if (!vis[e]) {
                dfs(graph, e, vis);
            }
        }

    }

    // HAMILTONIAN PATH AND CYCLE

    public static void hamiltonian(int src) {
        boolean[] vis = new boolean[n + 1];
        hamiltonian(src, src, vis, 1, "");// last 1 is number of vertex visitex
    }

    public static void hamiltonian(int osrc, int src, boolean[] vis, int count, String psf) {
        if (count == n) {
            int idx = findEdge(src, osrc);
            if (idx == -1) {
                System.out.println("Hamiltonian Path" + psf + src);
            } else {
                System.out.println("Hamiltonian Cycle" + psf + src + osrc);
            }
        }

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                hamiltonian(osrc, e.v, vis, count + 1, psf + src);
            }
        }
        vis[src] = false;
    }

    // CYCLE DETECTION

    // METHOD 1

    public static boolean cycleDetection() {
        boolean res = false;
        boolean[] vis = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                res = res || cycleDfs(-1, i, vis);
            }
        }
        return res;
    }

    public static boolean cycleDfs(int par, int src, boolean[] vis) {
        vis[src] = true;
        boolean ans = false;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                ans = ans || cycleDfs(src, e.v, vis);
            } else if (par != e.v) {
                return true;
            }
        }
        return ans;
    }

    // METHOD 2
    public static boolean cycleDetection2() {
        boolean res = false;
        int[] par = new int[n + 1];
        Arrays.fill(par, -1);
        for (int i = 1; i <= n; i++) {
            if (par[i] == -1) {
                par[i] = i;
                res = res || cycleDfs2(i, par);
            }
        }
        return res;
    }

    public static boolean cycleDfs2(int src, int[] par) {
        boolean ans = false;
        for (Edge e : graph[src]) {
            if (par[e.v] == -1) {
                par[e.v] = src;
                ans = ans || cycleDfs2(e.v, par);
            } else if (par[src] != e.v) {
                return true;
            }
        }
        return ans;
    }

    // BFS MARK AT REMOVAL

    public static void bfs(int src, int data) {
        Queue<Integer> que = new LinkedList<>();
        que.add(src);
        boolean[] vis = new boolean[n + 1];
        int datalevel = -1;
        int level = 0;
        boolean isCyclic = false;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int top = que.poll();
                if (datalevel == -1 && top == data) {
                    datalevel = level;
                }
                if (vis[top]) {
                    isCyclic = true;
                    continue;
                }
                vis[top] = true;
                for (Edge e : graph[top]) {
                    if (!vis[e.v]) {
                        que.add(e.v);
                    }
                }
            }
            level++;
        }
        System.out.println(data + " is found at llevel: " + datalevel);
        System.out.println("CyclicGraph: " + isCyclic);
    }

    // BFS MARK AT ADDING

    public static void bfsOpti(int src, int data) {
        Queue<Integer> que = new LinkedList<>();
        que.add(src);
        int[] vis = new int[n + 1];// visited of parent
        Arrays.fill(vis, -1);
        int datalevel = -1;
        int level = 0;
        boolean isCyclic = false;
        vis[src] = src;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int top = que.poll();
                if (top == data) {
                    datalevel = level;
                }

                for (Edge e : graph[top]) {
                    if (vis[e.v] == -1) {
                        vis[e.v] = top;
                        que.add(e.v);
                    } else if (vis[top] != e.v) {
                        isCyclic = true;
                    }
                }
            }
            level++;
        }

        System.out.println(data + "is found at llevel: " + datalevel);
        System.out.println("CyclicGraph: " + isCyclic);
    }
}