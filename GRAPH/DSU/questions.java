import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.lang.model.type.UnionType;

public class questions {

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

    public static void main(String[] args) {
        int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 0 }, { 4, 2 } };
        boolean cycle = cycleDetection(5, edges);
        System.out.println(cycle);
        ArrayList<Edge>[] graph = SpanningTree(5, edges);
        display(5, graph);
    }

    // CYCLE DETECTION
    public static boolean cycleDetection(int n, int[][] edges) {
        dsu uf = new dsu(n);
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int val = uf.union(u, v);
            if (val == -1) {
                return true;
            }
        }
        return false;
    }

    // SPANNING TREE

    public static ArrayList<Edge>[] SpanningTree(int n, int[][] edges) {
        ArrayList<Edge>[] graph = new ArrayList[n];
        init(graph, n);
        dsu uf = new dsu(n);
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int val = uf.union(u, v);
            if (val != -1) {
                addEdge(graph, u, v, 0);
            }
        }
        return graph;
    }

    public static void init(ArrayList<Edge>[] graph, int n) {
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int wt) {
        graph[u].add(new Edge(v, wt));
        graph[v].add(new Edge(u, wt));
    }

    public static void display(int n, ArrayList<Edge>[] graph) {
        for (int i = 0; i < n; i++) {
            System.out.print(i + "-> ");
            for (Edge e : graph[i]) {
                // System.out.print("(" + e.v + "," + e.wt + ") ");
                System.out.print(e);
            }
            System.out.println();
        }
    }

    // GET CONNECTED COMPONENTS

    public static int gcc(int n, int[][] edges) {
        dsu uf = new dsu(n);
        int compo = n;
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int val = uf.union(u, v);
            if (val != -1) {
                compo--;
            }
        }
        return compo;
    }

    // NUMBER OF ISLAND

    public int numIslands(char[][] grid) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int r = grid.length;
        int c = grid[0].length;
        dsu uf = new dsu(r * c);
        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    int u = i * c + j;
                    for (int[] d : dir) {
                        int nr = i + d[0];
                        int nc = j + d[1];
                        if (nr >= 0 && nc >= 0 && nr < r && nc < c
                                && grid[nr][nc] == '1') {
                            int v = nr * c + nc;
                            int val = uf.union(u, v);
                            if (val != -1) {// different parent
                                ans--;
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }

    public int findPar(int[] par, int u) {
        return (par[u] == u ? u : (par[u] = findPar(par, par[u])));
    }

    public int maxAreaOfIsland(int[][] grid) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int r = grid.length;
        int c = grid[0].length;
        int[] par = new int[r * c];
        int[] size = new int[r * c];
        for (int i = 0; i < r * c; i++) {
            par[i] = i;
            size[i] = 1;
        }

        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    int u = i * c + j;
                    int l1 = findPar(par, u);
                    for (int[] d : dir) {
                        int nr = i + d[0];
                        int nc = j + d[1];
                        if (nr >= 0 && nc >= 0 && nr < r && nc < c && grid[nr][nc] == 1) {
                            int v = nr * c + nc;
                            int l2 = findPar(par, v);
                            if (l1 != l2) {
                                par[l2] = l1;
                                size[l1] = size[l1] + size[l2];
                            }
                        }
                    }
                    ans = Math.max(ans, size[l1]);
                }
            }
        }
        return ans;
    }

    public int largestIsland(int[][] grid) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int r = grid.length;
        int c = grid[0].length;
        int[] par = new int[r * c];
        int[] size = new int[r * c];
        for (int i = 0; i < r * c; i++) {
            par[i] = i;
            size[i] = 1;
        }
        HashSet<Integer> leader = new HashSet<>();

        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    int u = i * c + j;
                    int l1 = findPar(par, u);
                    for (int[] d : dir) {
                        int nr = i + d[0];
                        int nc = j + d[1];
                        if (nr >= 0 && nc >= 0 && nr < r && nc < c && grid[nr][nc] == 1) {
                            int v = nr * c + nc;
                            int l2 = findPar(par, v);
                            if (l1 != l2) {
                                par[l2] = l1;
                                size[l1] = size[l1] + size[l2];
                            }
                        }
                    }
                    ans = Math.max(ans, size[l1]);// max area
                }
            }
        }

        int sum = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0) {
                    sum = 0;
                    leader.clear();
                    for (int[] d : dir) {
                        int nr = i + d[0];
                        int nc = j + d[1];
                        if (nr >= 0 && nc >= 0 && nr < r && nc < c && grid[nr][nc] == 1) {
                            int v = nr * c + nc;
                            int l2 = findPar(par, v);
                            if (!leader.contains(l2)) {
                                sum += size[l2];
                                leader.add(l2);
                            }
                        }
                    }
                    ans = Math.max(ans, sum + 1);// max after converting
                }
            }
        }

        return ans;
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int r = m;
        int c = n;
        int[] par = new int[r * c];
        for (int i = 0; i < r * c; i++) {
            par[i] = i;
        }
        int[][] grid = new int[r][c];
        List<Integer> ans = new ArrayList<>();
        int count = 0;
        for (int[] e : positions) {
            int i = e[0];
            int j = e[1];
            if (grid[i][j] == 1) {
                ans.add(count);
                continue;
            }
            grid[i][j] = 1;
            count++;
            for (int[] d : dir) {
                int nr = i + d[0];
                int nc = j + d[1];
                if (nr >= 0 && nc >= 0 && nr < r && nc < c && grid[nr][nc] == 1) {
                    int u = i * c + j;
                    int v = nr * c + nc;
                    int p1 = findPar(par, u);
                    int p2 = findPar(par, v);
                    if (p1 != p2) {
                        count--;
                        par[p2] = p1;
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }

    // WITHOUT GRID

    public List<Integer> numIslands2_(int m, int n, int[][] positions) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int r = m;
        int c = n;
        int[] par = new int[r * c];
        for (int i = 0; i < r * c; i++) {
            par[i] = -1;// water initially
        }
        List<Integer> ans = new ArrayList<>();
        int count = 0;
        for (int[] e : positions) {
            int i = e[0];
            int j = e[1];
            int u = i * c + j;
            if (par[u] != -1) {
                ans.add(count);
                continue;
            }
            par[u] = u;// marking as land
            count++;
            for (int[] d : dir) {
                int nr = i + d[0];
                int nc = j + d[1];
                if (nr >= 0 && nc >= 0 && nr < r && nc < c && par[nr * c + nc] != -1) {
                    int v = nr * c + nc;
                    int p1 = findPar(par, u);
                    int p2 = findPar(par, v);
                    if (p1 != p2) {
                        count--;
                        par[p2] = p1;
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }

    public int numSimilarGroups(String[] strs) {
        int len = strs.length;
        int[] par = new int[len];// index based mapping
        int groups = len;
        for (int i = 0; i < len; i++) {
            par[i] = i;
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {// checking each pair similarity
                boolean similar = isSimilar(strs, i, j);
                if (similar) {
                    int p1 = findPar(par, i);
                    int p2 = findPar(par, j);
                    if (p1 != p2) {
                        par[p1] = p2;
                        groups--;
                    }
                }
            }
        }
        return groups;
    }

    public boolean isSimilar(String[] str, int i, int j) {
        int count = 0;
        for (int k = 0; k < str[i].length(); k++) {
            char ch1 = str[i].charAt(k);
            char ch2 = str[j].charAt(k);
            if (ch1 != ch2) {
                count++;
            }
            if (count > 2) {
                return false;
            }
        }
        return true;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        dsu uf = new dsu(n + 1);
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int val = uf.union(u, v);
            if (val == -1) {
                return e;
            }
        }
        return new int[0];
    }
}